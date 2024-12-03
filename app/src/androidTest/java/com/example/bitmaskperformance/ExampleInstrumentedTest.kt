package com.example.bitmaskperformance

import android.app.Application
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.bitmaskperformance", appContext.packageName)
    }

    @Test
    fun benchmarkUpdateCards() {
        val appContext = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = CardViewModel(appContext)

        benchmarkRule.measureRepeated {
            runWithTimingDisabled {
                runBlocking {
                    viewModel.deleteCards()
                    viewModel.insertCards(10000)
                }
            }

            // 측정 대상 코드
            runBlocking {
                viewModel.updateCards(10000)
            }
        }
    }
}