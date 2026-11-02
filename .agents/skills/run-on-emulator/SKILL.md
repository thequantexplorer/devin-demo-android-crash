---
name: run-on-emulator
description: Set up the Android SDK and emulator, build the Groceries app, run it on the emulator and read crash logs. Use before reproducing a bug or testing a change in this app.
---

# Run the Groceries app on the Android emulator

The app is plain Kotlin with no AndroidX. It needs JDK 17, Android SDK platform 34 and build-tools 34.0.0.

## 1. Install the SDK (skip if `$HOME/android-sdk/emulator/emulator` exists)

```bash
export ANDROID_HOME="$HOME/android-sdk"
mkdir -p "$ANDROID_HOME/cmdline-tools"
cd "$ANDROID_HOME/cmdline-tools"
curl -fsSL https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip -o tools.zip
unzip -q tools.zip -d latest-tmp && mv latest-tmp/cmdline-tools latest && rm -rf tools.zip latest-tmp
export PATH="$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$ANDROID_HOME/emulator:$PATH"
yes | sdkmanager --licenses > /dev/null
sdkmanager "platform-tools" "build-tools;34.0.0" "platforms;android-34" "emulator" "system-images;android-34;google_apis;x86_64"
echo "no" | avdmanager create avd -n devin -k "system-images;android-34;google_apis;x86_64" --device "pixel_6"
```

In every new shell:

```bash
export ANDROID_HOME="$HOME/android-sdk"
export PATH="$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$ANDROID_HOME/emulator:$PATH"
```

## 2. Start the emulator

Start it with a window, so it shows on the desktop and in screen recordings. If `/dev/kvm` is not writable, run `sudo chmod 666 /dev/kvm` first.

```bash
DISPLAY=:0 nohup emulator -avd devin -no-audio -no-snapshot -gpu swiftshader_indirect > /tmp/emulator.log 2>&1 &
adb wait-for-device
until [ "$(adb shell getprop sys.boot_completed | tr -d '\r')" = "1" ]; do sleep 5; done
```

The first boot can take several minutes. Start it before the Gradle build so they overlap.

## 3. Build, install and open the app

```bash
./gradlew assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -W -n com.acme.groceries/.MainActivity
```

- Right after boot, `adb install` can fail with `Broken pipe` and `am start` can say the activity `does not exist`. The package manager is still starting. Wait 20 seconds and try again.
- On a slow machine a "System UI isn't responding" dialog can appear and swallow taps. Tap **Wait**, then turn these dialogs off with `adb shell settings put global hide_error_dialogs 1`. A crashing app then just closes, and the crash is still in logcat.
- The emulator can drop taps that come too quickly. Wait about 2 seconds between `adb shell input tap` calls, and check the screen (`adb exec-out screencap -p > screen.png`) before the next step.
- To start from a fresh install (the three sample items Milk, Bread and Eggs): `adb shell pm clear com.acme.groceries`.

## 4. Read crash logs

```bash
adb logcat -c                 # clear old logs before you reproduce
adb logcat -d -b crash        # print crashes after you reproduce
```

## 5. Unit tests

```bash
./gradlew testDebugUnitTest
```

Tests live in `app/src/test/java/com/acme/groceries/`. They run on the JVM, so they don't need the emulator.
Keep app logic that can crash (like `ListSummary`) in plain Kotlin objects so it can be unit tested.

## Gradle downloads

`settings.gradle.kts` lists Google's mirror of Maven Central before `mavenCentral()`, because Maven Central often returns `429 Too Many Requests` to cloud machines. Keep it there.
