package com.aram.paexamtest.ui


import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.aram.paexamtest.ArticlesApplication
import com.aram.paexamtest.MainActivity
import com.aram.paexamtest.R
import com.google.android.material.navigation.NavigationView
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AllArticlesFragmentTest {

    // when conext needed for test can get this way /
    // if context needed in test section must change android test dependency to test dependency
    val appContext = getInstrumentation().targetContext
    private var context = ApplicationProvider.getApplicationContext<ArticlesApplication>()

    @Rule
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    var manitor = getInstrumentation().addMonitor(MainActivity::class.qualifiedName,null,false)

    private var mainActivity: MainActivity? = null


    @Before
    fun setUp() {
        mainActivity = activityRule.activity
    }

    @Test
    fun fragmentLaunch(){
        val nawView = mainActivity?.findViewById<NavigationView>(R.id.navView)
        assertNotNull(nawView)

        // activity lunchi tarberak
        ActivityScenario.launch(MainActivity::class.java)
        val fragment = AllArticlesFragment()

        mainActivity?.supportFragmentManager?.beginTransaction()
        getInstrumentation().waitForIdleSync()

       var mainActivity = getInstrumentation().waitForMonitorWithTimeout(manitor,5000)
        assertNotNull(mainActivity)
        mainActivity.finish()
    }

    @After
    fun tearDown() {
        mainActivity = null
    }
}