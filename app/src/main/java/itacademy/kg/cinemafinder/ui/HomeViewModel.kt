package itacademy.kg.cinemafinder.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import itacademy.kg.cinemafinder.models.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import kotlin.math.log

class HomeViewModel : ViewModel() {

    companion object {
        const val LOG = "GetMovie"
    }


    var liveDataForCurrent = MutableLiveData<Movies>()
    var liveDataForCurrentUpcaming = MutableLiveData<Movies>()
    var liveDataForCurrentVideos = MutableLiveData<Videos>()
    var repository = Repository()

    init {
        getMovies("popularity.desc")
        getMovies("vote_count.desc")
    }

    fun getMovies(sort_by:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCurrentMovie(Utils.API_KEY,"ru",sort_by)
            if (response.isSuccessful && response.body() != null) {
                if (sort_by.equals("popularity.desc")) {
                    withContext(Dispatchers.Main) {
                        liveDataForCurrent.value = response.body()!!
                    }
                }else {
                    withContext(Dispatchers.Main) {
                        liveDataForCurrentUpcaming.value = response.body()!!
                    }
                }
            }
        }
    }

    fun getVideos (id : Int) {
        Log.i("mytag",id.toString())
        viewModelScope.launch (Dispatchers.IO ){
            val response = repository.getCurrentVidoes(id,Utils.API_KEY,"ru")
            if (response.isSuccessful && response.body() != null){
                Log.i("myTag",response.body().toString())
                withContext(Dispatchers.Main){
                    liveDataForCurrentVideos.value = response.body()!!
                }
            }
        }
    }

}