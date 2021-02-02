package com.am.stackexchange.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.am.stackexchange.model.data.Item

@Dao
interface ItemsDao {

    @Query("SELECT * FROM item")
    fun getItems(): List<Item>

    @Insert
    fun setItems(question: List<Item>)

}