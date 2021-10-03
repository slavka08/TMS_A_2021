package by.tms.homework25room.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import by.tms.homework25room.dao.CatsDao
import by.tms.homework25room.dao.MySqlHelper
import by.tms.homework25room.entity.Cat


class CatRepository(
    private val catsDao: CatsDao,
    private val sqlHelper: MySqlHelper,
    private val prefs: SharedPreferences
) {
    val listCats = MutableLiveData<List<Cat>>()
    val Cat = MutableLiveData<Cat>()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertRoom(cat: Cat) {
        Log.e("DB_TYPE", "ROOM INSERT")
        catsDao.insertCat(cat)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertHelper(cat: Cat) {
        Log.e("DB_TYPE", "HELPER DELETE")
        sqlHelper.insertData(cat)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(cat: Cat) {
        val dbType = prefs.getString("db_type", "1")
        when (dbType) {
            "1" -> insertRoom(cat)
            "2" -> insertHelper(cat)
            else -> insertRoom(cat)
        }
    }

    private fun deleteCatRoom(id: Int) {
        Log.e("DB_TYPE", "ROOM DELETE")
        catsDao.deleteById(id)
        getAllData()
    }

    private fun deleteCatHelper(id: Int) {
        Log.e("DB_TYPE", "SQLITE HELPER DELETE")
        sqlHelper.deleteData(id)
    }

    fun deleteCatById(id: Int) {
        val dbType = prefs.getString("db_type", "1")
        when (dbType) {
            "1" -> deleteCatRoom(id)
            "2" -> deleteCatHelper(id)
            else -> deleteCatRoom(id)
        }
        getAllData()
    }

    fun getAllData() {
        val dbType = prefs.getString("db_type", "1")
        val sortType = prefs.getString("db_filter", "1")
        when (dbType) {
            "1" -> getAllDataRoom(sortType)
            "2" -> getAllDataHelper(sortType)
            else -> getAllDataRoom(sortType)
        }
    }

    private fun getAllDataHelper(sortType: String?) {
        Log.e("DB_TYPE", "SQLITE HELPER GET ALL DATA")
        val list = mutableListOf<Cat>()
        val cursor = when (sortType) {
            "1" -> sqlHelper.allData
            "2" -> sqlHelper.allDataSortName
            "3" -> sqlHelper.allDataSortAge
            else -> sqlHelper.allData
        }
        if (cursor.moveToFirst()) {
            list.add(
                Cat(
                    ID = cursor.getInt(0),
                    NAME = cursor.getString(1),
                    AGE = cursor.getInt(2)
                )
            )
            while (cursor.moveToNext())
                list.add(
                    Cat(
                        ID = cursor.getInt(0),
                        NAME = cursor.getString(1),
                        AGE = cursor.getInt(2)
                    )
                )
        }
        listCats.value = list
    }

    private fun getAllDataRoom(sortType: String?) {
        Log.e("DB_TYPE", "ROOM GET ALL DATA")
        listCats.value = when (sortType) {
            "1" -> catsDao.getAllCats()
            "2" -> catsDao.getAllCatsOrderName()
            "3" -> catsDao.getAllCatsOrderAge()
            else -> catsDao.getAllCats()
        }

    }

    fun getCatById(id: Int) {
        val dbType = prefs.getString("db_type", "1")
        when (dbType) {
            "1" -> getCatRoom(id)
            "2" -> getCatHelper(id)
            else -> getCatRoom(id)
        }
    }

    private fun getCatRoom(id: Int) {
        Log.e("DB_TYPE", "ROOM GET CAT BY ID")
        Cat.value = catsDao.getCat(id)
    }

    private fun getCatHelper(id: Int) {
        Log.e("DB_TYPE", "SQLITE HELPER GET CAT BY ID")
        Cat.value = sqlHelper.getCatData(id)
    }

    fun updateCat(cat: Cat) {
        val dbType = prefs.getString("db_type", "1")
        when (dbType) {
            "1" -> updateCatRoom(cat)
            "2" -> updateCatHelper(cat)
            else -> updateCatRoom(cat)
        }
        getAllData()
    }

    private fun updateCatRoom(cat: Cat) {
        Log.e("DB_TYPE", "ROOM UPDATE DATA")
        catsDao.updateCat(cat)
    }

    private fun updateCatHelper(cat: Cat) {
        Log.e("DB_TYPE", "SQLITE HELPER UPDATE DATA")
        sqlHelper.updateData(cat)
    }

}