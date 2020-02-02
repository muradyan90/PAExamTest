package com.aram.paexamtest.vievmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aram.paexamtest.getOrAwaitValue
import com.aram.paexamtest.repository.ArticlesRepository
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//@ExperimentalCoroutinesApi
class AllArticlesViewModelTest {

    private var repo: ArticlesRepository = mockk()
    private lateinit var allArticlesViewModel: AllArticlesViewModel

    // testing rule for testinc live data
    // Executes each task synchronously using Architecture Components.

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        allArticlesViewModel = AllArticlesViewModel(repo)
    }

    @Test
    fun test(){

        runBlockingTest {

        }

        // extention function to get live data value from view model without lifecycle owner for testing
        allArticlesViewModel.articles.getOrAwaitValue()
    }
    @After
    fun tearDown() {

    }
}