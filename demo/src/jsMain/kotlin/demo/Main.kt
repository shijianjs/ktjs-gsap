package demo

import io.github.shijianjs.ktjs.gsap.core.EaseLike
import io.github.shijianjs.ktjs.gsap.core.Position
import io.github.shijianjs.ktjs.gsap.core.TweenTarget
import io.github.shijianjs.ktjs.gsap.core.TweenValue
import io.github.shijianjs.ktjs.gsap.gsap
import io.github.shijianjs.ktjs.gsap.timelineVars
import io.github.shijianjs.ktjs.gsap.tweenVars
import kotlin.js.JsAny
import kotlin.js.unsafeCast
import web.cssom.ClassName
import web.dom.document
import web.events.EventHandler
import web.html.HTMLElement
import web.html.HtmlTagName

private const val PROJECT_NAME = "ktjs-gsap"

private val texts = Texts(
    mapOf<String, Pair<String, String>>(
        "h1" to ("$PROJECT_NAME demo" to "$PROJECT_NAME 演示"),
        "intro" to Pair(
            "Every animation below is a real GSAP tween created through the Kotlin/JS wrapper: a " +
                "timeline with staggered children, and one tween whose easing comes from the select.",
            "下面每个动画都是通过 Kotlin/JS wrapper 创建的真实 GSAP 补间：一条带错开子项的时间线，" +
                "以及一条缓动由下拉框决定的补间。",
        ),
        "timeline" to ("Timeline with a stagger" to "带错开的时间线"),
        "easeDemo" to ("Single tween, easing from the select" to "单条补间，缓动取自下拉框"),
        "play" to ("Play" to "播放"),
        "reset" to ("Reset" to "重置"),
        "ease" to ("Easing" to "缓动"),
        "version" to ("Version" to "版本"),
    ),
)

/** The easing names the demo offers; all of them ship with `gsap`. */
private val easingNames = listOf("power2.out", "back.out", "elastic.out", "bounce.out", "circ.out")

/**
 * Entry point of the demo page.
 *
 * Nodes are built with `document.createElement` and mutated in place — no HTML strings, no
 * `innerHTML` (see the UI conventions in AGENTS.md).
 */
fun main() {
    val root = document.createElement(HtmlTagName.div).apply { className = ClassName("page") }
    val heading = document.createElement(HtmlTagName.h1)
    val intro = document.createElement(HtmlTagName.p).apply { className = ClassName("hint") }
    val version = document.createElement(HtmlTagName.p).apply { className = ClassName("version") }

    val logo = document.createElement(HtmlTagName.img).apply {
        className = ClassName("logo")
        setAttribute("src", "img/$PROJECT_NAME-logo.png")
        setAttribute("alt", "$PROJECT_NAME logo")
    }

    val header = document.createElement(HtmlTagName.header).apply {
        className = ClassName("header")
        append(logo, heading, languageSwitch(texts))
    }

    // --- Section 1: a timeline with staggered children ------------------------------------
    val timelineTitle = document.createElement(HtmlTagName.h2)
    val stage = document.createElement(HtmlTagName.div).apply { className = ClassName("stage") }
    val boxes = (1..5).map { index ->
        document.createElement(HtmlTagName.div).apply {
            className = ClassName("box")
            textContent = index.toString()
            stage.append(this)
        }
    }

    val timelinePlay = document.createElement(HtmlTagName.button).apply { setAttribute("type", "button") }
    val timelineReset = document.createElement(HtmlTagName.button).apply { setAttribute("type", "button") }

    timelinePlay.onclick = EventHandler {
        gsap.killTweensOf(TweenTarget(".box"), null, null)
        boxes.forEach { box ->
            box.style.transform = ""
            box.style.opacity = "1"
        }
        val timeline = gsap.timeline(timelineVars { paused = true })
        boxes.forEachIndexed { index, box ->
            timeline.from(
                TweenTarget(box.js()),
                tweenVars {
                    y = TweenValue(120.0)
                    opacity = TweenValue(0.0)
                    duration = 0.5
                    ease = EaseLike("back.out")
                },
                Position(index * 0.15),
            )
        }
        timeline.play(null, null)
        Unit
    }

    timelineReset.onclick = EventHandler {
        gsap.killTweensOf(TweenTarget(".box"), null, null)
        boxes.forEach { box ->
            box.style.transform = ""
            box.style.opacity = "1"
        }
    }

    val timelineRow = document.createElement(HtmlTagName.div).apply {
        className = ClassName("row")
        append(timelinePlay, timelineReset)
    }

    // --- Section 2: one tween, easing picked from the select --------------------------------
    val easingTitle = document.createElement(HtmlTagName.h2)
    val easeStage = document.createElement(HtmlTagName.div).apply { className = ClassName("stage") }
    val dot = document.createElement(HtmlTagName.div).apply {
        className = ClassName("dot")
        easeStage.append(this)
    }

    val easingLabel = document.createElement(HtmlTagName.label).apply { className = ClassName("label") }
    val easingSelect = document.createElement(HtmlTagName.select).apply { className = ClassName("select") }
    easingNames.forEach { name ->
        easingSelect.append(
            document.createElement(HtmlTagName.option).apply {
                value = name
                textContent = name
            },
        )
    }

    val easingPlay = document.createElement(HtmlTagName.button).apply { setAttribute("type", "button") }

    fun playDot() {
        gsap.killTweensOf(TweenTarget(".dot"), null, null)
        gsap.set(TweenTarget(".dot"), tweenVars { x = TweenValue(0.0) })
        gsap.to(
            TweenTarget(".dot"),
            tweenVars {
                x = TweenValue(280.0)
                duration = 1.4
                ease = EaseLike(easingSelect.value)
            },
        )
    }

    easingPlay.onclick = EventHandler { playDot() }
    easingSelect.onchange = EventHandler { playDot() }

    val easingRow = document.createElement(HtmlTagName.div).apply {
        className = ClassName("row")
        append(easingLabel, easingSelect, easingPlay)
    }

    val content = document.createElement(HtmlTagName.main).apply {
        className = ClassName("content")
        append(timelineTitle, timelineRow, stage, easingTitle, easingRow, easeStage)
    }

    fun render() {
        heading.textContent = texts["h1"]
        intro.textContent = texts["intro"]
        timelineTitle.textContent = texts["timeline"]
        easingTitle.textContent = texts["easeDemo"]
        timelinePlay.textContent = texts["play"]
        timelineReset.textContent = texts["reset"]
        easingPlay.textContent = texts["play"]
        easingLabel.textContent = "${texts["ease"]}: "
        version.textContent = "${texts["version"]}: ${gsap.version}"
    }
    texts.onChange { render() }
    render()

    root.append(header, intro, version, content)
    document.body.append(root)
}

/** The one conversion a DOM element needs to reach GSAP's `Element | object` target type. */
private fun HTMLElement.js(): JsAny = this.unsafeCast<JsAny>()
