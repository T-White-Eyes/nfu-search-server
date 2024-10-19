object Version {
	const val KOTLIN_VERSION = "1.9.25"
	const val NETFLIX_EUREKA_CLIENT_VERSION = "4.1.3"
	const val SPRING_CLOUD_CONFIG_VERSION = "4.1.3"
	const val SPRING_BOOT_VERSION = "3.3.3"
	const val JACKSON_MODULE_VERSION = "2.17.2"
	const val MARIADB_JAVA_CLIENT_VERSION = "3.4.1"
}

plugins {
	val KOTLIN_VERSION = "1.9.25"
	val SPRING_PLUGIN_VERSION = "1.9.25"
	val SPRING_BOOT_VERSION = "3.3.3"
	val SPRING_BOOT_DEPENDENCY_MANAGEMENT_VERSION = "1.1.6"

	kotlin("jvm") version KOTLIN_VERSION
	kotlin("plugin.jpa") version KOTLIN_VERSION
	kotlin("plugin.spring") version SPRING_PLUGIN_VERSION
	kotlin("kapt") version KOTLIN_VERSION
	id("org.springframework.boot") version SPRING_BOOT_VERSION
	id("io.spring.dependency-management") version SPRING_BOOT_DEPENDENCY_MANAGEMENT_VERSION
}

group = "com.nfu"
version = "0.0.1"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.3"

dependencies {
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Version.JACKSON_MODULE_VERSION}")
	implementation("org.jetbrains.kotlin:kotlin-reflect:${Version.KOTLIN_VERSION}")

	implementation("org.springframework.boot:spring-boot-starter-web:${Version.SPRING_BOOT_VERSION}")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:${Version.SPRING_BOOT_VERSION}")
	implementation("org.springframework.cloud:spring-cloud-starter-config:${Version.SPRING_CLOUD_CONFIG_VERSION}")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:${Version.NETFLIX_EUREKA_CLIENT_VERSION}")

	implementation("org.mariadb.jdbc:mariadb-java-client:${Version.MARIADB_JAVA_CLIENT_VERSION}")

	testImplementation("org.springframework.boot:spring-boot-starter-test:${Version.SPRING_BOOT_VERSION}")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
