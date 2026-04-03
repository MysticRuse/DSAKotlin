package com.mr.patterns.stacks.easy

/**
 * 20. Valid Parentheses
 * Easy
 * Topics: Stack, HashMap
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 * Input: s = "(]"
 * Output: false
 * Example 4:
 * Input: s = "([])"
 * Output: true
 * Example 5:
 * Input: s = "([)]"
 * Output: false
 * Constraints:
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 */
class ValidParenthesis {

    fun isValidParenthesis(s: String): Boolean {

        val stack = ArrayDeque<Char>()

        val closeToOpenMap = mapOf(
            ')' to '(',
            ']' to '[',
            '}' to '{'
        )

        for (char in s) {
            if (closeToOpenMap.containsKey(char)) {
                if (stack.isEmpty() || stack.last() != closeToOpenMap[char]) {
                    return false
                } else {
                    stack.removeLast()
                }
            } else {
                stack.addLast(char)
            }
        }

        // Note: have to return stack.isEmpty(). Not return true.
        return stack.isEmpty()
    }
}

fun main() {
    val vp = ValidParenthesis()

    val testCases = listOf(
        "()" to true,
        "()[]{}" to true,
        "(]" to false,
        "([)]" to false,
        "{[]}" to true,
        "" to true,
        "(" to false,
        "(((" to false,
        "({[]})" to true,
        "({[})" to false
    )

    for ((input, expected) in testCases) {
        val result = vp.isValidParenthesis(input)
        println("Input: \"$input\" | Expected: $expected | Got: $result | ${if (result == expected) "PASS" else "FAIL"}")
    }
}