# ktjs-gsap

GSAP 的 Kotlin/JS 绑定 —— 核心引擎与所有随包免费发布的插件

封装覆盖整个 `gsap` 包：核心引擎、动画类层次、`gsap.utils`、全部缓动，以及所有插件（自 GSAP 3.13 起全部免费）。插件模块在被读取类对象时自行注册，与 npm 包的行为一致。

- 上游包：[`gsap`](https://github.com/greensock/GSAP) 3.15.1-kt.1
- 文档：https://shijianjs.github.io/ktjs-gsap/
- 演示：https://shijianjs.github.io/ktjs-gsap/demo/
- API：https://shijianjs.github.io/ktjs-gsap/api/
- 英文说明：[README.md](README.md)

## 用法

```kotlin
implementation("io.github.shijianjs:ktjs-gsap:3.15.1-kt.1")
```

## 构建

```bash
./gradlew build
./gradlew :ktjs-gsap:jsBrowserTest
./gradlew :demo:e2eTest
./gradlew pagesSite
```

## 许可证

wrapper 是 MIT；上游 `gsap` 的许可证不同，见文档的「许可证」页。
