package com.example.yoavgross.hackathon2kt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {


    val job = Job()
    val context: CoroutineContext
        get() = job + Dispatchers.Main

    val scope = CoroutineScope(context)

    val items : LiveData<List<DbItem>>

    init {

        val dao: DbItemDao = MyRoomDb.getInstance(app,scope).myDao()
        items = dao.getAllItems()
    }

}