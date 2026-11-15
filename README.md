# ktjs-gsap

Kotlin/JS bindings for GSAP — the core engine and every plugin that ships free

The wrapper covers the whole `gsap` package: the core engine, the animation class hierarchy, `gsap.utils`, every easing, and all plugins (all of them free since GSAP 3.13). Plugin modules register themselves when their class object is read, exactly like the npm package.

- Upstream package: [`gsap`](https://github.com/greensock/GSAP) 3.15.1-kt.1
- Docs: https://shijianjs.github.io/ktjs-gsap/
- Demo: https://shijianjs.github.io/ktjs-gsap/demo/
- API: https://shijianjs.github.io/ktjs-gsap/api/
- 中文说明：[README.zh-CN.md](README.zh-CN.md)

## Usage

```kotlin
implementation("io.github.shijianjs:ktjs-gsap:3.15.1-kt.1")
```

## Build

```bash
./gradlew build
./gradlew :ktjs-gsap:jsBrowserTest
./gradlew :demo:e2eTest
./gradlew pagesSite
```

## License

The wrapper is MIT; the upstream `gsap` license differs — see the "License" page of the docs.
