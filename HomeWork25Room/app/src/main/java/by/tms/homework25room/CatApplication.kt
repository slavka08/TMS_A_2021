package by.tms.homework25room

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import by.tms.homework25room.dao.MySqlHelper
import by.tms.homework25room.database.CatsRoomDataBase
import by.tms.homework25room.repository.CatRepository

class CatApplication : Application() {
    private val sqlHelper by lazy { MySqlHelper(this) }
    private val database by lazy { CatsRoomDataBase.getDataBase(this) }
    val repository by lazy { CatRepository(database.catDao(), sqlHelper, prefs) }
    val prefs: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(this) }
}