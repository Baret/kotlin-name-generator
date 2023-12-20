package de.gleex.kng.architecture

import com.tngtech.archunit.core.domain.JavaMember.Predicates.declaredIn
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.constructors
import de.gleex.kng.api.NameGenerator
import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.WordSpec

private val log = KotlinLogging.logger {  }

@AnalyzeClasses(packages = ["de.gleex.kng"], importOptions = [ImportOption.DoNotIncludeTests::class])
class VisibilityTest: WordSpec() {
    @ArchTest
    val constructorsOnlyInBuilder = constructors()
        .that()
        .areDeclaredInClassesThat().implement(NameGenerator::class.java)
        .should()
        .onlyBeCalled()
        .byMethodsThat(declaredIn("de.gleex.kng.generators.NameGenerators"))
        .orShould()
        .onlyBeCalled()
        .byClassesThat().implement(NameGenerator::class.java)
        .orShould()
        .onlyBeCalled()
        .byClassesThat().haveFullyQualifiedName("de.gleex.kng.generators.ExtensionsKt")
}