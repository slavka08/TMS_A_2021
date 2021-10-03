package by.tms.homework25room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.tms.homework25room.dao.MySqlHelper.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class Cat(@PrimaryKey(autoGenerate = true) val ID: Int = 0, val NAME: String, val AGE: Int) {
}