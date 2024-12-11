package com.example.bitmaskperformance.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitmaskperformance.CardViewModel.Companion.UPDATE
import kotlin.random.Random

@Entity(tableName = "card")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val col1: Int?,
    val col2: Int?,
    val col3: Int?,
    val col4: Int?,
    val col5: Int?,
    val col6: Int?,
    val col7: Int?,
    val col8: Int?,
    val col9: Int?,
    val col10: Int?,

    val col11: Int?,
    val col12: Int?,
    val col13: Int?,
    val col14: Int?,
    val col15: Int?,
    val col16: Int?,
    val col17: Int?,
    val col18: Int?,
    val col19: Int?,
    val col20: Int?,

    val col21: Int?,
    val col22: Int?,
    val col23: Int?,
    val col24: Int?,
    val col25: Int?,
    val col26: Int?,
    val col27: Int?,
    val col28: Int?,
    val col29: Int?,
    val col30: Int?,

    val col31: Int?,
    val col32: Int?,
    val col33: Int?,
    val col34: Int?,
    val col35: Int?,
    val col36: Int?,
    val col37: Int?,
    val col38: Int?,
    val col39: Int?,
    val col40: Int?,

    val col41: Int?,
    val col42: Int?,
    val col43: Int?,
    val col44: Int?,
    val col45: Int?,
    val col46: Int?,
    val col47: Int?,
    val col48: Int?,
    val col49: Int?,
    val col50: Int?,

    val col51: Int?,
    val col52: Int?,
    val col53: Int?,
    val col54: Int?,
    val col55: Int?,
    val col56: Int?,
    val col57: Int?,
    val col58: Int?,
    val col59: Int?,
    val col60: Int?,

    val col61: Int?,
    val col62: Int?,

    val df1: Boolean = false,
    val df2: Boolean = false,
    val df3: Boolean = false,
    val df4: Boolean = false,
    val df5: Boolean = false,
    val df6: Boolean = false,
    val df7: Boolean = false,
    val df8: Boolean = false,
    val df9: Boolean = false,
    val df10: Boolean = false,

    val df11: Boolean = false,
    val df12: Boolean = false,
    val df13: Boolean = false,
    val df14: Boolean = false,
    val df15: Boolean = false,
    val df16: Boolean = false,
    val df17: Boolean = false,
    val df18: Boolean = false,
    val df19: Boolean = false,
    val df20: Boolean = false,

    val df21: Boolean = false,
    val df22: Boolean = false,
    val df23: Boolean = false,
    val df24: Boolean = false,
    val df25: Boolean = false,
    val df26: Boolean = false,
    val df27: Boolean = false,
    val df28: Boolean = false,
    val df29: Boolean = false,
    val df30: Boolean = false,

    val df31: Boolean = false,
    val df32: Boolean = false,
    val df33: Boolean = false,
    val df34: Boolean = false,
    val df35: Boolean = false,
    val df36: Boolean = false,
    val df37: Boolean = false,
    val df38: Boolean = false,
    val df39: Boolean = false,
    val df40: Boolean = false,

    val df41: Boolean = false,
    val df42: Boolean = false,
    val df43: Boolean = false,
    val df44: Boolean = false,
    val df45: Boolean = false,
    val df46: Boolean = false,
    val df47: Boolean = false,
    val df48: Boolean = false,
    val df49: Boolean = false,
    val df50: Boolean = false,

    val df51: Boolean = false,
    val df52: Boolean = false,
    val df53: Boolean = false,
    val df54: Boolean = false,
    val df55: Boolean = false,
    val df56: Boolean = false,
    val df57: Boolean = false,
    val df58: Boolean = false,
    val df59: Boolean = false,
    val df60: Boolean = false,

    val df61: Boolean = false,
    val df62: Boolean = false,
)

fun randomEntity(size: Int, state: String): List<CardEntity> {
    val rd = Random

    return List(size) { index ->
        CardEntity(id = if(state == UPDATE) index.toLong() + 1 else 0L,
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
}

