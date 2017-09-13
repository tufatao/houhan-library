package com.houhan.library.helper

import java.util.*

/**
 * Created by Administrator on 2017/9/5.
 */
class DateUtil {
    companion object {

//        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"))
        val calender: Calendar = Calendar.getInstance()

        /**
         * 当前时间
         */
        fun curTime(): Date? {
            return Date()
        }

        /**
         * years before or after
         */
        fun addYear(date: Date, years: Int): Date? {
            calender.time = date
            calender.add(Calendar.YEAR, years)
            return calender.time
        }

        /**
         * monthes before or after
         */
        fun addMonth(date: Date, monthes: Int): Date? {
            calender.time = date
            calender.add(Calendar.MONTH, monthes)
            return calender.time
        }

        /**
         * weeks before or after
         */
        fun addWeek(date: Date, weeks: Int): Date? {
            calender.time = date
            calender.add(Calendar.WEEK_OF_YEAR, weeks)
            return calender.time
        }

        /**
         * days before or after
         */
        fun addDays(date: Date, days: Int): Date? {
            calender.time = date
            calender.add(Calendar.DATE, days)
            return calender.time
        }

    }
}
