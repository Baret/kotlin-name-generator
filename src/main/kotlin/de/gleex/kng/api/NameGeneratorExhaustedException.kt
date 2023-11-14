package de.gleex.kng.api

/**
 * This exception is thrown, when a [NameGenerator] is unable to generate another [Name].
 */
object NameGeneratorExhaustedException: Throwable("Name generator is unable to generate any more names") {
    // needed for serialization
    private fun readResolve(): Any = NameGeneratorExhaustedException
}