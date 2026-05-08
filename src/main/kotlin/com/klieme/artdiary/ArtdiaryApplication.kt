package com.klieme.artdiary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ArtdiaryApplication

fun main(args: Array<String>) {
	runApplication<ArtdiaryApplication>(*args)
}
