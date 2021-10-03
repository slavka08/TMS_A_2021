package by.tms.homework25room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.tms.homework25room.dao.CatsDao
import by.tms.homework25room.dao.MySqlHelper.Companion.DATABASE_NAME
import by.tms.homework25room.entity.Cat

@Database(entities = [Cat::class], version = 1, exportSchema = false)
abstract class CatsRoomDataBase : RoomDatabase() {
    abstract fun catDao(): CatsDao

    companion object {
        @Volatile
        private var INSTANCE: CatsRoomDataBase? = null

        fun getDataBase(context: Context): CatsRoomDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatsRoomDataBase::class.java, DATABASE_NAME

                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}