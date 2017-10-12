package com.houhan.library.helper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author tufatao
 * @version V 0.1
 * @describe {}
 * @time 2017/8/14 14:58.
 */
object DateFormatUtil {
    private var simpleDateFormat: SimpleDateFormat? = null

    private val DATE_PATTERN_1 = "yyyy-MM-dd"
    private val DATE_PATTERN_2 = "yyyy-MM-dd HH:mm:ss"
    private val DATE_PATTERN_3 = "yyyy年MM月dd日"
    private val DATE_PATTERN_4 = "yyyy年MM月dd日 HH时mm分ss秒"

    fun strStyle1(date: Date): String {
        return date2String(date, DATE_PATTERN_1)
    }

    fun strStyle2(date: Date): String {
        return date2String(date, DATE_PATTERN_2)
    }

    fun strStyle3(date: Date): String {
        return date2String(date, DATE_PATTERN_3)
    }

    fun strStyle4(date: Date): String {
        return date2String(date, DATE_PATTERN_4)
    }

    fun dateStyle1(dateStr: String): Date? {
        return string2Date(dateStr, DATE_PATTERN_1)
    }

    fun dateStyle2(dateStr: String): Date? {
        return string2Date(dateStr, DATE_PATTERN_2)
    }

    fun dateStyle3(dateStr: String): Date? {
        return string2Date(dateStr, DATE_PATTERN_3)
    }

    fun dateStyle4(dateStr: String): Date? {
        return string2Date(dateStr, DATE_PATTERN_4)
    }

    /**
     * @param date
     * *
     * @param datePattern
     * *
     * @return
     */
    private fun date2String(date: Date, datePattern: String): String {
        simpleDateFormat = SimpleDateFormat(datePattern)
        return simpleDateFormat!!.format(date)
    }

    /**
     * @param dateStr
     * *
     * @param datePattern
     * *
     * @return
     */
    private fun string2Date(dateStr: String, datePattern: String): Date? {
        simpleDateFormat = SimpleDateFormat(datePattern)
        try {
            return simpleDateFormat!!.parse(dateStr)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }
}
