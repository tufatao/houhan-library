package com.houhan.library

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@SpringBootApplication
class HouhanLibraryApp

fun main(args: Array<String>) {
    SpringApplication.run(HouhanLibraryApp::class.java, *args)
}
