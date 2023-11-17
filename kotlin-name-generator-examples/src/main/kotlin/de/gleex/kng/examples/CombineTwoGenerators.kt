package de.gleex.kng.examples

import de.gleex.kng.api.NameGenerator
import de.gleex.kng.generators.nameGenerator
import de.gleex.kng.generators.plus
import de.gleex.kng.wordlist.asWordList

/**
 * A simple example showing how to combine two finite name generators
 */
fun main() {
    val firstWordList = listOf("Alpha", "Bravo", "Charlie").asWordList()
    val secondWordList = listOf("1", "2").asWordList()

    val natoAlphabet = nameGenerator(firstWordList)
    val numbers = nameGenerator(secondWordList)

    val squadNames: NameGenerator = natoAlphabet + numbers

    val combinationCount = firstWordList.size * secondWordList.size
    println("Generating all $combinationCount names:")
    for(name in squadNames) {
        println("\t${name.asString()}")
    }
    println("The next invocation of next() will throw an exception")
    println("You should not be able to read this: ${squadNames.next().asString()}")
}