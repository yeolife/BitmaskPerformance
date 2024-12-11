package com.example.bitmaskperformance.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitmaskperformance.CardViewModel.Companion.UPDATE
import kotlin.random.Random

@Entity(tableName = "card")
data class CardEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,

    @BitPosition(0) val col1: Int?,
    @BitPosition(1) val col2: Int?,
    @BitPosition(2) val col3: Int?,
    @BitPosition(3) val col4: Int?,
    @BitPosition(4) val col5: Int?,
    @BitPosition(5) val col6: Int?,
    @BitPosition(6) val col7: Int?,
    @BitPosition(7) val col8: Int?,
    @BitPosition(8) val col9: Int?,
    @BitPosition(9) val col10: Int?,

    @BitPosition(10) val col11: Int?,
    @BitPosition(11) val col12: Int?,
    @BitPosition(12) val col13: Int?,
    @BitPosition(13) val col14: Int?,
    @BitPosition(14) val col15: Int?,
    @BitPosition(15) val col16: Int?,
    @BitPosition(16) val col17: Int?,
    @BitPosition(17) val col18: Int?,
    @BitPosition(18) val col19: Int?,
    @BitPosition(19) val col20: Int?,

    @BitPosition(20) val col21: Int?,
    @BitPosition(21) val col22: Int?,
    @BitPosition(22) val col23: Int?,
    @BitPosition(23) val col24: Int?,
    @BitPosition(24) val col25: Int?,
    @BitPosition(25) val col26: Int?,
    @BitPosition(26) val col27: Int?,
    @BitPosition(27) val col28: Int?,
    @BitPosition(28) val col29: Int?,
    @BitPosition(29) val col30: Int?,

    @BitPosition(30) val col31: Int?,
    @BitPosition(31) val col32: Int?,
    @BitPosition(32) val col33: Int?,
    @BitPosition(33) val col34: Int?,
    @BitPosition(34) val col35: Int?,
    @BitPosition(35) val col36: Int?,
    @BitPosition(36) val col37: Int?,
    @BitPosition(37) val col38: Int?,
    @BitPosition(38) val col39: Int?,
    @BitPosition(39) val col40: Int?,

    @BitPosition(40) val col41: Int?,
    @BitPosition(41) val col42: Int?,
    @BitPosition(42) val col43: Int?,
    @BitPosition(43) val col44: Int?,
    @BitPosition(44) val col45: Int?,
    @BitPosition(45) val col46: Int?,
    @BitPosition(46) val col47: Int?,
    @BitPosition(47) val col48: Int?,
    @BitPosition(48) val col49: Int?,
    @BitPosition(49) val col50: Int?,

    @BitPosition(50) val col51: Int?,
    @BitPosition(51) val col52: Int?,
    @BitPosition(52) val col53: Int?,
    @BitPosition(53) val col54: Int?,
    @BitPosition(54) val col55: Int?,
    @BitPosition(55) val col56: Int?,
    @BitPosition(56) val col57: Int?,
    @BitPosition(57) val col58: Int?,
    @BitPosition(58) val col59: Int?,
    @BitPosition(59) val col60: Int?,

    @BitPosition(60) val col61: Int?,
    @BitPosition(61) val col62: Int?,

    val bitmask: Long = 0L
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
