package com.am.stackexchange.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.am.stackexchange.model.data.*

@Database(entities = [Item::class, Owner::class], version = 1)
@TypeConverters(Converters::class)
abstract class StackExchangeDatabase : RoomDatabase() {

    abstract fun itemsDao(): ItemsDao

}