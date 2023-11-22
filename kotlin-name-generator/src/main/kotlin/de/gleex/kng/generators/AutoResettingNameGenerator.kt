package de.gleex.kng.generators

import de.gleex.kng.api.Name
import de.gleex.kng.api.NameGenerator

/**
 * Delegates to another [NameGenerator] but always calls [reset] when there are no more [hasNext].
 */
internal class AutoResettingNameGenerator(private val delegatedGenerator: NameGenerator): NameGenerator by delegatedGenerator {
    override val isAutoResetting: Boolean = true

    override fun next(): Name {
        return delegatedGenerator.next().also {
            if(delegatedGenerator.hasNext().not()) {
                reset()
            }
        }
    }
}