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
fun <T : Any> updateBitmaskForChangedColumns(prevBit: Long, dto1: T, dto2: T): Long {
    return dto1::class.memberProperties
        .filter { it.findAnnotation<BitPosition>() != null }
        .fold(prevBit) { newBit, prop ->
            val bitPosition = prop.findAnnotation<BitPosition>()!!.position
            val typedProp = prop as KProperty1<T, *>
            val value1 = typedProp.get(dto1)
            val value2 = typedProp.get(dto2)

            if (value1 != value2) newBit or (1L shl bitPosition) else newBit
        }
}

fun <T : Any> applyBitmaskToNullableColumns(columnUpdate: Long, dto: T): T {
    val kClass = dto::class
    require(kClass.isData) { "Only data classes are supported" }

    val copyFunction = kClass.members.first { it.name == "copy" }
    val copyParametersByName = copyFunction.parameters.associateBy { it.name }

    val args = mutableMapOf<KParameter, Any?>()
    copyFunction.instanceParameter?.let { args[it] = dto }

    kClass.memberProperties.forEach { prop ->
        val bitPosition = prop.findAnnotation<BitPosition>()?.position ?: return@forEach
        val bitSet = (columnUpdate and (1L shl bitPosition)) != 0L
        val parameter = copyParametersByName[prop.name]

        parameter?.let {
            when {
                bitSet -> args[parameter] = prop.getter.call(dto)
                prop.returnType.isMarkedNullable -> args[parameter] = null
                else -> throw IllegalArgumentException("Non-nullable property '${prop.name}' cannot be set to null")
            }
        }
    }

    copyFunction.parameters
        .filter { it.kind == KParameter.Kind.VALUE && !args.containsKey(it) }
        .forEach { parameter ->
            kClass.memberProperties.firstOrNull { it.name == parameter.name }
                ?.let { args[parameter] = it.getter.call(dto) }
        }

    @Suppress("UNCHECKED_CAST")
    return copyFunction.callBy(args) as T
}