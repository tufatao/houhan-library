package com.houhan.library

import org.springframework.boot.SpringApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
//@SpringBootApplication
class HouhanLibraryAPIApplication

fun main(args: Array<String>) {
    SpringApplication.run(HouhanLibraryAPIApplication::class.java, *args)
}
