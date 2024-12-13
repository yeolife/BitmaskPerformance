package com.example.bitmaskperformance

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitmaskperformance.data.AppDatabase
import com.example.bitmaskperformance.data.CardDao
import com.example.bitmaskperformance.data.CardEntity
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
                setDirtyFlags(prevCard, updateCard)
            }
            cardDao.updateCards(updatedData)
        }
    }

    private fun setDirtyFlags(prevDto: CardEntity, updateDto: CardEntity): CardEntity {
        return updateDto.copy(
            df1 = updateDto.df1 or (prevDto.col1 != updateDto.col1),
            df2 = updateDto.df2 or (prevDto.col2 != updateDto.col2),
            df3 = updateDto.df3 or (prevDto.col3 != updateDto.col3),
            df4 = updateDto.df4 or (prevDto.col4 != updateDto.col4),
            df5 = updateDto.df5 or (prevDto.col5 != updateDto.col5),
            df6 = updateDto.df6 or (prevDto.col6 != updateDto.col6),
            df7 = updateDto.df7 or (prevDto.col7 != updateDto.col7),
            df8 = updateDto.df8 or (prevDto.col8 != updateDto.col8),
            df9 = updateDto.df9 or (prevDto.col9 != updateDto.col9),
            df10 = updateDto.df10 or (prevDto.col10 != updateDto.col10),
            df11 = updateDto.df11 or (prevDto.col11 != updateDto.col11),
            df12 = updateDto.df12 or (prevDto.col12 != updateDto.col12),
            df13 = updateDto.df13 or (prevDto.col13 != updateDto.col13),
            df14 = updateDto.df14 or (prevDto.col14 != updateDto.col14),
            df15 = updateDto.df15 or (prevDto.col15 != updateDto.col15),
            df16 = updateDto.df16 or (prevDto.col16 != updateDto.col16),
            df17 = updateDto.df17 or (prevDto.col17 != updateDto.col17),
            df18 = updateDto.df18 or (prevDto.col18 != updateDto.col18),
            df19 = updateDto.df19 or (prevDto.col19 != updateDto.col19),
            df20 = updateDto.df20 or (prevDto.col20 != updateDto.col20),
            df21 = updateDto.df21 or (prevDto.col21 != updateDto.col21),
            df22 = updateDto.df22 or (prevDto.col22 != updateDto.col22),
            df23 = updateDto.df23 or (prevDto.col23 != updateDto.col23),
            df24 = updateDto.df24 or (prevDto.col24 != updateDto.col24),
            df25 = updateDto.df25 or (prevDto.col25 != updateDto.col25),
            df26 = updateDto.df26 or (prevDto.col26 != updateDto.col26),
            df27 = updateDto.df27 or (prevDto.col27 != updateDto.col27),
            df28 = updateDto.df28 or (prevDto.col28 != updateDto.col28),
            df29 = updateDto.df29 or (prevDto.col29 != updateDto.col29),
            df30 = updateDto.df30 or (prevDto.col30 != updateDto.col30),
            df31 = updateDto.df31 or (prevDto.col31 != updateDto.col31),
            df32 = updateDto.df32 or (prevDto.col32 != updateDto.col32),
            df33 = updateDto.df33 or (prevDto.col33 != updateDto.col33),
            df34 = updateDto.df34 or (prevDto.col34 != updateDto.col34),
            df35 = updateDto.df35 or (prevDto.col35 != updateDto.col35),
            df36 = updateDto.df36 or (prevDto.col36 != updateDto.col36),
            df37 = updateDto.df37 or (prevDto.col37 != updateDto.col37),
            df38 = updateDto.df38 or (prevDto.col38 != updateDto.col38),
            df39 = updateDto.df39 or (prevDto.col39 != updateDto.col39),
            df40 = updateDto.df40 or (prevDto.col40 != updateDto.col40),
            df41 = updateDto.df41 or (prevDto.col41 != updateDto.col41),
            df42 = updateDto.df42 or (prevDto.col42 != updateDto.col42),
            df43 = updateDto.df43 or (prevDto.col43 != updateDto.col43),
            df44 = updateDto.df44 or (prevDto.col44 != updateDto.col44),
            df45 = updateDto.df45 or (prevDto.col45 != updateDto.col45),
            df46 = updateDto.df46 or (prevDto.col46 != updateDto.col46),
            df47 = updateDto.df47 or (prevDto.col47 != updateDto.col47),
            df48 = updateDto.df48 or (prevDto.col48 != updateDto.col48),
            df49 = updateDto.df49 or (prevDto.col49 != updateDto.col49),
            df50 = updateDto.df50 or (prevDto.col50 != updateDto.col50),
            df51 = updateDto.df51 or (prevDto.col51 != updateDto.col51),
            df52 = updateDto.df52 or (prevDto.col52 != updateDto.col52),
            df53 = updateDto.df53 or (prevDto.col53 != updateDto.col53),
            df54 = updateDto.df54 or (prevDto.col54 != updateDto.col54),
            df55 = updateDto.df55 or (prevDto.col55 != updateDto.col55),
            df56 = updateDto.df56 or (prevDto.col56 != updateDto.col56),
            df57 = updateDto.df57 or (prevDto.col57 != updateDto.col57),
            df58 = updateDto.df58 or (prevDto.col58 != updateDto.col58),
            df59 = updateDto.df59 or (prevDto.col59 != updateDto.col59),
            df60 = updateDto.df60 or (prevDto.col60 != updateDto.col60),
            df61 = updateDto.df61 or (prevDto.col61 != updateDto.col61),
            df62 = updateDto.df62 or (prevDto.col62 != updateDto.col62)
        )
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