package com.houhan.library.helper

import java.util.*
import javax.validation.constraints.NotNull

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
        fun curTime(): Date {
            return Date()
        }

        /**
         * years before or after
         */
        fun addYear(@NotNull date: Date, @NotNull years: Int): Date {
            calender.time = date
            calender.add(Calendar.YEAR, years)
            return calender.time
        }

        /**
         * monthes before or after
         */
        fun addMonth(@NotNull date: Date, @NotNull monthes: Int): Date {
            calender.time = date
            calender.add(Calendar.MONTH, monthes)
            return calender.time
        }

        /**
         * weeks before or after
         */
        fun addWeek(@NotNull date: Date, @NotNull weeks: Int): Date {
            calender.time = date
            calender.add(Calendar.WEEK_OF_YEAR, weeks)
            return calender.time
        }

        /**
         * days before or after
         */
        fun addDays(@NotNull date: Date, @NotNull days: Int): Date {
            calender.time = date
            calender.add(Calendar.DATE, days)
            return calender.time
        }

    }
}
