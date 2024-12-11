package com.example.processor

interface ChangeTracker<T> {
    fun calculateBitmask(prev: T, current: T): Long
}