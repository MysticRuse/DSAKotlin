package com.mr.problemsets.set_1

import kotlin.test.Test
import kotlin.test.assertEquals

class MinMeetingRoomsTest {

    @Test
    fun `example case - two overlapping meetings`() {
        val meetings = listOf(
            Meeting(0, 30),
            Meeting(5, 10),
            Meeting(15, 20)
        )
        assertEquals(2, minMeetingRooms(meetings))
    }

    @Test
    fun `no overlap - single room needed`() {
        val meetings = listOf(
            Meeting(0, 5),
            Meeting(5, 10),
            Meeting(10, 15)
        )
        assertEquals(1, minMeetingRooms(meetings))
    }

    @Test
    fun `all meetings overlap - need n rooms`() {
        val meetings = listOf(
            Meeting(1, 10),
            Meeting(2, 9),
            Meeting(3, 8)
        )
        assertEquals(3, minMeetingRooms(meetings))
    }

    @Test
    fun `single meeting`() {
        val meetings = listOf(Meeting(0, 10))
        assertEquals(1, minMeetingRooms(meetings))
    }

    @Test
    fun `empty list`() {
        assertEquals(0, minMeetingRooms(emptyList()))
    }

    @Test
    fun `meetings end exactly when others start`() {
        val meetings = listOf(
            Meeting(0, 10),
            Meeting(10, 20),
            Meeting(20, 30)
        )
        assertEquals(1, minMeetingRooms(meetings))
    }

    @Test
    fun `partial overlaps`() {
        val meetings = listOf(
            Meeting(0, 10),
            Meeting(5, 15),
            Meeting(10, 20)
        )
        // At time 5-10: meetings 0 and 1 overlap (2 rooms)
        // At time 10-15: meetings 1 and 2 overlap (2 rooms)
        assertEquals(2, minMeetingRooms(meetings))
    }

    @Test
    fun `complex scenario`() {
        val meetings = listOf(
            Meeting(1, 5),
            Meeting(2, 6),
            Meeting(3, 7),
            Meeting(4, 8),
            Meeting(10, 12)
        )
        // At time 4: all first 4 meetings overlap
        assertEquals(4, minMeetingRooms(meetings))
    }

    @Test
    fun `same start and end times`() {
        val meetings = listOf(
            Meeting(0, 10),
            Meeting(0, 10),
            Meeting(0, 10)
        )
        assertEquals(3, minMeetingRooms(meetings))
    }

    @Test
    fun `large time values`() {
        val meetings = listOf(
            Meeting(0, 1_000_000_000),
            Meeting(500_000_000, 1_000_000_000)
        )
        assertEquals(2, minMeetingRooms(meetings))
    }

    @Test
    fun `sequential non-overlapping`() {
        val meetings = listOf(
            Meeting(1, 2),
            Meeting(3, 4),
            Meeting(5, 6),
            Meeting(7, 8)
        )
        assertEquals(1, minMeetingRooms(meetings))
    }

    @Test
    fun `peak at different times`() {
        val meetings = listOf(
            Meeting(0, 5),
            Meeting(1, 3),
            Meeting(4, 8),
            Meeting(6, 10),
            Meeting(7, 9)
        )
        // Peak at time 7-8: meetings (4,8), (6,10), (7,9) = 3 rooms
        assertEquals(3, minMeetingRooms(meetings))
    }
}