package com.example.bitmaskperformance

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitmaskperformance.data.AppDatabase
import com.example.bitmaskperformance.data.CardDao
import com.example.bitmaskperformance.data.CardEntity
import com.example.bitmaskperformance.data.bitmaskColumn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlin.random.Random

class CardViewModel(application: Application): AndroidViewModel(application) {
    private val rd = Random

    private val cardDao: CardDao = AppDatabase.getInstance(application).cardDao()

    private val _cardList = MutableStateFlow<List<CardEntity>>(emptyList())
    val cardList: StateFlow<List<CardEntity>> = _cardList.asStateFlow()

    init {
        viewModelScope.launch {
            cardDao.getAllCards().collect { cards ->
                _cardList.value = cards
            }
        }
    }

    private fun logMemoryUsage(): Long {
        Runtime.getRuntime().gc()
        val runtime = Runtime.getRuntime()
        val usedMemory = runtime.totalMemory() - runtime.freeMemory()

        return usedMemory
    }

    fun insertCards(size: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val startMemory = logMemoryUsage()
            val startTime = System.nanoTime()

            val testData = List(size) { _ ->
                CardEntity(id = 0, rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000))
            }

            cardDao.insertCards(testData)

            val endMemory = logMemoryUsage()
            val endTime = System.nanoTime()

            Log.d("", "My Used Insert Memory: ${endMemory - startMemory}")
            Log.d("", "My Used Insert Time: ${(endTime - startTime) / 1000000} ms")
        }
    }

    fun updateCards(size: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            val startMemory = logMemoryUsage()
            val startTime = System.nanoTime()

            val testData = List(size) { index ->
                CardEntity(id = index.toLong() + 1, rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000))
            }

            val updatedData = testData.map { card ->
                val prevCard = cardDao.getCard(card.id)
                card.copy(bitmask = bitmaskColumn(prevCard.bitmask, prevCard, card))
            }
            cardDao.updateCards(updatedData)

            val endMemory = logMemoryUsage()
            val endTime = System.nanoTime()

            Log.d("", "My Used Update Memory: ${endMemory - startMemory}")
            Log.d("", "My Used Update Time: ${(endTime - startTime) / 1000000} ms")
        }
    }

    fun deleteCards() {
        viewModelScope.launch(Dispatchers.IO) {
            cardDao.deleteCards()
        }
    }
}