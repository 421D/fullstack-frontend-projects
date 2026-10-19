# Daily Finance News App（今日理财资讯）

An Android app that shows a list of finance news items and lets the user add their own. Written in Java.

## Features

- **Splash screen:** a full-screen image for 3 seconds, then the main screen opens.
- **News list:** each item shows a title, a summary and an image. Six sample items are loaded on first launch.
- **Add an item:** type a title and content at the top of the screen and tap the button; the list refreshes immediately.
- **Local storage:** items are saved in an on-device SQLite database, so they stay after the app is closed.

## How it works

| File | Role |
|---|---|
| `SplahActivity.java` | Launcher activity; uses a `Handler` to open the main screen after 3 seconds |
| `MainActivity.java` | Reads items from the database and binds them to a `ListView` through a `SimpleAdapter` |
| `DayData.java` | `SQLiteOpenHelper` that creates the table and handles inserts and queries |
| `XianshiActivity.java` | Detail screen layout for a single item |
| `res/layout/` | XML layouts for the splash, main list, list item and detail screens |

## Stack

Java, Android SDK, Android Support Library (AppCompat), SQLite, `ListView` / `SimpleAdapter`

## Run it

Open this folder in Android Studio and let it sync, then run on an emulator or device (Android 5.0+).

The original Gradle files were lost. The build files here were added afterwards to match the code (Support Library 28, Android Gradle Plugin 4.2) and have not been test-built.

## Layout

```
app/src/main/
  AndroidManifest.xml
  java/com/example/administrator/myapplication/   activities and database helper
  res/                                             layouts, images, icons, values
```
