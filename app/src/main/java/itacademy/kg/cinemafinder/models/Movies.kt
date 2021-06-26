package itacademy.kg.cinemafinder.models

import java.io.Serializable

data class Movies (var page : Int,
    val results : MutableList<ResultMovie>,
    var total_pages : Int,
    var total_results: Int)

data class Videos(
    val results: MutableList<LinkVideos>
)