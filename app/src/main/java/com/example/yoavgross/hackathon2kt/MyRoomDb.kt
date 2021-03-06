package com.example.yoavgross.hackathon2kt

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [DbItem::class], version = 1)
abstract class MyRoomDb : RoomDatabase() {

    abstract fun myDao(): DbItemDao

    companion object {

        @Volatile
        private var instance: MyRoomDb? = null

        fun getInstance(context: Context, scope: CoroutineScope): MyRoomDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context, scope).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context, scope: CoroutineScope): MyRoomDb {
            return Room.databaseBuilder(context, MyRoomDb::class.java, "my_db")
                .addCallback(object : RoomDatabase.Callback() {

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        scope.launch(Dispatchers.IO) {
                            var item = DbItem("itzko")
                            instance?.myDao()?.insertDataObject(item)
                            item = DbItem("shraga")
                            instance?.myDao()?.insertDataObject(item)
                            item = DbItem("shosho")
                            instance?.myDao()?.insertDataObject(item)
                            item = DbItem("makron")
                            instance?.myDao()?.insertDataObject(item)
                            instance?.myDao()?.insertList(listOf(DbItem("boni"), DbItem("sjoqddd")))
                        }
                    }
                })
                .build()
        }

    }

}

