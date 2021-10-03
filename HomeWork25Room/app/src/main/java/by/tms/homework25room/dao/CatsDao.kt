package by.tms.homework25room.dao

import androidx.room.*
import by.tms.homework25room.dao.MySqlHelper.Companion.TABLE_NAME
import by.tms.homework25room.entity.Cat

@Dao
interface CatsDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllCats(): List<Cat>

    @Query("SELECT * FROM $TABLE_NAME  order by NAME asc")
    fun getAllCatsOrderName(): List<Cat>

    @Query("SELECT * FROM $TABLE_NAME  order by AGE asc")
    fun getAllCatsOrderAge(): List<Cat>

    @Query("SELECT * FROM $TABLE_NAME WHERE ID = :id")
    fun getCat(id: Int): Cat

    @Query("DELETE FROM $TABLE_NAME WHERE ID = :catId")
    fun deleteById(catId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCat(cat: Cat)

    @Update
    fun updateCat(cat: Cat)
}