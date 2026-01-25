package org.interview.java.core.oops.encapsulation;

import java.util.Date;

// making a variable private final only saves the reference from being changed but the object itself can still be modified if it's mutable.
// example: date, list, map, set etc.
public class DefensiveCoping {

    private final Date date;


    public DefensiveCoping(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return new Date(date.getTime()); // returning a new Date object to prevent external modification
    }
}
