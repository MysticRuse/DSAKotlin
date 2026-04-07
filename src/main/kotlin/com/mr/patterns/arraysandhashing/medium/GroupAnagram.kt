package com.mr.patterns.arraysandhashing.medium

/**
 * 49. Group Anagrams
 * Medium
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Explanation:
 * There is no string in strs that can be rearranged to form "bat".
 * The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
 * The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 * Constraints:
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
class GroupAnagram {

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return groupAnagramsHashTable(strs)
    }

    private fun groupAnagramsHashTable(strs: Array<String>): List<List<String>> {

        // Key   = frequency count of characters (size 26 for 'a' to 'z')
        // Value = list of strings that match that frequency pattern (anagrams)
        val anagramMap = HashMap<List<Int>, MutableList<String>>()

        for (s in strs) {
            // Create a character frequency array of size 26 initialized with 0
            // Example: "eat" -> count['e']=1, count['a']=1, count['t']=1
            val count = MutableList(26) { 0 }

            // Fill the frequency count
            for (c in s) {
                count[c - 'a']++
            }

            // Use the frequency list as the key.
            // All anagrams will produce the same frequency list.
            anagramMap.getOrPut(count) { mutableListOf() }.add(s)
        }
        return anagramMap.values.toList()
    }

    private fun groupAnagramsSortAndHashMap(strs: Array<String>): List<List<String>> {

        // Time Complexity: O(NKlogK), where N is the number of strs,
        // and K is the maximum length of a string in strs.
        // The outer loop has complexity O(N) as we iterate through each string.
        // Then, we sort each string in O(KlogK) time.
        // Space Complexity: O(NK), the total information content stored in ans.

        // eat, tea, tan, ate, nat, bat
        // sort each word.

        val sortedKeyMap = mutableMapOf<String, MutableList<String>>();

        for (word in strs) {
            // Sort characters and convert to a string as key
            // Convert it into a string - one line
            val sortedWord = word.toCharArray().sorted().joinToString("")

            // Convert in steps
            //val wordArray = word.toCharArray()
            //wordArray.sort()
            //val sortedWord = wordArray.joinToString("")

            // Group the words by the sorted key - one line
            sortedKeyMap.getOrPut(sortedWord) {mutableListOf<String>()}.add(word)

            // Elaboration:
            //val listKey = map.getOrPut(sortedWord) {
            //    mutableListOf<String>()
            //}
            //listKey.add(word)
        }

        return sortedKeyMap.values.toList()
    }
}

fun main() {
    val solver = GroupAnagram()

    // Helper: normalize output (sort groups internally and sort groups)
    // Normalize output for testing:
    // 1. Sort each group internally
    // 2. Sort the list of groups so ordering doesn't affect equality checks
    fun normalize(groups: List<List<String>>): List<List<String>> {
        return groups.map { it.sorted() }.sortedBy { it.joinToString(",") }
    }

    val testCases = listOf(
        Pair(
            arrayOf("eat", "tea", "tan", "ate", "nat", "bat"),
            listOf(
                listOf("eat", "tea", "ate"),
                listOf("tan", "nat"),
                listOf("bat")
            )
        ),
        Pair(
            arrayOf(""),
            listOf(listOf(""))
        ),
        Pair(
            arrayOf("a"),
            listOf(listOf("a"))
        ),
        Pair(
            arrayOf("abc", "bca", "cab", "xyz", "zyx", "yxz"),
            listOf(
                listOf("abc", "bca", "cab"),
                listOf("xyz", "zyx", "yxz")
            )
        ),
        Pair(
            arrayOf("a", "b", "c"),
            listOf(
                listOf("a"),
                listOf("b"),
                listOf("c")
            )
        ),
        Pair(
            arrayOf("ab", "ba", "abc", "bca", "cab", "a"),
            listOf(
                listOf("ab", "ba"),
                listOf("abc", "bca", "cab"),
                listOf("a")
            )
        )
    )

    var passed = 0
    var failed = 0

    for ((index, test) in testCases.withIndex()) {
        val (input, expected) = test

        val result = solver.groupAnagrams(input)

        val normalizedResult = normalize(result)
        val normalizedExpected = normalize(expected)

        val isPass = normalizedResult == normalizedExpected
        if (isPass) passed++ else failed++

        println(
            "Test #${index + 1}: input=${input.contentToString()}\n" +
                    "Expected: $normalizedExpected\n" +
                    "Got     : $normalizedResult\n" +
                    "${if (isPass) "PASS" else "FAIL"}\n"
        )
    }

    println("========== TEST SUMMARY ==========")
    println("Total Tests : ${testCases.size}")
    println("Passed      : $passed")
    println("Failed      : $failed")
    println("==================================")
}