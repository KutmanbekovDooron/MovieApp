package itacademy.kg.cinemafinder.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import itacademy.kg.cinemafinder.R
import itacademy.kg.cinemafinder.adaptor.AdapterMovie
import itacademy.kg.cinemafinder.adaptor.onClick
import itacademy.kg.cinemafinder.databinding.FragmentHomeBinding
import itacademy.kg.cinemafinder.models.Movies
import itacademy.kg.cinemafinder.models.ResultMovie

class HomeFragment : Fragment(), onClick {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    lateinit var listPosition: MutableList<ResultMovie>
    lateinit var list: MutableList<ResultMovie>
    lateinit var adaptor: AdapterMovie
    var page:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listPosition = mutableListOf()
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)



        viewModel.liveDataForCurrent.observe(viewLifecycleOwner, {
//            for (page in 1.until(it.total_pages)){
//                Log.i("myTag","${page}")
//            }
            configureRecyclerView(it.results, binding.recycle1)
        })

        list = mutableListOf()
        viewModel.liveDataForCurrentUpcaming.observe(viewLifecycleOwner) {
            configureRecyclerView(it.results, binding.recycle2)
        }


    }

    private fun configureRecyclerView(list: MutableList<ResultMovie>, recycle: RecyclerView) {
        listPosition.addAll(list)
        binding.apply {
            recycle.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,  false)
            recycle.setHasFixedSize(true)
            adaptor = AdapterMovie(list.sortedByDescending { it.vote_average }, this@HomeFragment, requireContext())
            recycle.adapter = adaptor
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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