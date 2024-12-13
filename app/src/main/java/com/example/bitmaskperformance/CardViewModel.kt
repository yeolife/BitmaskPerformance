package com.example.bitmaskperformance

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitmaskperformance.data.AppDatabase
import com.example.bitmaskperformance.data.CardDao
import com.example.bitmaskperformance.data.CardEntity
import com.example.bitmaskperformance.data.bitmaskColumn
import com.example.bitmaskperformance.data.randomEntity
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

    fun insertCards() {
        val testData = randomEntity(10000, INSERT)
        measurePerformance(INSERT) {
            cardDao.insertCards(testData)
        }
    }

    fun updateCards() {
        val testData = randomEntity(10000, UPDATE)
        measurePerformance(UPDATE) {
            val updatedData = testData.map { updateCard ->
                val prevCard = cardDao.getCard(updateCard.id)
                updateCard.copy(bitmask = bitmaskColumn(prevCard.bitmask, prevCard, updateCard))
            }
            cardDao.updateCards(updatedData)
        }
    }

    fun deleteCards() {
        viewModelScope.launch(Dispatchers.IO) {
            cardDao.deleteCards()
        }
    }

    private fun measurePerformance(operation: String, block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val startMemory = logMemoryUsage()
            val startTime = System.nanoTime()

            block()

            System.gc()

            val endMemory = logMemoryUsage()
            val endTime = System.nanoTime()

            val usedMemoryKb = (endMemory - startMemory) / 1024.0 // KB 단위
            val usedMemoryMb = usedMemoryKb / 1024.0 // MB 단위
            val elapsedTimeSec = (endTime - startTime) / 1_000_000_000.0 // 초 단위

            Log.d("", "My Used $operation Memory: %.3f MB".format(usedMemoryMb))
            Log.d("", "My Used $operation Time: %.3f seconds".format(elapsedTimeSec))
        }
    }

    companion object {
        const val INSERT: String = "INSERT"
        const val UPDATE: String = "UPDATE"
    }
}