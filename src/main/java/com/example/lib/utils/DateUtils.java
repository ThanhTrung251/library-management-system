package com.example.lib.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {

    public static LocalDate getMonDayOfTheWeek() {
        return LocalDate.now().with(DayOfWeek.MONDAY);
    }

    public static LocalDate getSunDayOfTheWeek() {
        return LocalDate.now().with(DayOfWeek.SUNDAY);
    }
}
