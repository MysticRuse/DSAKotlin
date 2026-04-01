package com.mr.problemsets.set_2

/**
 * LRU cache Operations
 * LRUCache(int capacity): Initialize the LRU cache with a positive capacity.
 * No keys should be present in the cache initially.
 *
 * int get(int key): Return the value of the key, if it exists.
 * Otherwise, return -1.
 *
 * void put(int key, int value): Update the value of the key if the key exists.
 * Otherwise,add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Your LRU Cache should support the following operations:
 * LRUCache(int capacity) Initialize the LRU cache with positive capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists.
 *     - Otherwise, add the key-value pair to the cache.
 *     - If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 *
 * Note: Any key that is accessed for a valid get() OR put() operation can be considered as recently used key.
 * Input
 *   The first line of input contains an integer N, representing the capacity of the cache.
 *   The second line of input contains an integer M, representing the number of operations.
 *  The third line of input contains M space-separated strings, each representing an operation. The format of an operation can be one of these:
 *  GET, x: Get the value of key x present in Cache
 *  PUT, x, y: Update the value of the key x if x exists, else add the key-value (x, y) pair to the cache.
 *
 * Output
 * An array of values returned by the GET operations.
 * Constraints
 * 1 <= N <= 20
 *
 * Example #1 Input
 *   2
 *   6
 *   GET,2 PUT,1,100 PUT,2,125 PUT,3,150 GET,1 GET,3
 * Output
 *   -1 -1 150O
 * Explanation: So the operations on LRU cache with capacity 2 are,
 * GET, 2 --> The Cache is initially empty. i.e, Key 2 does not exist, so return -1 PUT,1,100 --> Insert Key 1 with value 100 --> [ (1,100) ]
 * PUT,2,125 --> Insert Key 2 with value 125 --> [ ( 1,100 ), ( 2, 125 ) ]
 * PUT,3,150 --> Cache is full, so delete the least recently used key 1 and insert the new pair --> [( 2, 125 ) (3, 150) ]
 * GET, 1 --> Key 1 does not exist, so return -1 GET, 3 --> Key 3 exists, so return its value 150 So, the final array will be [-1, -1, 150]
 *
 * Example #2 Input
 *   3
 *   5
 *   PUT,11,25 PUT,22,50 PUT,11,75 GET,11 GET,22
 * Output
 *  75 50
 * Explanation: The operations on LRU cache with capacity 3 are, PUT,11,25 --> Insert Key 11 with value 25 --> [ (11,25) ] PUT,22,50 --> Insert Key 22 with value 50 --> [ (11, 25), ( 22, 50 ) ]
 * PUT,11,75 --> Key 11 exists in the cache, so update the Key 11 with value 75 --> [ ( 22, 50 ), (11, 75) ]
 * (Here LRU will be key 22, since 11 is updated with new value).
 * GET, 11 --> Key 11 exists, so return its value 75 GET, 22 --> Key 22 exists, so return its value 50 So, the final array will be [75, 50].
 *
 */

/**
 * LRU Cache Implementation
 *
 * Uses a HashMap for O(1) lookup and a doubly linked list for O(1) eviction.
 * The linked list maintains access order: head = most recent, tail = least recent.
 *
 * Time Complexity: O(1) for both get and put
 * Space Complexity: O(capacity)
 */
class LRUCache(private val capacity: Int) {

    class Node(key: Int, value: Int) {
        val key: Int  = key
        var value: Int  = value
        var prev: Node? = null
        var next: Node? = null

        override fun toString(): String {
            return "Node(key=$key, value=$value)"
        }
    }

    // <key, Node>
    private val cache: HashMap<Int, Node> = HashMap() // O(1) lookup for get()

    // Dummy sentinel nodes to mark head and tail of doubly linked list
    private val head: Node = Node(-1, -1) // MRU end
    private val tail: Node = Node(-1, -1) // LRU end

    init {
        // Set up the doubly linked list for maintaining LRU order
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int {
        val node = cache[key] ?: return -1
        // MRU operation
        moveToFront(node)
        //println("Get key $key from Cache: $cache")
        return node.value
    }

    fun put(key: Int, value: Int) {
        // find existing key, val if present
        val putNode = cache[key]
        if (putNode == null) {
            // Create a new node
            val newNode = Node(key, value)
            addToFront(newNode)
            // Add to cache
            cache[key] = newNode

            //println("Adding new node: ${newNode.toString()}")

            // Evict LRU node if cache is full
            if (cache.size > capacity) {
                val lruNode = removeLast()
                // println("Evicting LRU node: $lruNode")
                // remove from cacheMap
                cache.remove(lruNode?.key)
            }
        } else {
            // update existing node
            putNode.value = value

            moveToFront(putNode)
            //println("Updating existing node: ${putNode.toString()} and put to front")
        }

        println("Cache: $cache")
    }

    //------------List manipulation methods ----------
    private fun addToFront(node: Node) {
        node.prev = head
        node.next = head.next
        head.next?.prev = node
        head.next = node
    }

    private fun removeLast() : Node? {
        val lruNode = tail.prev
        if (lruNode != null) {
            removeNode(lruNode)
        }
        return lruNode
    }


    private fun moveToFront(node: Node) {
        // NOTE!! First remove the node and then add it to the front!
        // BEWARE!! The wrong order of remove and add will mess up the pointers.
        removeNode(node)
        addToFront(node)
    }

    private fun removeNode(node: Node) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
    }
}

/**
 * Main function to handle input/output as per problem format.
 */
fun main() {
    val capacity = readln().trim().toInt()
    val numOperations = readln().trim().toInt()
    val operations = readln().trim().split(" ")

    val cache = LRUCache(capacity)
    val results = mutableListOf<Int>()

    for (op in operations) {
        val parts = op.split(",")
        when (parts[0]) {
            "GET" -> {
                val key = parts[1].toInt()
                results.add(cache.get(key))
            }
            "PUT" -> {
                val key = parts[1].toInt()
                val value = parts[2].toInt()
                cache.put(key, value)
            }
        }
    }

    println(results.joinToString(" "))
}