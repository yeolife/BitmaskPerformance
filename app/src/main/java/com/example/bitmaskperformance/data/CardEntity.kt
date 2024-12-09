package com.example.bitmaskperformance.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.processor.TrackChanges
import kotlin.random.Random

@Entity(tableName = "card")
data class CardEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,

    @TrackChanges(bitPosition = 0) val col1: Int?,
    @TrackChanges(bitPosition = 1) val col2: Int?,
    @TrackChanges(bitPosition = 2) val col3: Int?,
    @TrackChanges(bitPosition = 3) val col4: Int?,
    @TrackChanges(bitPosition = 4) val col5: Int?,
    @TrackChanges(bitPosition = 5) val col6: Int?,
    @TrackChanges(bitPosition = 6) val col7: Int?,
    @TrackChanges(bitPosition = 7) val col8: Int?,
    @TrackChanges(bitPosition = 8) val col9: Int?,
    @TrackChanges(bitPosition = 9) val col10: Int?,

    @TrackChanges(bitPosition = 10) val col11: Int?,
    @TrackChanges(bitPosition = 11) val col12: Int?,
    @TrackChanges(bitPosition = 12) val col13: Int?,
    @TrackChanges(bitPosition = 13) val col14: Int?,
    @TrackChanges(bitPosition = 14) val col15: Int?,
    @TrackChanges(bitPosition = 15) val col16: Int?,
    @TrackChanges(bitPosition = 16) val col17: Int?,
    @TrackChanges(bitPosition = 17) val col18: Int?,
    @TrackChanges(bitPosition = 18) val col19: Int?,
    @TrackChanges(bitPosition = 19) val col20: Int?,

    @TrackChanges(bitPosition = 20) val col21: Int?,
    @TrackChanges(bitPosition = 21) val col22: Int?,
    @TrackChanges(bitPosition = 22) val col23: Int?,
    @TrackChanges(bitPosition = 23) val col24: Int?,
    @TrackChanges(bitPosition = 24) val col25: Int?,
    @TrackChanges(bitPosition = 25) val col26: Int?,
    @TrackChanges(bitPosition = 26) val col27: Int?,
    @TrackChanges(bitPosition = 27) val col28: Int?,
    @TrackChanges(bitPosition = 28) val col29: Int?,
    @TrackChanges(bitPosition = 29) val col30: Int?,

    @TrackChanges(bitPosition = 30) val col31: Int?,
    @TrackChanges(bitPosition = 31) val col32: Int?,
    @TrackChanges(bitPosition = 32) val col33: Int?,
    @TrackChanges(bitPosition = 33) val col34: Int?,
    @TrackChanges(bitPosition = 34) val col35: Int?,
    @TrackChanges(bitPosition = 35) val col36: Int?,
    @TrackChanges(bitPosition = 36) val col37: Int?,
    @TrackChanges(bitPosition = 37) val col38: Int?,
    @TrackChanges(bitPosition = 38) val col39: Int?,
    @TrackChanges(bitPosition = 39) val col40: Int?,

    @TrackChanges(bitPosition = 40) val col41: Int?,
    @TrackChanges(bitPosition = 41) val col42: Int?,
    @TrackChanges(bitPosition = 42) val col43: Int?,
    @TrackChanges(bitPosition = 43) val col44: Int?,
    @TrackChanges(bitPosition = 44) val col45: Int?,
    @TrackChanges(bitPosition = 45) val col46: Int?,
    @TrackChanges(bitPosition = 46) val col47: Int?,
    @TrackChanges(bitPosition = 47) val col48: Int?,
    @TrackChanges(bitPosition = 48) val col49: Int?,
    @TrackChanges(bitPosition = 49) val col50: Int?,

    @TrackChanges(bitPosition = 50) val col51: Int?,
    @TrackChanges(bitPosition = 51) val col52: Int?,
    @TrackChanges(bitPosition = 52) val col53: Int?,
    @TrackChanges(bitPosition = 53) val col54: Int?,
    @TrackChanges(bitPosition = 54) val col55: Int?,
    @TrackChanges(bitPosition = 55) val col56: Int?,
    @TrackChanges(bitPosition = 56) val col57: Int?,
    @TrackChanges(bitPosition = 57) val col58: Int?,
    @TrackChanges(bitPosition = 58) val col59: Int?,
    @TrackChanges(bitPosition = 59) val col60: Int?,

    @TrackChanges(bitPosition = 60) val col61: Int?,
    @TrackChanges(bitPosition = 61) val col62: Int?,

    val bitmask: Long = 0L
)
