package com.mr.problemsets.set_2

/**
 * Binary Addition
 * Given two binary strings a and b, find their sum as a binary string. i.e, perform the binary addition operation on these values.
 *
 * Input
 * The first line of input contains a string a, denoting the first binary number.
 * The second line of input contains a string b, denoting the second binary number.
 * Output
 * The sum of a and b, in binary form.
 * Constraints
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 * Example #1
 * Input: 11 , 1
 * Output: 100
 * Explanation: Binary addition of these values is 100.
 * Example #2
 * Input:  1010, 1011
 * Output 10101
 * Explanation: Binary addition of these values is 10101.
 */
fun addbinaryQuick(a: String, b: String): String {
    return (a.toInt(2) + b.toInt(2)).toString(2)
}

fun addBinary(a: String, b: String): String {
    var carry = 0
    val result = StringBuilder()

    var i = a.length - 1
    var j = b.length - 1

    while (i >= 0 || j >= 0 || carry > 0) {
        val digitA = if (i >= 0) a[i] - '0' else 0
        val digitB = if (j >= 0) b[j] - '0' else 0

        val sum = digitA + digitB + carry
        result.append(sum % 2)
        carry = sum / 2
        i--
        j--
    }

    return result.reverse().toString()
}



