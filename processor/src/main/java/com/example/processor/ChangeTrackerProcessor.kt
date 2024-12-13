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
        val entitySymbols = resolver.getSymbolsWithAnnotation("androidx.room.Entity")
            .filterIsInstance<KSClassDeclaration>()

        for (entityClass in entitySymbols) {
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

        // 첫번째 함수
        val funcBuilder = FunSpec.builder("updateBitmaskForChangedColumns")
            .receiver(entityType)
            .addParameter("other", entityType)
            .returns(Long::class)
            .addCode(buildString {
                append("var mask = this.bitmask\n")
                mapping.forEach { (propName, idx) ->
                    append("if (this.$propName != other.$propName) mask = mask or (1L shl $idx)\n")
                }
                append("return mask\n")
            })

        fileBuilder.addFunction(funcBuilder.build())

        // 두번째 함수
        val nullFuncBuilder = FunSpec.builder("applyBitmaskToNullableColumns")
            .receiver(entityType)
            .addParameter("other", entityType)
            .returns(entityType)
            .addCode(buildString {
                append("return this.copy(\n")
                // mapping을 기반으로 코드 생성
                mapping.forEachIndexed { index, (propName, _) ->
                    val comma = if (index == mapping.lastIndex) "" else ","
                    append("    $propName = if (bitmask and (1L shl $index) != 0L) this.$propName else null$comma\n")
                }
                append(")\n")
            })

        fileBuilder.addFunction(nullFuncBuilder.build())

        fileBuilder.build().writeTo(codeGenerator, Dependencies(false))
    }
}