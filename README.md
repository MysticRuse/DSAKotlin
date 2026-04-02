# DSAKotlin

Kotlin solutions for data structures and algorithms, grouped by pattern and problem set.

## Problem index

The full inventory (links into `src/main/kotlin`, difficulty, and summary counts) lives in **[PROBLEMS.generated.md](PROBLEMS.generated.md)**.

Regenerate it after you add or move solutions:

```bash
./gradlew generateProblemsIndex
```

Commit the updated `PROBLEMS.generated.md` when you want the repo view on GitHub to stay current.

## Running tests

```bash
./gradlew test
```

## Gradle JDK

Use **JDK 21 or 23** to run Gradle. If `JAVA_HOME` points at **JDK 25**, configuring the build can fail with `IllegalArgumentException: 25.0.1` while compiling `build.gradle.kts`. On macOS: `export JAVA_HOME=$(/usr/libexec/java_home -v 23)`, or set `org.gradle.java.home` in `gradle.properties` to a JDK 23 install. Native-access warnings from Gradle are addressed with `--enable-native-access=ALL-UNNAMED` on the Gradle JVM in `gradle.properties`.

## Tech stack

- **Language:** Kotlin 2.2
- **JDK:** 23
- **Build:** Gradle with Kotlin DSL
- **Testing:** JUnit 5
