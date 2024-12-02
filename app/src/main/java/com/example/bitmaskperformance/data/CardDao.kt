package com.example.bitmaskperformance.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    // 조회

    // 조회
    @Query("""
        SELECT * 
        FROM card
        WHERE id = :cardId
    """)
    fun getCard(cardId: Long): CardEntity

    @Query("""
        SELECT * 
        FROM card
    """)
    fun getAllCards(): Flow<List<CardEntity>>

    // 생성
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(cards: List<CardEntity>): List<Long>

    // 업데이트
    @Update
    suspend fun updateCard(card: CardEntity)

    @Update
    suspend fun updateCards(cards: List<CardEntity>)
    
    @Upsert
    suspend fun upsertCard(card: CardEntity)

    @Upsert
    suspend fun upsertCards(cards: List<CardEntity>)

    // 삭제
    @Delete
    suspend fun deleteCard(card: CardEntity)

    @Query("delete from card")
    suspend fun deleteCards()
}