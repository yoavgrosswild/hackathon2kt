package com.example.yoavgross.hackathon2kt

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DbItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataObject(dbItem: DbItem)

    @Insert
    @JvmSuppressWildcards
    fun insertList(list : List<DbItem>)

    @Query("select * from db_items")
    fun getAllItems() : List<DbItem>

    @Query("delete from db_items")
    fun deleteAllItems()


}