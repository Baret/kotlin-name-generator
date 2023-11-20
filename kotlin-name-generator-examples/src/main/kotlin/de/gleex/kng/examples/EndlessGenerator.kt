package de.gleex.kng.examples

import de.gleex.kng.generators.nameGenerator
import de.gleex.kng.generators.nextAsString
import de.gleex.kng.wordlist.wordListOf

fun main() {
    val words = wordListOf("one", "two", "three")
    val endlessGenerator = nameGenerator(words) {
        autoReset = true
    }

    repeat(6) {
        println("$it: ${endlessGenerator.next()}")
    }

    println("${endlessGenerator.nextAsString() == "one"}")
    println("${endlessGenerator.nextAsString() == "two"}")
    println("${endlessGenerator.nextAsString() == "three"}")

    println("${endlessGenerator.nextAsString() == "one"}")
    println("${endlessGenerator.nextAsString() == "two"}")
    println("${endlessGenerator.nextAsString() == "three"}")
}