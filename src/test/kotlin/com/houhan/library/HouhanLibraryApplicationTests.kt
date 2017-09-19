package com.houhan.library

import com.houhan.library.helper.JsonUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class HouhanLibraryApplicationTests {
    val log: Logger = LoggerFactory.getLogger(HouhanLibraryApplicationTests::class.java)

    @Test
    fun testLet() {
        val hehe = ""
        hehe?.let {
            println("不为空")
        } ?: println("take care, NPE")
    }

    @Test
    fun testReturn() {
        val traces = Thread.currentThread().stackTrace
        println(JsonUtil.obj2Json(traces))
    }
}
