package com.example.photobook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PhotobookApplication

fun main(args: Array<String>) {
	runApplication<PhotobookApplication>(*args)
}
