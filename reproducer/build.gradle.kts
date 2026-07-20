import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    `maven-publish`
}

kotlin {
    watchosArm32()
    watchosArm64()
    watchosSimulatorArm64()
    @OptIn(ExperimentalKotlinGradlePluginApi::class) //
    applyDefaultHierarchyTemplate {
        common {
            group("watchos") { withWatchos() }
            group("x32") {
                withWatchosArm32()
            }
            group("x64") {
                // Correct configuration would be:
                //withWatchosArm64()
                //withWatchosSimulatorArm64()

                // This will add watchosArm32 to x64 _and_ x32 on accident
                group("watchos")
            }
        }
    }
}