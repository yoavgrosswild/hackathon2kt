package com.example.yoavgross.hackathon2kt

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


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

        Assert.assertEquals("title", presenter.titleLiveData.value)
    }
}