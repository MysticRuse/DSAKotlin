package com.mr.patterns.twopointers.medium

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RemoveNthFromListEndTest {

    // ===== LeetCode Examples =====

    @Test
    fun `example 1 - remove 2nd from end of 5 nodes`() {
        val head = arrayToList(listOf(1, 2, 3, 4, 5))
        val result = removeNthNodeFromEnd(head, 2)
        assertEquals(listOf(1, 2, 3, 5), listToArray(result))
    }

    @Test
    fun `example 2 - single node remove it`() {
        val head = arrayToList(listOf(1))
        val result = removeNthNodeFromEnd(head, 1)
        assertEquals(emptyList<Int>(), listToArray(result))
    }

    @Test
    fun `example 3 - two nodes remove last`() {
        val head = arrayToList(listOf(1, 2))
        val result = removeNthNodeFromEnd(head, 1)
        assertEquals(listOf(1), listToArray(result))
    }

    // ===== Edge Cases - Removing Head =====

    @Test
    fun `remove head from two node list`() {
        val head = arrayToList(listOf(1, 2))
        val result = removeNthNodeFromEnd(head, 2)
        assertEquals(listOf(2), listToArray(result))
    }

    @Test
    fun `remove head from longer list`() {
        val head = arrayToList(listOf(1, 2, 3, 4, 5))
        val result = removeNthNodeFromEnd(head, 5)
        assertEquals(listOf(2, 3, 4, 5), listToArray(result))
    }

    // ===== Edge Cases - Removing Tail =====

    @Test
    fun `remove last node n equals 1`() {
        val head = arrayToList(listOf(1, 2, 3))
        val result = removeNthNodeFromEnd(head, 1)
        assertEquals(listOf(1, 2), listToArray(result))
    }

    @Test
    fun `remove last from five nodes`() {
        val head = arrayToList(listOf(10, 20, 30, 40, 50))
        val result = removeNthNodeFromEnd(head, 1)
        assertEquals(listOf(10, 20, 30, 40), listToArray(result))
    }

    // ===== Middle Node Removal =====

    @Test
    fun `remove middle node from three nodes`() {
        val head = arrayToList(listOf(1, 2, 3))
        val result = removeNthNodeFromEnd(head, 2)
        assertEquals(listOf(1, 3), listToArray(result))
    }

    @Test
    fun `remove middle node from five nodes`() {
        val head = arrayToList(listOf(1, 2, 3, 4, 5))
        val result = removeNthNodeFromEnd(head, 3)
        assertEquals(listOf(1, 2, 4, 5), listToArray(result))
    }

    // ===== Various List Sizes =====

    @Test
    fun `three nodes remove first`() {
        val head = arrayToList(listOf(1, 2, 3))
        val result = removeNthNodeFromEnd(head, 3)
        assertEquals(listOf(2, 3), listToArray(result))
    }

    @Test
    fun `four nodes remove second from end`() {
        val head = arrayToList(listOf(1, 2, 3, 4))
        val result = removeNthNodeFromEnd(head, 2)
        assertEquals(listOf(1, 2, 4), listToArray(result))
    }

    // ===== Values Edge Cases =====

    @Test
    fun `nodes with zero values`() {
        val head = arrayToList(listOf(0, 0, 0))
        val result = removeNthNodeFromEnd(head, 2)
        assertEquals(listOf(0, 0), listToArray(result))
    }

    @Test
    fun `nodes with large values`() {
        val head = arrayToList(listOf(100, 99, 98, 97))
        val result = removeNthNodeFromEnd(head, 1)
        assertEquals(listOf(100, 99, 98), listToArray(result))
    }

    @Test
    fun `duplicate values`() {
        val head = arrayToList(listOf(1, 1, 1, 1, 1))
        val result = removeNthNodeFromEnd(head, 3)
        assertEquals(listOf(1, 1, 1, 1), listToArray(result))
    }
}