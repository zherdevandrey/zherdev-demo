pluginManagement {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2")
            mavenContent { releasesOnly() }
        }
        maven {
            url = uri("https://repo1.maven.org/maven2")
            mavenContent { releasesOnly() }
        }
        maven {
            url = uri("https://repo.playbs.app/artifactory/cbdc-maven-snapshot-local")
        }
        maven {
            url = uri("https://repo.playbs.app/artifactory/cbdc-maven-release-local")
        }
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://repo1.maven.org/maven2")
            mavenContent { releasesOnly() }
        }
        maven {
            url = uri("https://plugins.gradle.org/m2")
            mavenContent { releasesOnly() }
        }
        maven {
            url = uri("https://repo.playbs.app/artifactory/cbdc-maven-snapshot-local")
            mavenContent { snapshotsOnly() }
        }
        maven {
            url = uri("https://repo.playbs.app/artifactory/cbdc-maven-release-local")
            mavenContent { releasesOnly() }
        }
    }
}

rootProject.name = "kafka-cbdc-lib-test"
