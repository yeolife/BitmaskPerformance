package com.example.bitmaskperformance

import android.app.Application
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

    fun insertCard(card: CardEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            cardDao.insertCard(card)
        }
    }

    fun insertCards(size: Int) {
        viewModelScope.launch(Dispatchers.IO) {
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
        }
    }

    fun updateCard(card: CardEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            cardDao.upsertCard(card)
        }
    }

    fun updateCards(size: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val testData = List(size) { index ->
                CardEntity(id = index.toLong(), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000), rd.nextInt(10000),
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

            cardDao.updateCards(testData)
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