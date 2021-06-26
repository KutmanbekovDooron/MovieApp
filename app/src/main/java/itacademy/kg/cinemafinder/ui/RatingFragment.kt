package itacademy.kg.cinemafinder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import itacademy.kg.cinemafinder.R
import itacademy.kg.cinemafinder.adaptor.AdapterMovie
import itacademy.kg.cinemafinder.adaptor.onClick
import itacademy.kg.cinemafinder.databinding.FragmentRatingBinding
import itacademy.kg.cinemafinder.models.ResultMovie

class RatingFragment : Fragment(),onClick {

    private var _binding:FragmentRatingBinding ?= null
    private val binding get() = _binding!!
    lateinit var viewModel: HomeViewModel
    lateinit var adapter:AdapterMovie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRatingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.liveDataForCurrentUpcaming.observe(viewLifecycleOwner){
            configureRecyclerView(it.results, binding.ratingRacycle)
        }

    }


    private fun configureRecyclerView(list: MutableList<ResultMovie>, recycle: RecyclerView) {
        binding.apply {
            recycle.layoutManager =
                GridLayoutManager(view?.context, 2, GridLayoutManager.VERTICAL,  false)
            recycle.setHasFixedSize(true)
            adapter = AdapterMovie(list.sortedByDescending { it.vote_average }, this@RatingFragment, requireContext())
            recycle.adapter = adapter
        }
    }

    override fun onClickItamMovei(position: Int, movie: List<ResultMovie>) {
        val bundle = Bundle()
        bundle.putSerializable("info", movie.get(position))

        val fragment = DetalisFragment()
        fragment.arguments = bundle

        setFragment(fragment)
    }

    private fun setFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }
}