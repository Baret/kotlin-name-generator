package de.gleex.kng.api

/**
 * Generates an either endless or finite amount of names.
 */
interface NameGenerator: Iterator<Name> {
    /**
     * Generates the next [Name], if possible.
     *
     * @throws NameGeneratorExhaustedException when this is a finite generator and the last
     *      [Name] was generated with the previous call to next()
     */
    override fun next(): Name

    /**
     * @return true if this generator is able to generate another [Name]. This means, that it
     * is safe to call [next].
     */
    override fun hasNext(): Boolean

    /**
     * Resets this name generator to its initial state. Calling [next] afterward may return the same
     * order of [Names][Name] as the previous times. But the contract does not require such behavior.
     */
    fun reset()
}