package com.example.yoavgross.hackathon2kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.rv)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = MyAdapter(this, listOf(DbItem("arik"), DbItem("Bentz")))

    }
}
