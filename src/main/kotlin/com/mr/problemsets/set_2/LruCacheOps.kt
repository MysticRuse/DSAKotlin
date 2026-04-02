package com.mr.problemsets.set_2

/**
 * 146. LRU Cache
 * Medium
 * Companies
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Example 1:
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 * Constraints:
 * 1 <= capacity <= 3000
 * 0 <= key <= 104
 * 0 <= value <= 105
 * At most 2 * 105 calls will be made to get and put.
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