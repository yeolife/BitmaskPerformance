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
import kotlinx.coroutines.launch
import kotlin.random.Random

class CardViewModel(application: Application): AndroidViewModel(application) {
    private val rd = Random

    private val cardDao: CardDao = AppDatabase.getInstance(application).cardDao()

    private val _cardList = MutableStateFlow<List<CardEntity>>(emptyList())
    val cardList: StateFlow<List<CardEntity>> = _cardList.asStateFlow()

    init {
        viewModelScope.launch {
            val startMemory = logMemoryUsage()
            val startTime = System.nanoTime()
            cardDao.getAllCards().collect { cards ->
                _cardList.value = cards

                val endMemory = logMemoryUsage()
                val endTime = System.nanoTime()

                Log.d("", "My Used Read Memory: ${endMemory - startMemory}")
                Log.d("", "My Used Read Time: ${(endTime - startTime) / 1000000} ms")
            }
        }
    }

    private fun logMemoryUsage(): Long {
        Runtime.getRuntime().gc()
        val runtime = Runtime.getRuntime()
        val usedMemory = runtime.totalMemory() - runtime.freeMemory()

        return usedMemory
    }

    fun insertCard(card: CardEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            cardDao.insertCard(card)
        }
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

    fun updateCard(card: CardEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            cardDao.upsertCard(card)
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

            Log.d("", "My Used Insert Memory: ${endMemory - startMemory}")
            Log.d("", "My Used Insert Time: ${(endTime - startTime) / 1000000} ms")
        }
    }

    fun deleteCard(card: CardEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            cardDao.deleteCard(card)
        }
    }

    fun deleteCards() {
        viewModelScope.launch(Dispatchers.IO) {
            cardDao.deleteCards()
        }
    }
}