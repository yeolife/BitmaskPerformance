package com.example.bitmaskperformance.data

import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class BitPosition(val position: Int)

// 바뀐 컬럼 비트 켜기
fun bitmaskColumn(prevBit: Long, dto1: CardEntity, dto2: CardEntity): Long {
    var newBit = prevBit

    if (dto1.col1 != dto2.col1) newBit = newBit or (1L shl 0)
    if (dto1.col2 != dto2.col2) newBit = newBit or (1L shl 1)
    if (dto1.col3 != dto2.col3) newBit = newBit or (1L shl 2)
    if (dto1.col4 != dto2.col4) newBit = newBit or (1L shl 3)
    if (dto1.col5 != dto2.col5) newBit = newBit or (1L shl 4)
    if (dto1.col6 != dto2.col6) newBit = newBit or (1L shl 5)
    if (dto1.col7 != dto2.col7) newBit = newBit or (1L shl 6)
    if (dto1.col8 != dto2.col8) newBit = newBit or (1L shl 7)
    if (dto1.col9 != dto2.col9) newBit = newBit or (1L shl 8)
    if (dto1.col10 != dto2.col10) newBit = newBit or (1L shl 9)
    if (dto1.col11 != dto2.col11) newBit = newBit or (1L shl 10)
    if (dto1.col12 != dto2.col12) newBit = newBit or (1L shl 11)
    if (dto1.col13 != dto2.col13) newBit = newBit or (1L shl 12)
    if (dto1.col14 != dto2.col14) newBit = newBit or (1L shl 13)
    if (dto1.col15 != dto2.col15) newBit = newBit or (1L shl 14)
    if (dto1.col16 != dto2.col16) newBit = newBit or (1L shl 15)
    if (dto1.col17 != dto2.col17) newBit = newBit or (1L shl 16)
    if (dto1.col18 != dto2.col18) newBit = newBit or (1L shl 17)
    if (dto1.col19 != dto2.col19) newBit = newBit or (1L shl 18)
    if (dto1.col20 != dto2.col20) newBit = newBit or (1L shl 19)
    if (dto1.col21 != dto2.col21) newBit = newBit or (1L shl 20)
    if (dto1.col22 != dto2.col22) newBit = newBit or (1L shl 21)
    if (dto1.col23 != dto2.col23) newBit = newBit or (1L shl 22)
    if (dto1.col24 != dto2.col24) newBit = newBit or (1L shl 23)
    if (dto1.col25 != dto2.col25) newBit = newBit or (1L shl 24)
    if (dto1.col26 != dto2.col26) newBit = newBit or (1L shl 25)
    if (dto1.col27 != dto2.col27) newBit = newBit or (1L shl 26)
    if (dto1.col28 != dto2.col28) newBit = newBit or (1L shl 27)
    if (dto1.col29 != dto2.col29) newBit = newBit or (1L shl 28)
    if (dto1.col30 != dto2.col30) newBit = newBit or (1L shl 29)
    if (dto1.col31 != dto2.col31) newBit = newBit or (1L shl 30)
    if (dto1.col32 != dto2.col32) newBit = newBit or (1L shl 31)
    if (dto1.col33 != dto2.col33) newBit = newBit or (1L shl 32)
    if (dto1.col34 != dto2.col34) newBit = newBit or (1L shl 33)
    if (dto1.col35 != dto2.col35) newBit = newBit or (1L shl 34)
    if (dto1.col36 != dto2.col36) newBit = newBit or (1L shl 35)
    if (dto1.col37 != dto2.col37) newBit = newBit or (1L shl 36)
    if (dto1.col38 != dto2.col38) newBit = newBit or (1L shl 37)
    if (dto1.col39 != dto2.col39) newBit = newBit or (1L shl 38)
    if (dto1.col40 != dto2.col40) newBit = newBit or (1L shl 39)
    if (dto1.col41 != dto2.col41) newBit = newBit or (1L shl 40)
    if (dto1.col42 != dto2.col42) newBit = newBit or (1L shl 41)
    if (dto1.col43 != dto2.col43) newBit = newBit or (1L shl 42)
    if (dto1.col44 != dto2.col44) newBit = newBit or (1L shl 43)
    if (dto1.col45 != dto2.col45) newBit = newBit or (1L shl 44)
    if (dto1.col46 != dto2.col46) newBit = newBit or (1L shl 45)
    if (dto1.col47 != dto2.col47) newBit = newBit or (1L shl 46)
    if (dto1.col48 != dto2.col48) newBit = newBit or (1L shl 47)
    if (dto1.col49 != dto2.col49) newBit = newBit or (1L shl 48)
    if (dto1.col50 != dto2.col50) newBit = newBit or (1L shl 49)
    if (dto1.col51 != dto2.col51) newBit = newBit or (1L shl 50)
    if (dto1.col52 != dto2.col52) newBit = newBit or (1L shl 51)
    if (dto1.col53 != dto2.col53) newBit = newBit or (1L shl 52)
    if (dto1.col54 != dto2.col54) newBit = newBit or (1L shl 53)
    if (dto1.col55 != dto2.col55) newBit = newBit or (1L shl 54)
    if (dto1.col56 != dto2.col56) newBit = newBit or (1L shl 55)
    if (dto1.col57 != dto2.col57) newBit = newBit or (1L shl 56)
    if (dto1.col58 != dto2.col58) newBit = newBit or (1L shl 57)
    if (dto1.col59 != dto2.col59) newBit = newBit or (1L shl 58)
    if (dto1.col60 != dto2.col60) newBit = newBit or (1L shl 59)
    if (dto1.col61 != dto2.col61) newBit = newBit or (1L shl 60)
    if (dto1.col62 != dto2.col62) newBit = newBit or (1L shl 61)

    return newBit
}

// 바뀌지 않은 컬럼은 null
fun <T : Any> getBitmaskDto(columnUpdate: Long, dto: T): T {
    val kClass = dto::class
    if (!kClass.isData) {
        throw IllegalArgumentException("Only data classes are supported")
    }

    val copyFunction = kClass.members.firstOrNull { it.name == "copy" }
        ?: throw IllegalArgumentException("No copy function found")

    val args = mutableMapOf<KParameter, Any?>()

    // 'copy' 함수의 인스턴스 파라미터를 설정
    val instanceParameter = copyFunction.instanceParameter
    if (instanceParameter != null) {
        args[instanceParameter] = dto
    }

    val copyParametersByName = copyFunction.parameters.associateBy { it.name }

    kClass.memberProperties.forEach { prop ->
        val bitPosition = prop.findAnnotation<BitPosition>()?.position ?: return@forEach

        val bitSet = (columnUpdate and (1L shl bitPosition)) != 0L

        val parameter = copyParametersByName[prop.name]
        if (parameter != null) {
            if (bitSet) {
                // 비트가 설정되어 있으면 원래 값을 유지
                args[parameter] = prop.getter.call(dto)
            } else {
                if (prop.returnType.isMarkedNullable) {
                    // 비트가 설정되지 않았고 속성이 nullable이면 null로 설정
                    args[parameter] = null
                } else {
                    // 비트가 설정되지 않았고 속성이 nullable이 아니면 기본값 사용 또는 예외 처리
                    // 기본값이 없으면 예외를 발생시켜야 합니다.
                    throw IllegalArgumentException("Non-nullable property '${prop.name}' cannot be set to null")
                }
            }
        }
    }

    // 필수 파라미터 중 아직 값이 설정되지 않은 경우 원래 값으로 채워줍니다.
    copyFunction.parameters.forEach { parameter ->
        if (!args.containsKey(parameter) && parameter.kind == KParameter.Kind.VALUE) {
            val prop = kClass.memberProperties.firstOrNull { it.name == parameter.name }
            if (prop != null) {
                args[parameter] = prop.getter.call(dto)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    val newDto = copyFunction.callBy(args) as T

    return newDto
}