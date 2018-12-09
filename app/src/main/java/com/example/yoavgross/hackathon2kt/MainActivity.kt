package com.example.yoavgross.hackathon2kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()
        val scope = CoroutineScope(coroutineContext)
        val dao: DbItemDao = MyRoomDb.getInstance(this, scope).myDao()
        scope.launch {
            val items = getItems(dao, scope)
            val recycler = findViewById<RecyclerView>(R.id.rv)
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            //val adapter = MyAdapter(this, listOf(DbItem("arik"), DbItem("Bentz")))
            val adapter = MyAdapter(this@MainActivity, items)
            recycler.adapter = adapter
        }
    }

    suspend fun getItems(dao: DbItemDao, scope: CoroutineScope) = {
        withContext(Dispatchers.IO){
            dao.getAllItems()
        }
    }
}
