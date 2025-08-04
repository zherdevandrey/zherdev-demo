plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "ru.bs.cbdc"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

//repositories {
//	mavenCentral()
//}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("org.springframework.kafka:spring-kafka:3.2.0")
	implementation("ru.bs.cbdc.libs:logging:0.0.1")
	implementation("ru.bs.cbdc.libs:spring-util:0.0.1")
	implementation("ru.bs.cbdc.libs:util:0.0.1")
	implementation("ru.bs.cbdc.libs:api:0.0.1")
	implementation("ru.bs.cbdc.libs:kafka:0.0.1")


}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}
