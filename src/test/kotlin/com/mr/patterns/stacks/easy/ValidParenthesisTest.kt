package com.mr.patterns.stacks.easy

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ValidParenthesisTest {

    private val vp = ValidParenthesis()

    @Test
    fun testEmptyString() {
        assertTrue(vp.isValidParenthesis(""))
    }

    @Test
    fun testSinglePair() {
        assertTrue(vp.isValidParenthesis("()"))
        assertTrue(vp.isValidParenthesis("[]"))
        assertTrue(vp.isValidParenthesis("{}"))
    }

    @Test
    fun testMultiplePairs() {
        assertTrue(vp.isValidParenthesis("()[]{}"))
        assertTrue(vp.isValidParenthesis("{[()]}"))
    }

    @Test
    fun testInvalidOrder() {
        assertFalse(vp.isValidParenthesis("(]"))
        assertFalse(vp.isValidParenthesis("([)]"))
        assertFalse(vp.isValidParenthesis("{(})"))
    }

    @Test
    fun testUnbalanced() {
        assertFalse(vp.isValidParenthesis("("))
        assertFalse(vp.isValidParenthesis("((("))
        assertFalse(vp.isValidParenthesis("(()"))
        assertFalse(vp.isValidParenthesis("())"))
    }

    @Test
    fun testValidNested() {
        assertTrue(vp.isValidParenthesis("({[]})"))
        assertTrue(vp.isValidParenthesis("((({{{[[[]]]}}})))"))
    }
}