pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Planet Listing App"
include(":app")
include(":core")
include(":feature-planet-list")
include(":feature-planet-detail")
include(":util")
include(":common-ui")
include(":datasource-local")
include(":datasource-remote")
include(":benchmark")
