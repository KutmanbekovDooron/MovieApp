package itacademy.kg.cinemafinder.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import itacademy.kg.cinemafinder.R
import itacademy.kg.cinemafinder.databinding.MovieItamBinding
import itacademy.kg.cinemafinder.models.ResultMovie
import itacademy.kg.cinemafinder.models.Utils
import java.io.Serializable

class AdapterMovie( listMovei: List<ResultMovie>,
                   var listiner: onClick,
                   val context:Context) :
    RecyclerView.Adapter<AdapterMovie.MovieHolder>() {

    inner class MovieHolder(val binding: MovieItamBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                listiner.onClickItamMovei(adapterPosition,list)
            }
        }

    }

    val list = listMovei

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = MovieItamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }


    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        with(holder) {
             binding.apply {
                 val position = list.get(position)
                 nameMovie.text = position.title
                 yearMovie.text = position.release_date
                 ratingsMovie.text = position.vote_average.toString()
                 Glide.with(context).load(Utils.IMAGE_URL + position.poster_path).into(imageMovie)
             }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface onClick {
    fun onClickItamMovei(position: Int, movie:List<ResultMovie>)
}