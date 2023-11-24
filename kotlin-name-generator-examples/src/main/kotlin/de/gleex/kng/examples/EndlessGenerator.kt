package de.gleex.kng.examples

import de.gleex.kng.generators.nameGenerator
import de.gleex.kng.generators.nextAsString
import de.gleex.kng.wordlist.wordListOf

/**
 * This example shows the auto-resetting feature. A name generator may be auto-resetting which
 * makes it produce an endless stream of values. Be careful when iterating over endless generators!
 */
fun main() {
    val words = wordListOf("one", "two", "three")
    val endlessGenerator = nameGenerator(words) {
        autoReset = true
    }

    println("This name generator is infinite: ${endlessGenerator.isAutoResetting}")

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