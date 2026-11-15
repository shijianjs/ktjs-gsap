# ktjs-gsap

Kotlin/JS declarations for [`gsap`](https://github.com/greensock/GSAP) 3.15.1.

One npm package maps to one Kotlin package, `io.github.shijianjs.ktjs.gsap`, with per-area
sub-packages:

| Package | Contents |
| --- | --- |
| `io.github.shijianjs.ktjs.gsap` | the `gsap` object, the module import, the vars builders |
| `…gsap.core` | `Animation`, `Tween`, `Timeline`, their class objects, the vars interfaces |
| `…gsap.utils` | `gsap.utils` value helpers |
| `…gsap.plugins` | every plugin: ScrollTrigger, Observer, Draggable, Flip, SplitText, MotionPath, ScrollSmoother, Inertia, MorphSVG, DrawSVG, Text, ScrambleText, ScrollTo, Pixi, Physics2D, PhysicsProps, CSSRule, Easel, GSDevTools, and the CustomEase family |
| `…gsap.ease` | the easing names and the `EasePack` classes |

## Installation

```kotlin
dependencies {
    implementation("io.github.shijianjs:ktjs-gsap:3.15.1-kt.1")
}
```

## Usage

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

## Build and test

```bash
./gradlew :ktjs-gsap:jsBrowserTest
```

After adding or upgrading an npm dependency, refresh the shared lock file **against the official
registry**, so the `resolved` URLs CI uses are not mirror URLs:

```bash
./gradlew --stop
./gradlew kotlinUpgradePackageLock
```

## License

The wrapper is MIT. The upstream GSAP license is the Standard "no charge" license, not MIT —
read the [license page](../docs/src/jsMain/resources/docs/guide/license.md) before shipping.
