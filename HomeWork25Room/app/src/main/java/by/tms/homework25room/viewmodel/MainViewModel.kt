package by.tms.homework25room.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import by.tms.homework25room.entity.Cat
import by.tms.homework25room.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CatRepository, private val sharedPreferences: SharedPreferences) :
    ViewModel() {

    val listCats: LiveData<List<Cat>> = repository.listCats
    val cat: LiveData<Cat> = repository.Cat

    init {
        repository.getAllData()
    }

    fun getCatById(id: Int){
        repository.getCatById(id)
    }

    fun deleteById(id:Int) {
        repository.deleteCatById(id)
    }

    fun update(cat: Cat){
        repository.updateCat(cat)
    }

    fun insert(cat: Cat) = viewModelScope.launch {
        Dispatchers.IO
        repository.insert(cat)
    }

    fun reloadData(){
        repository.getAllData()
    }
    class MainViewModelFactory(
        private val repository: CatRepository,
        private val sharedPreferences: SharedPreferences
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(repository, sharedPreferences) as T
            }
            throw IllegalArgumentException("Unknown model class")
        }

    }
}