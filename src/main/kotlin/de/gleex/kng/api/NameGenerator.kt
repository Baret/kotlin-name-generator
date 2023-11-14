package de.gleex.kng.api

/**
 * Generates an either endless or finite amount of names.
 */
interface NameGenerator {
    fun next(): Name
}