package by.tms.homework25room.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import by.tms.homework25room.entity.Cat

class MySqlHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        val DATABASE_NAME = "stars.db"
        val COL_1 = "ID"
        val COL_2 = "NAME"
        val COL_3 = "AGE"
        const val TABLE_NAME = "tablecat"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,NAME TEXT NOT NULL, AGE INTEGER NOT NULL)")
        db?.execSQL("INSERT INTO $TABLE_NAME(NAME,AGE)  VALUES('test1 name',1)")
        db?.execSQL("INSERT INTO $TABLE_NAME(NAME,AGE)  VALUES('test2 name',1)")
        db?.execSQL("INSERT INTO $TABLE_NAME(NAME,AGE)  VALUES('test3 name',2)")
    }

    fun insertData(cat: Cat) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, cat.NAME)
        contentValues.put(COL_3, cat.AGE)
        db.insert(TABLE_NAME, null, contentValues)
    }

    fun updateData(cat: Cat) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, cat.NAME)
        contentValues.put(COL_3, cat.AGE)
        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(cat.ID.toString()))
    }

    fun deleteData(id: Int) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        db.delete(TABLE_NAME, "$COL_1 = ?", arrayOf(id.toString()))
    }

    val allData: Cursor
        get() {
            val db = this.readableDatabase
            return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        }

    val allDataSortName: Cursor
        get() {
            val db = this.readableDatabase
            return db.rawQuery("SELECT * FROM $TABLE_NAME  order by NAME asc", null)
        }

    val allDataSortAge: Cursor
        get() {
            val db = this.readableDatabase
            return db.rawQuery("SELECT * FROM $TABLE_NAME  order by AGE asc", null)
        }

    fun getCatData(id: Int): Cat? {
        val db = this.readableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE ID = ?", arrayOf(id.toString()))
        return if (cursor.moveToFirst()) return Cat(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getInt(2)
        )
        else null
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}