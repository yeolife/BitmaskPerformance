package com.example.bitmaskperformance

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitmaskperformance.data.AppDatabase
import com.example.bitmaskperformance.data.CardDao
import com.example.bitmaskperformance.data.CardEntity
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
            cardDao.getAllCards().collect { cards ->
                _cardList.value = cards
            }
        }
    }

    private fun logMemoryUsage(tag: String) {
        Runtime.getRuntime().gc()
        val runtime = Runtime.getRuntime()
        val usedMemory = runtime.totalMemory() - runtime.freeMemory()
        println("$tag: Used memory = ${usedMemory / 1024} KB")
    }

    fun measureExecutionTime(tag: String, block: () -> Unit) {
        val startTime = System.nanoTime()
        block()
        val elapsedTime = System.nanoTime() - startTime
        println("$tag: Execution time = ${elapsedTime / 1_000_000} ms")
    }

    fun insertCard(card: CardEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            cardDao.insertCard(card)
        }
    }

    fun insertCards(size: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val testData = List(size) { _ ->
                CardEntity(id = 0,
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
                    rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
                    rd.nextInt(10000), rd.nextInt(10000))
            }

            cardDao.insertCards(testData)
        }
    }

    fun updateCard(card: CardEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            cardDao.upsertCard(card)
        }
    }

    fun updateCards(size: Int) {
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

        viewModelScope.launch(Dispatchers.IO) {
            logMemoryUsage("Before DirtyFlag Update")
            val startTime = System.nanoTime()

            val updatedData = testData.map { card ->
                val prevCard = cardDao.getCard(card.id)
                setDirtyFlags(prevCard, card)
            }

            cardDao.updateCards(updatedData)

            logMemoryUsage("After DirtyFlag Update")
            val endTime = System.nanoTime()

            val duration = (endTime - startTime) / 1000000 // 밀리초로 변환
            Log.d("PerformanceTest", "Function A 실행 시간: $duration ms")
        }
    }

    private fun setDirtyFlags(prev: CardEntity, updated: CardEntity): CardEntity {
        return updated.copy(
            df1 = prev.col1 != updated.col1,
            df2 = prev.col2 != updated.col2,
            df3 = prev.col3 != updated.col3,
            df4 = prev.col4 != updated.col4,
            df5 = prev.col5 != updated.col5,
            df6 = prev.col6 != updated.col6,
            df7 = prev.col7 != updated.col7,
            df8 = prev.col8 != updated.col8,
            df9 = prev.col9 != updated.col9,
            df10 = prev.col10 != updated.col10,
            df11 = prev.col11 != updated.col11,
            df12 = prev.col12 != updated.col12,
            df13 = prev.col13 != updated.col13,
            df14 = prev.col14 != updated.col14,
            df15 = prev.col15 != updated.col15,
            df16 = prev.col16 != updated.col16,
            df17 = prev.col17 != updated.col17,
            df18 = prev.col18 != updated.col18,
            df19 = prev.col19 != updated.col19,
            df20 = prev.col20 != updated.col20,
            df21 = prev.col21 != updated.col21,
            df22 = prev.col22 != updated.col22,
            df23 = prev.col23 != updated.col23,
            df24 = prev.col24 != updated.col24,
            df25 = prev.col25 != updated.col25,
            df26 = prev.col26 != updated.col26,
            df27 = prev.col27 != updated.col27,
            df28 = prev.col28 != updated.col28,
            df29 = prev.col29 != updated.col29,
            df30 = prev.col30 != updated.col30,
            df31 = prev.col31 != updated.col31,
            df32 = prev.col32 != updated.col32,
            df33 = prev.col33 != updated.col33,
            df34 = prev.col34 != updated.col34,
            df35 = prev.col35 != updated.col35,
            df36 = prev.col36 != updated.col36,
            df37 = prev.col37 != updated.col37,
            df38 = prev.col38 != updated.col38,
            df39 = prev.col39 != updated.col39,
            df40 = prev.col40 != updated.col40,
            df41 = prev.col41 != updated.col41,
            df42 = prev.col42 != updated.col42,
            df43 = prev.col43 != updated.col43,
            df44 = prev.col44 != updated.col44,
            df45 = prev.col45 != updated.col45,
            df46 = prev.col46 != updated.col46,
            df47 = prev.col47 != updated.col47,
            df48 = prev.col48 != updated.col48,
            df49 = prev.col49 != updated.col49,
            df50 = prev.col50 != updated.col50,
            df51 = prev.col51 != updated.col51,
            df52 = prev.col52 != updated.col52,
            df53 = prev.col53 != updated.col53,
            df54 = prev.col54 != updated.col54,
            df55 = prev.col55 != updated.col55,
            df56 = prev.col56 != updated.col56,
            df57 = prev.col57 != updated.col57,
            df58 = prev.col58 != updated.col58,
            df59 = prev.col59 != updated.col59,
            df60 = prev.col60 != updated.col60,
            df61 = prev.col61 != updated.col61,
            df62 = prev.col62 != updated.col62
        )
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