package DataStructure.Design;

import java.util.*;

class Event {
    int start;
    int end;

    Event(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class MyCalendarTwo {
    List<Event> bookings;
    List<Event> overlappedBookings;

    public MyCalendarTwo() {
        this.bookings = new ArrayList<Event>();
        this.overlappedBookings = new ArrayList<Event>();
    }

    public boolean book(int start, int end) {
        for (Event booking : this.overlappedBookings) {
            if (isOverlapped(booking, start, end)) {
                return false;
            }
        }

        for (Event booking : this.bookings) {
            if (isOverlapped(booking, start, end)) {
                this.overlappedBookings.add(new Event(
                        Math.max(booking.start, start), Math.min(booking.end, end)));
            }
        }
        this.bookings.add(new Event(start, end));
        return true;
    }

    private boolean isOverlapped(Event booking, int start, int end) {
        return booking.start < end && start < booking.end;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */