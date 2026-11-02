# Devin demo: Fix an Android crash from a bug report

**What it shows:** A beta tester says the Groceries app closes when they clear their list. Devin starts an Android emulator on its own machine, reproduces the crash and records it, fixes it, adds a test, and records the fixed app.

**Needs:** GitHub. Nothing to install: the emulator runs on Devin's machine.

More demos: [devin-advanced-demos](https://github.com/thequantexplorer/devin-advanced-demos)

## Set it up

1. Click **Use this template → Create a new repository**, choose your GitHub account or org as the owner, and keep the name `devin-demo-android-crash`. The links and prompt below assume that name. Use the template rather than a fork: pull requests on a fork go to the original repo by default.
2. In Devin, open [**Settings → Connections → GitHub**](https://app.devin.ai/settings/connections/github) and make sure Devin can access the new repository.

## Prompt

Start a new Devin session at [app.devin.ai](https://app.devin.ai) and paste this. Replace `<you>` with your GitHub user or org.

```text
A beta tester reports that our Groceries Android app crashes when they clear their list. The bug report and the logcat stack trace are in docs/bug-report.md in https://github.com/<you>/devin-demo-android-crash.

First reproduce it. Set up an Android emulator and run the app on it (the repo skill run-on-emulator explains how). Record a video of the crash on the emulator, following the steps in the bug report, and check that logcat shows the same stack trace.

Then find the root cause and fix it, so an empty list shows a friendly message instead of crashing. Add a unit test that fails before the fix and passes after it, and run ./gradlew testDebugUnitTest.

Finally, record a second video on the emulator showing the same steps working after the fix. Open a PR that explains the root cause, includes the new test and the test output, and describes both recordings. Send me both recordings here when you are done.
```

## What to watch

- Open the **Desktop** tab to watch Devin tap through the app in the Android emulator.
- It records the crash before it changes any code.
- The first run takes a few extra minutes while Devin downloads the Android SDK and boots the emulator.

## You're done when

- You have two videos: the crash, and the same steps working after the fix.
- The PR has the fix and a unit test for the empty list, and the **CI** check on the PR is green.

Optional: ask Devin to save its Android setup to the repo's environment blueprint, so later sessions start with the emulator already installed. Devin shows a card for you to approve before it changes anything.
