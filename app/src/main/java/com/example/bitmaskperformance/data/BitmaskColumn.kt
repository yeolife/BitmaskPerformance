package com.example.bitmaskperformance.data

// 바뀐 컬럼 비트 켜기
fun CardEntity.updateBitmaskForChangedColumns(dto: CardEntity): Long {
    var newBit = this.bitmask

    if (this.col1 != dto.col1) newBit = newBit or (1L shl 0)
    if (this.col2 != dto.col2) newBit = newBit or (1L shl 1)
    if (this.col3 != dto.col3) newBit = newBit or (1L shl 2)
    if (this.col4 != dto.col4) newBit = newBit or (1L shl 3)
    if (this.col5 != dto.col5) newBit = newBit or (1L shl 4)
    if (this.col6 != dto.col6) newBit = newBit or (1L shl 5)
    if (this.col7 != dto.col7) newBit = newBit or (1L shl 6)
    if (this.col8 != dto.col8) newBit = newBit or (1L shl 7)
    if (this.col9 != dto.col9) newBit = newBit or (1L shl 8)
    if (this.col10 != dto.col10) newBit = newBit or (1L shl 9)
    if (this.col11 != dto.col11) newBit = newBit or (1L shl 10)
    if (this.col12 != dto.col12) newBit = newBit or (1L shl 11)
    if (this.col13 != dto.col13) newBit = newBit or (1L shl 12)
    if (this.col14 != dto.col14) newBit = newBit or (1L shl 13)
    if (this.col15 != dto.col15) newBit = newBit or (1L shl 14)
    if (this.col16 != dto.col16) newBit = newBit or (1L shl 15)
    if (this.col17 != dto.col17) newBit = newBit or (1L shl 16)
    if (this.col18 != dto.col18) newBit = newBit or (1L shl 17)
    if (this.col19 != dto.col19) newBit = newBit or (1L shl 18)
    if (this.col20 != dto.col20) newBit = newBit or (1L shl 19)
    if (this.col21 != dto.col21) newBit = newBit or (1L shl 20)
    if (this.col22 != dto.col22) newBit = newBit or (1L shl 21)
    if (this.col23 != dto.col23) newBit = newBit or (1L shl 22)
    if (this.col24 != dto.col24) newBit = newBit or (1L shl 23)
    if (this.col25 != dto.col25) newBit = newBit or (1L shl 24)
    if (this.col26 != dto.col26) newBit = newBit or (1L shl 25)
    if (this.col27 != dto.col27) newBit = newBit or (1L shl 26)
    if (this.col28 != dto.col28) newBit = newBit or (1L shl 27)
    if (this.col29 != dto.col29) newBit = newBit or (1L shl 28)
    if (this.col30 != dto.col30) newBit = newBit or (1L shl 29)
    if (this.col31 != dto.col31) newBit = newBit or (1L shl 30)
    if (this.col32 != dto.col32) newBit = newBit or (1L shl 31)
    if (this.col33 != dto.col33) newBit = newBit or (1L shl 32)
    if (this.col34 != dto.col34) newBit = newBit or (1L shl 33)
    if (this.col35 != dto.col35) newBit = newBit or (1L shl 34)
    if (this.col36 != dto.col36) newBit = newBit or (1L shl 35)
    if (this.col37 != dto.col37) newBit = newBit or (1L shl 36)
    if (this.col38 != dto.col38) newBit = newBit or (1L shl 37)
    if (this.col39 != dto.col39) newBit = newBit or (1L shl 38)
    if (this.col40 != dto.col40) newBit = newBit or (1L shl 39)
    if (this.col41 != dto.col41) newBit = newBit or (1L shl 40)
    if (this.col42 != dto.col42) newBit = newBit or (1L shl 41)
    if (this.col43 != dto.col43) newBit = newBit or (1L shl 42)
    if (this.col44 != dto.col44) newBit = newBit or (1L shl 43)
    if (this.col45 != dto.col45) newBit = newBit or (1L shl 44)
    if (this.col46 != dto.col46) newBit = newBit or (1L shl 45)
    if (this.col47 != dto.col47) newBit = newBit or (1L shl 46)
    if (this.col48 != dto.col48) newBit = newBit or (1L shl 47)
    if (this.col49 != dto.col49) newBit = newBit or (1L shl 48)
    if (this.col50 != dto.col50) newBit = newBit or (1L shl 49)
    if (this.col51 != dto.col51) newBit = newBit or (1L shl 50)
    if (this.col52 != dto.col52) newBit = newBit or (1L shl 51)
    if (this.col53 != dto.col53) newBit = newBit or (1L shl 52)
    if (this.col54 != dto.col54) newBit = newBit or (1L shl 53)
    if (this.col55 != dto.col55) newBit = newBit or (1L shl 54)
    if (this.col56 != dto.col56) newBit = newBit or (1L shl 55)
    if (this.col57 != dto.col57) newBit = newBit or (1L shl 56)
    if (this.col58 != dto.col58) newBit = newBit or (1L shl 57)
    if (this.col59 != dto.col59) newBit = newBit or (1L shl 58)
    if (this.col60 != dto.col60) newBit = newBit or (1L shl 59)
    if (this.col61 != dto.col61) newBit = newBit or (1L shl 60)
    if (this.col62 != dto.col62) newBit = newBit or (1L shl 61)

    return newBit
}

// 바뀌지 않은 컬럼은 null
fun CardEntity.applyBitmaskToNullableColumns(dto: CardEntity): CardEntity {
    return dto.copy(
        col1 = if (bitmask and (1L shl 0) != 0L) dto.col1 else null,
        col2 = if (bitmask and (1L shl 1) != 0L) dto.col2 else null,
        col3 = if (bitmask and (1L shl 2) != 0L) dto.col3 else null,
        col4 = if (bitmask and (1L shl 3) != 0L) dto.col4 else null,
        col5 = if (bitmask and (1L shl 4) != 0L) dto.col5 else null,
        col6 = if (bitmask and (1L shl 5) != 0L) dto.col6 else null,
        col7 = if (bitmask and (1L shl 6) != 0L) dto.col7 else null,
        col8 = if (bitmask and (1L shl 7) != 0L) dto.col8 else null,
        col9 = if (bitmask and (1L shl 8) != 0L) dto.col9 else null,
        col10 = if (bitmask and (1L shl 9) != 0L) dto.col10 else null,
        col11 = if (bitmask and (1L shl 10) != 0L) dto.col11 else null,
        col12 = if (bitmask and (1L shl 11) != 0L) dto.col12 else null,
        col13 = if (bitmask and (1L shl 12) != 0L) dto.col13 else null,
        col14 = if (bitmask and (1L shl 13) != 0L) dto.col14 else null,
        col15 = if (bitmask and (1L shl 14) != 0L) dto.col15 else null,
        col16 = if (bitmask and (1L shl 15) != 0L) dto.col16 else null,
        col17 = if (bitmask and (1L shl 16) != 0L) dto.col17 else null,
        col18 = if (bitmask and (1L shl 17) != 0L) dto.col18 else null,
        col19 = if (bitmask and (1L shl 18) != 0L) dto.col19 else null,
        col20 = if (bitmask and (1L shl 19) != 0L) dto.col20 else null,
        col21 = if (bitmask and (1L shl 20) != 0L) dto.col21 else null,
        col22 = if (bitmask and (1L shl 21) != 0L) dto.col22 else null,
        col23 = if (bitmask and (1L shl 22) != 0L) dto.col23 else null,
        col24 = if (bitmask and (1L shl 23) != 0L) dto.col24 else null,
        col25 = if (bitmask and (1L shl 24) != 0L) dto.col25 else null,
        col26 = if (bitmask and (1L shl 25) != 0L) dto.col26 else null,
        col27 = if (bitmask and (1L shl 26) != 0L) dto.col27 else null,
        col28 = if (bitmask and (1L shl 27) != 0L) dto.col28 else null,
        col29 = if (bitmask and (1L shl 28) != 0L) dto.col29 else null,
        col30 = if (bitmask and (1L shl 29) != 0L) dto.col30 else null,
        col31 = if (bitmask and (1L shl 30) != 0L) dto.col31 else null,
        col32 = if (bitmask and (1L shl 31) != 0L) dto.col32 else null,
        col33 = if (bitmask and (1L shl 32) != 0L) dto.col33 else null,
        col34 = if (bitmask and (1L shl 33) != 0L) dto.col34 else null,
        col35 = if (bitmask and (1L shl 34) != 0L) dto.col35 else null,
        col36 = if (bitmask and (1L shl 35) != 0L) dto.col36 else null,
        col37 = if (bitmask and (1L shl 36) != 0L) dto.col37 else null,
        col38 = if (bitmask and (1L shl 37) != 0L) dto.col38 else null,
        col39 = if (bitmask and (1L shl 38) != 0L) dto.col39 else null,
        col40 = if (bitmask and (1L shl 39) != 0L) dto.col40 else null,
        col41 = if (bitmask and (1L shl 40) != 0L) dto.col41 else null,
        col42 = if (bitmask and (1L shl 41) != 0L) dto.col42 else null,
        col43 = if (bitmask and (1L shl 42) != 0L) dto.col43 else null,
        col44 = if (bitmask and (1L shl 43) != 0L) dto.col44 else null,
        col45 = if (bitmask and (1L shl 44) != 0L) dto.col45 else null,
        col46 = if (bitmask and (1L shl 45) != 0L) dto.col46 else null,
        col47 = if (bitmask and (1L shl 46) != 0L) dto.col47 else null,
        col48 = if (bitmask and (1L shl 47) != 0L) dto.col48 else null,
        col49 = if (bitmask and (1L shl 48) != 0L) dto.col49 else null,
        col50 = if (bitmask and (1L shl 49) != 0L) dto.col50 else null,
        col51 = if (bitmask and (1L shl 50) != 0L) dto.col51 else null,
        col52 = if (bitmask and (1L shl 51) != 0L) dto.col52 else null,
        col53 = if (bitmask and (1L shl 52) != 0L) dto.col53 else null,
        col54 = if (bitmask and (1L shl 53) != 0L) dto.col54 else null,
        col55 = if (bitmask and (1L shl 54) != 0L) dto.col55 else null,
        col56 = if (bitmask and (1L shl 55) != 0L) dto.col56 else null,
        col57 = if (bitmask and (1L shl 56) != 0L) dto.col57 else null,
        col58 = if (bitmask and (1L shl 57) != 0L) dto.col58 else null,
        col59 = if (bitmask and (1L shl 58) != 0L) dto.col59 else null,
        col60 = if (bitmask and (1L shl 59) != 0L) dto.col60 else null,
        col61 = if (bitmask and (1L shl 60) != 0L) dto.col61 else null,
        col62 = if (bitmask and (1L shl 61) != 0L) dto.col62 else null
    )
}