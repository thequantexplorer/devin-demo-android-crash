# Bug: app closes when I clear my list

**Reported by:** Priya (beta tester)
**App version:** Groceries 1.4.0 (versionCode 14)
**Device:** Android 14 (API 34)
**How often:** Every time

## What happened

I ticked off everything on my list at the store. Then I tapped **Clear checked** and the app closed straight away.
When I opened it again, my old items were still there, all ticked.

## Steps to reproduce

1. Install the app and open it. It starts with three sample items: Milk, Bread and Eggs.
2. Tap each item so all three are checked.
3. Tap **Clear checked**.

## Expected

The checked items are removed and I see an empty list.

## Actual

The app crashes. Android shows "Groceries keeps stopping" (or the app just closes).

## Logcat

Captured with `adb logcat -b crash` right after the crash:

```text
--------- beginning of crash
11-02 00:32:14.886  4215  4215 E AndroidRuntime: FATAL EXCEPTION: main
11-02 00:32:14.886  4215  4215 E AndroidRuntime: Process: com.acme.groceries, PID: 4215
11-02 00:32:14.886  4215  4215 E AndroidRuntime: java.util.NoSuchElementException
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at com.acme.groceries.ListSummary.headline(ListSummary.kt:18)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at com.acme.groceries.MainActivity.render(MainActivity.kt:72)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at com.acme.groceries.MainActivity.saveAndRender(MainActivity.kt:68)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at com.acme.groceries.MainActivity.onCreate$lambda$3(MainActivity.kt:52)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at com.acme.groceries.MainActivity.$r8$lambda$azTWBaiNBo7hPdLTWGIEv42-HVU(Unknown Source:0)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at com.acme.groceries.MainActivity$$ExternalSyntheticLambda3.onClick(D8$$SyntheticClass:0)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at android.view.View.performClick(View.java:7659)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at android.view.View.performClickInternal(View.java:7636)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at android.view.View.-$$Nest$mperformClickInternal(Unknown Source:0)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at android.view.View$PerformClick.run(View.java:30156)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at android.os.Handler.handleCallback(Handler.java:958)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at android.os.Handler.dispatchMessage(Handler.java:99)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at android.os.Looper.loopOnce(Looper.java:205)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at android.os.Looper.loop(Looper.java:294)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at android.app.ActivityThread.main(ActivityThread.java:8177)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at java.lang.reflect.Method.invoke(Native Method)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:552)
11-02 00:32:14.886  4215  4215 E AndroidRuntime: 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:971)
```
