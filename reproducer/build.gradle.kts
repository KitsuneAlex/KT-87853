import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    `maven-publish`
}

kotlin {
    listOf(
        macosArm64(),
        iosArm64(),
        iosSimulatorArm64(),
        watchosArm32(),
        watchosArm64(),
        watchosSimulatorArm64()
    ).forEach { target ->
        target.compilations {
            named("main") {
                cinterops {
                    create("test")
                }
            }
        }
    }
    @OptIn(ExperimentalKotlinGradlePluginApi::class) //
    applyDefaultHierarchyTemplate {
        common {
            group("macos") { withMacos() }
            group("ios") { withIos() }
            group("watchos") { withWatchos() }
            group("x32") {
                withWatchosArm32()
            }
            group("x64") {
                group("macos")
                group("ios")

                // Correct configuration would be:
                //withWatchosArm64()
                //withWatchosSimulatorArm64()

                // This will add watchosArm32 to x64 _and_ x32 on accident
                group("watchos")
            }
        }
    }
}