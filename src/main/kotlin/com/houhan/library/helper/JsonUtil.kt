package com.houhan.library.helper


import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper

import java.io.IOException

/**
 * @author tufatao
 * *
 * @version V 0.1
 * *
 * @describe {}
 * *
 * @time 2017/8/9 16:10.
 */
object JsonUtil {
    private val MAPPER = ObjectMapper()

    /**
     * 配置ObjectMapper
     * https://stackoverflow.com/questions/3907929/should-i-declare-jacksons-objectmapper-as-a-static-field

     * @return ObjectMapper
     */
    init {
        MAPPER.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true)
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    fun obj2Json(obj: Any): String? {
        try {
            return MAPPER.writeValueAsString(obj)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }

        return null
    }

    fun <T> json2Obj(json: String, clazz: Class<T>): T? {
        try {
            return MAPPER.readValue(json, clazz)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }
}
