package com.example.yoavgross.hackathon2kt

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class DbTests2 {


    private lateinit var dao: DbItemDao
    private lateinit var db: MyRoomDb


    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MyRoomDb::class.java).build()
        dao = db.myDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val item = DbItem("bara")
        dao.insertDataObject(item)
        //val get = dao.getItem(DbItem("bara"))

    }


    class Presenter {

        val titleLiveData = MutableLiveData<String>()

        fun showTitle(title: String) {
            titleLiveData.postValue(title)
        }
    }

    class PresenterTests {
        @get:Rule
        var rule: TestRule = InstantTaskExecutorRule()

        @Test
        fun showTitleTest() {
            val presenter = Presenter()

            presenter.showTitle("title")

            assertEquals("title", presenter.titleLiveData.value)
        }
    }

}