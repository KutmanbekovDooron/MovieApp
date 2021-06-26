package itacademy.kg.cinemafinder.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import itacademy.kg.cinemafinder.databinding.FragmentDetalisBinding
import itacademy.kg.cinemafinder.models.LinkVideos
import itacademy.kg.cinemafinder.models.ResultMovie
import itacademy.kg.cinemafinder.models.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalisFragment : Fragment() {
    private var _binding: FragmentDetalisBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    lateinit var list: MutableList<LinkVideos>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        list = mutableListOf()

        val bundle = this.arguments
        val movie = bundle?.getSerializable("info") as ResultMovie

        binding.apply {
            Glide.with(this@DetalisFragment).load(Utils.IMAGE_URL + movie.backdrop_path)
                .into(imageDete)
            nameDete.text = movie.title
            ratingDete.text = movie.vote_average.toString()
            dataDete.text = movie.release_date
            descriptionDete.text = movie.overview
        }
        var linkKey = ""
        viewModel.getVideos(movie.id.toInt())
        viewModel.liveDataForCurrentVideos.observe(viewLifecycleOwner) {
            list.addAll(it.results)
            for (i in list) {
                binding.Link.text = i.key
                linkKey = i.key
            }
        }

        binding.Link.setOnClickListener {
            Toast.makeText(context, "link", Toast.LENGTH_SHORT).show()
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://watch?v=$linkKey"))
            startActivity(intent)
        }


    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        Utils.LIST.clear()
        super.onDestroy()
    }

}