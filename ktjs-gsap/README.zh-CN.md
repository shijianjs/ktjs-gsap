# ktjs-gsap

`gsap`（[上游仓库](https://github.com/greensock/GSAP)）3.15.1 的 Kotlin/JS 声明。

一个 npm 包对应一个 Kotlin 包 `io.github.shijianjs.ktjs.gsap`，按领域分子包：

| 包 | 内容 |
| --- | --- |
| `io.github.shijianjs.ktjs.gsap` | `gsap` 对象、模块导入、vars 构造器 |
| `…gsap.core` | `Animation`、`Tween`、`Timeline` 及其类对象、vars 接口 |
| `…gsap.utils` | `gsap.utils` 的取值工具 |
| `…gsap.plugins` | 全部插件：ScrollTrigger、Observer、Draggable、Flip、SplitText、MotionPath、ScrollSmoother、Inertia、MorphSVG、DrawSVG、Text、ScrambleText、ScrollTo、Pixi、Physics2D、PhysicsProps、CSSRule、Easel、GSDevTools 与 CustomEase 家族 |
| `…gsap.ease` | 缓动名称与 `EasePack` 里的缓动类 |

## 安装

```kotlin
dependencies {
    implementation("io.github.shijianjs:ktjs-gsap:3.15.1-kt.1")
}
```

## 用法

```kotlin
import io.github.shijianjs.ktjs.gsap.core.TweenTarget
import io.github.shijianjs.ktjs.gsap.core.TweenValue
import io.github.shijianjs.ktjs.gsap.gsap
import io.github.shijianjs.ktjs.gsap.tweenVars

gsap.to(
    TweenTarget(".card"),
    tweenVars {
        x = TweenValue(120.0)
        duration = 0.8
    },
)
```

## 构建与测试

```bash
./gradlew :ktjs-gsap:jsBrowserTest
```

新增或升级 npm 依赖后，**用官方源**刷新共享锁文件，避免锁文件里留下镜像地址、把 CI 带偏：

```bash
./gradlew --stop
./gradlew kotlinUpgradePackageLock
```

## 许可证

wrapper 是 MIT。上游 GSAP 使用 Standard "no charge" license，不是 MIT —— 上线前请看
[许可证页](../docs/src/jsMain/resources/docs/guide/license.md)。
