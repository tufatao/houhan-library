package com.houhan.library

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@SpringBootApplication
class HouhanLibraryApplication

fun main(args: Array<String>) {
    SpringApplication.run(HouhanLibraryApplication::class.java, *args)
}
