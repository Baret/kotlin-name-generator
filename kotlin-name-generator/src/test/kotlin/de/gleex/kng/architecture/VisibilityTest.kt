package de.gleex.kng.architecture

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assertTrue
import de.gleex.kng.api.NameGenerator
import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.WordSpec
import io.kotest.mpp.bestName

private val log = KotlinLogging.logger {  }

class VisibilityTest: WordSpec() {
    init {
        "Name generator implementations" should {
            "be internal" {
                log.info { "Project path: ${Konsist.projectRootPath}" }
                Konsist.scopeFromProject()
                    .print()
                    .classes()
                    .also { log.info { "Filtering ${it.size} classes for implementations of ${NameGenerator::class.bestName()}" } }
                    .filter { it.hasInterfaceWithName(NameGenerator::class.bestName()) }
                    .also { log.info { "Found ${it.size} classes implementing the interface" } }
                    .assertTrue(testName = this.testCase.name.testName) { it.hasInternalModifier }
            }
        }
    }
}