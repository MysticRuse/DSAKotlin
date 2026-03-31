package com.mr.problemsets.set_1

/**
 * ✅ Problem 4 (Medium/Hard) — Minimum Meeting Rooms
 * You are given n meeting intervals where each meeting has a start time and end time.
 * Find the minimum number of meeting rooms required.
 * Input
 * First line: n
 * Next n lines: start end
 * Output
 * Print the minimum rooms required.
 * Constraints
 * 1 ≤ n ≤ 200000
 * 0 ≤ start < end ≤ 10^9
 * Example
 * Input
 * 3
 * 0 30
 * 5 10
 * 15 20
 * Output
 * 2
 */


data class Meeting(val start: Int, val end: Int)

/**
 * Finds the minimum number of meeting rooms required to schedule all meetings.
 *
 * @param meetings List of meetings with start and end times
 * @return Minimum number of rooms required
 */
fun minMeetingRooms(meetings: List<Meeting>): Int {
   //return minMeetingRoomsEventSweepLine(meetings)
    return minMeetingRoomsTwoSortedArrays(meetings)
}

fun minMeetingRoomsTwoSortedArrays(meetings: List<Meeting>): Int {
    if (meetings.isEmpty()) return 0

    val startTimes = meetings.map { it.start }.sorted()
    val endTimes = meetings.map { it.end }.sorted()

    var rooms = 0
    var endPtr = 0

   for (start in startTimes) {
       if (start < endTimes[endPtr]) rooms++
       else endPtr++
   }

    return rooms
}

fun minMeetingRoomsEventSweepLine(meetings: List<Meeting>): Int {
    if (meetings.isEmpty()) return 0

    // Create events: +1 for start, -1 for end
    val events = mutableListOf<Pair<Int, Int>>()
    for (meeting in meetings) {
        events.add(meeting.start to 1)   // meeting starts
        events.add(meeting.end to -1)    // meeting ends
    }

    // Sort by time; if same time, process ends (-1) before starts (+1)
    events.sortWith(compareBy({ it.first }, { it.second }))

    var currentRooms = 0
    var maxRooms = 0

    for ((_, delta) in events) {
        currentRooms += delta
        maxRooms = maxOf(maxRooms, currentRooms)
    }

    return maxRooms
}

fun main() {
    val n = readln().toInt()
    val meetings = List(n) {
        val (start, end) = readln().split(" ").map { it.toInt() }
        Meeting(start, end)
    }
    println(minMeetingRooms(meetings))
}