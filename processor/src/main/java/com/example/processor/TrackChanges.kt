package com.example.processor

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class TrackChanges(val bitPosition: Int)