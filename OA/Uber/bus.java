// Given an array of strings 'schedule' that represents the schedule of bus
// departure 'times'
// and a string time that represents the current time, find out
// how many minutes ago the last bus left. If the first bus for the day has yet
// to leave, return '-1'.

// Time is represented as a string in the form of HH:mm (in the 24-hour format).
// Bus departure times are sorted in chronological order.

// Please assume that if a bus is scheduled to leave at the current time, it
// hasn't left yet.

// Note: You are not expected to provide the most optimal solution, but a
// solution with time complexity not worse than O(schedule.length *
// MINUTES_IN_DAY) will fit within the execution time limit.

// Example

// For schedule = ["12:30", "14:00", "19:55"] and time = "14:30", the output
// should be eldestson(schedule, time) = 30.

class Solution {

    // O(n) time
    public int busTimeOn(String[] schedule, String slot) {

        int slotTime = Integer.parseInt(slot.substring(0, 2)) * 60 + Integer.parseInt(slot.substring(3));
        int lastBus = -1;

        for (int i = 0; i < schedule.length; i++) {
            int busTime = Integer.parseInt(schedule[i].substring(0, 2)) * 60
                    + Integer.parseInt(schedule[i].substring(3));
            if (busTime > slotTime) {
                break;
            }
            lastBus = busTime;
        }

        return lastBus == -1 ? -1 : slotTime - lastBus;
    }

    // O(logn) time
    public int busTimeLogN(String[] schedule, String slot) {

        int slotTime = Integer.parseInt(slot.substring(0, 2)) * 60 + Integer.parseInt(slot.substring(3));
        int lastBus = -1;

        int left = 0;
        int right = schedule.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int busTime = Integer.parseInt(schedule[mid].substring(0, 2)) * 60
                    + Integer.parseInt(schedule[mid].substring(3));
            if (busTime <= slotTime) {
                lastBus = busTime;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return lastBus == -1 ? -1 : slotTime - lastBus;
    }

}