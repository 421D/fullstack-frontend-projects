# Full-Stack & Front-End Projects

WeChat Mini Programs, a Java desktop system, an Android app and a website, from static pages to a cloud-backed mini program.

| Project | What it is | Stack | Highlight |
|---|---|---|---|
| [Community Service Mini Program](wechat-miniprograms/community-service-miniprogram/) | Second-hand marketplace plus property services for a residential community | WeChat Mini Program, Cloud Functions, cloud database, cloud storage | Serverless backend: 8 cloud functions for listings, orders, stock and repair requests |
| [PM2.5 Studio Mini Program](wechat-miniprograms/pm25-studio-miniprogram/) | Showcase app for a digital media studio: works by category, articles and comments | WXML, WXSS, JavaScript, REST API | Content loaded from a hosted shop and CMS backend |
| [Hotel Management System](java-projects/hotel-management-system/) | Desktop app for hotel guest records | Java Swing, RMI, JDBC, SQL Server | Login server issues expiring session tokens; every RMI call is token-checked |
| [Daily Finance News App](android-projects/daily-news-app/) | Android app for browsing and adding finance items | Java, Android SDK, SQLite | Splash screen, list with user-added items, on-device SQLite storage |
| [YiCha Tea Website](web-projects/yicha-tea-website/) | Tea shop brand site, built as a static site and then as DedeCMS templates | HTML, CSS, JavaScript, jQuery, DedeCMS | Interactive cart and carousel; same design rebuilt as CMS templates |

## Repository layout

```
wechat-miniprograms/   community-service-miniprogram, pm25-studio-miniprogram
java-projects/         hotel-management-system
android-projects/      daily-news-app
web-projects/          yicha-tea-website (static/, cms-templates/)
```

Each project folder has its own README.
