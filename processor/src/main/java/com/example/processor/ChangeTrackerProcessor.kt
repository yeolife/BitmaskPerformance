package com.example.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ksp.writeTo

class ChangeTrackerProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        // @Entity 붙은 클래스 찾기
        val entitySymbols = resolver.getSymbolsWithAnnotation("androidx.room.Entity")
            .filterIsInstance<KSClassDeclaration>()

        for (entityClass in entitySymbols) {
            // ColumnBitIndex 애노테이션 달린 프로퍼티 수집
            val bitIndexedProperties: List<Pair<String, Int>> = entityClass.getAllProperties()
                .mapNotNull { prop ->
                    val anno = prop.annotations.firstOrNull { it.shortName.asString() == "TrackChanges" } ?: return@mapNotNull null
                    val indexValue = anno.arguments.firstOrNull()?.value as? Int ?: return@mapNotNull null
                    prop.simpleName.asString() to indexValue
                }
                .sortedBy { it.second }
                .toList()

            if (bitIndexedProperties.isNotEmpty()) {
                generateChangeTracker(entityClass, bitIndexedProperties)
            }
        }

        return emptyList()
    }

    private fun generateChangeTracker(
        entityClass: KSClassDeclaration,
        mapping: List<Pair<String, Int>>
    ) {
        val packageName = entityClass.packageName.asString()
        val className = entityClass.simpleName.asString()
        val fileName = "${className}ChangeTracker"

        val entityType = ClassName(packageName, className)

        val fileBuilder = FileSpec.builder(packageName, fileName)

        // calculateBitmaskComparedTo 함수 생성
        val funcBuilder = FunSpec.builder("calculateBitmaskComparedTo")
            .receiver(entityType)
            .addParameter("other", entityType)
            .returns(Long::class)
            .addStatement("var mask = 0L")

        mapping.forEach { (propName, idx) ->
            funcBuilder.addStatement(
                "if (this.%L != other.%L) mask = mask or (1L shl %L)",
                propName, propName, idx
            )
        }

        funcBuilder.addStatement("return mask")

        fileBuilder.addFunction(funcBuilder.build())

        fileBuilder.build().writeTo(codeGenerator, Dependencies(false))
    }
}