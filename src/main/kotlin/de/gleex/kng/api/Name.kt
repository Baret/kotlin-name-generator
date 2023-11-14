package de.gleex.kng.api

/**
 * Names may be generated and combined with other names. When used you most probably just need it [asString].
 */
@JvmInline
value class Name(private val name: String) {
    /**
     * Returns this [Name] as classic [String].
     */
    fun asString(): String = name
}