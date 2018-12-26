package com.example.yoavgross.hackathon2kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
        val recycler = findViewById<RecyclerView>(R.id.rv)
        val adapter = MyAdapter(this@MainActivity)
        recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        recycler.adapter = adapter
        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.items.observe(this, Observer { it ->
            it?.let {
                adapter.setAist(it)
            }
        })
    }

    private suspend fun getItems(dao: DbItemDao, scope: CoroutineScope) = runBlocking {
        withContext(Dispatchers.IO) {
            dao.getAllItems()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
