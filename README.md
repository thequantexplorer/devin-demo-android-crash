# Groceries for Android

A small shopping-list app for Android, written in Kotlin.
Tick items off as you shop, then tap **Clear checked** to remove them.

- `app/src/main/java/com/acme/groceries/`: the app (`MainActivity`, `ListSummary`, `ItemStore`, `ItemCodec`)
- `app/src/test/java/com/acme/groceries/`: JVM unit tests
- `docs/bug-report.md`: an open bug report from a beta tester
- `.agents/skills/run-on-emulator/SKILL.md`: how to build and run the app on the Android emulator

```sh
./gradlew testDebugUnitTest   # unit tests
./gradlew assembleDebug       # app/build/outputs/apk/debug/app-debug.apk
```

Needs JDK 17 and the Android SDK (platform 34). GitHub Actions runs the unit tests on every push and pull request.

---

This repo is a starter for a Devin demo. See [DEVIN_DEMO.md](DEVIN_DEMO.md) for setup and the prompt.
