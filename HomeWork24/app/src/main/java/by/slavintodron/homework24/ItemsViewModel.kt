package by.slavintodron.homework24

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slavintodron.homework24.entity2.Movie
import by.slavintodron.homework24.retrofit.Common
import by.slavintodron.homework24.retrofit.interfaces.ApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class ItemsViewModel : ViewModel() {
    private val _itemsList: MutableLiveData<MutableList<Movie>> = MutableLiveData()
    val itemsList: LiveData<MutableList<Movie>> get() = _itemsList
    private var mService: ApiRequest

    init {
        _itemsList.value = mutableListOf()
        mService = Common.retrofitServices
        addItems()
    }

    private fun addItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mService.getUsersList().awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()
                data?.addAll(data)
                data?.addAll(data)
                data?.shuffle()
                _itemsList.postValue(data)
            }
        }
    }
    fun getItem(id:Int):Movie?{
       return _itemsList.value?.get(id)
    }
}