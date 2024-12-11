package com.example.bitmaskperformance

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitmaskperformance.data.AppDatabase
import com.example.bitmaskperformance.data.CardDao
import com.example.bitmaskperformance.data.CardEntity
import com.example.bitmaskperformance.data.changeBitmaskEntity
import com.example.bitmaskperformance.data.randomEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
        measurePerformance("Insert") {
            val testData = randomEntity(10000, INSERT)
            cardDao.insertCards(testData)
        }
    }

    fun updateCards() {
        measurePerformance("Update") {
            val testData = randomEntity(10000, UPDATE)
            val updatedData = testData.map { updateCard ->
                val prevCard = cardDao.getCard(updateCard.id)
                updateCard.copy(bitmask = changeBitmaskEntity(prevCard.bitmask, prevCard, updateCard))
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