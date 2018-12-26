package com.example.yoavgross.hackathon2kt

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "db_items")
data class DbItem(@PrimaryKey @ColumnInfo(name = "item_name") val name : String)
