package com.wa2.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class DemoApplication{
//    @Bean
//    fun passwordEncoder() : PasswordEncoder {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
//    }
}


fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
