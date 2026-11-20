package demo.page.animation

import io.github.shijianjs.ktjs.demo.page.DemoPage
import io.github.shijianjs.ktjs.demo.i18n.Binder
import io.github.shijianjs.ktjs.demo.i18n.Lang
import io.github.shijianjs.ktjs.demo.i18n.Langs
import io.github.shijianjs.ktjs.demo.i18n.Translations
import io.github.shijianjs.ktjs.demo.i18n.texts
import io.github.shijianjs.ktjs.demo.page.Blocks.codeText
import io.github.shijianjs.ktjs.demo.page.Blocks.row
import io.github.shijianjs.ktjs.demo.page.SectionBlock
import io.github.shijianjs.ktjs.dom.element.invoke
import io.github.shijianjs.ktjs.dom.webcomponent.CustomElementReferenceAdapter
import io.github.shijianjs.ktjs.gsap.core.EaseLike
import io.github.shijianjs.ktjs.gsap.core.Position
import io.github.shijianjs.ktjs.gsap.core.TweenTarget
import io.github.shijianjs.ktjs.gsap.core.TweenValue
import io.github.shijianjs.ktjs.gsap.gsap
import io.github.shijianjs.ktjs.gsap.timelineVars
import io.github.shijianjs.ktjs.gsap.tweenVars
import io.github.shijianjs.ktjs.vaadin.button.createButton
import kotlin.js.JsAny
import kotlin.js.unsafeCast
import web.dom.clickEvent
import web.events.addHandler
import web.html.HTMLElement
import web.html.HtmlTagName

/** Every string this page shows, in one language. */
private class AnimationTexts(
    val title: String,
    val summary: String,
    val timelineHeading: String,
    val timelineHint: String,
    val easingHeading: String,
    val easingHint: String,
    val play: String,
    val reset: String,
    val easingLabel: String,
)

private val animationEnglish: AnimationTexts = AnimationTexts(
    title = "Animation",
    summary = "Two real gsap animations created through the Kotlin/JS wrapper: a timeline with " +
        "staggered children, and one tween whose easing comes from the picker.",
    timelineHeading = "Timeline with a stagger",
    timelineHint = "Each box starts 150 ms after the previous one, on a timeline built once at start-up.",
    easingHeading = "Single tween, easing from the picker",
    easingHint = "Choosing an easing plays the same tween again with the chosen curve.",
    play = "Play",
    reset = "Reset",
    easingLabel = "Easing",
)

private val animationChinese: AnimationTexts = AnimationTexts(
    title = "动画",
    summary = "两条通过 Kotlin/JS wrapper 创建的真实 gsap 动画：一条带错开子项的时间线，" +
        "以及一条缓动取自下拉框的补间。",
    timelineHeading = "带错开的时间线",
    timelineHint = "每个方块比前一个晚 150 毫秒入场，时间线在启动时构建一次。",
    easingHeading = "单条补间，缓动取自下拉框",
    easingHint = "换一个缓动会按所选曲线重新播放同一条补间。",
    play = "播放",
    reset = "重置",
    easingLabel = "缓动",
)

private val animationTexts: Translations<AnimationTexts> = texts(
    Langs.EN to animationEnglish,
    Langs.ZH_HANS to animationChinese,
)

/** The easing names the demo offers; all of them ship with `gsap`. */
private val EASINGS: List<String> =
    listOf("power2.out", "back.out", "elastic.out", "bounce.out", "circ.out")

/** The dot travels this far, leaving it inside the 640 px board with room to spare. */
private const val DOT_TRAVEL: Double = 520.0

/**
 * The animation page: both examples on one page, because they are one story — the timeline builds
 * children, the tween takes one value from a picker.
 *
 * The nodes are fields and the animations are built once; a language switch only rewrites text, so a
 * running or finished animation keeps its position while the copy changes.
 */
internal class AnimationPage : DemoPage() {

    private val texts = Binder(animationTexts)

    private val boxes: List<Box> = (1..5).map { newBox(it) }

    private val timelineStage: Stage = Stage.NAME().apply { boxes.forEach { append(it) } }

    private val easingStage: Stage = Stage.NAME()

    private val dotElement: Dot = Dot.NAME()

    private val timelinePlay = createButton()

    private val timelineReset = createButton()

    private val easingPlay = createButton()

    private val easingLabel: HTMLElement = HtmlTagName.label {}

    private val easingSelect: EasingPicker = newEasingPicker(EASINGS)

    /**
     * The staggered timeline; built once, restarted by the Play button.
     *
     * `immediateRender = false` keeps the boxes at their resting state: without it a paused `from()`
     * writes the start values as soon as the timeline is built, and the stage is empty until the first
     * click.
     */
    private val timeline = gsap.timeline(timelineVars { paused = true }).apply {
        boxes.forEachIndexed { index, each ->
            from(
                TweenTarget(each.target()),
                tweenVars {
                    y = TweenValue(120.0)
                    opacity = TweenValue(0.0)
                    duration = 0.5
                    ease = EaseLike("back.out")
                    immediateRender = false
                },
                Position(index * 0.15),
            )
        }
    }


    init {
        texts.bind(timelinePlay, { it.play })
        texts.bind(timelineReset, { it.reset })
        texts.bind(easingPlay, { it.play })
        texts.bind(easingLabel) { it.easingLabel + ": " }

        // The snippet next to the timeline: the same call the page makes, so the page shows both the
        // result and the API that produced it.
        val timelineSnippet = codeText(
            "gsap.timeline(timelineVars { paused = true }).apply { " +
                "from(target, tweenVars { y = …; opacity = 0.0 }, Position(i * 0.15)) }",
        )

        val timelineSection = SectionBlock.NAME { show() }
        texts.bind(timelineSection.heading, { it.timelineHeading })
        texts.bind(timelineSection.hint, { it.timelineHint })
        timelineSection.body.append(row(timelinePlay, timelineReset), timelineStage, timelineSnippet)

        val easingSection = SectionBlock.NAME { show() }
        texts.bind(easingSection.heading, { it.easingHeading })
        texts.bind(easingSection.hint, { it.easingHint })
        easingSection.body.append(row(easingLabel, easingSelect, easingPlay), easingStage)

        attachDot()

        body.append(timelineSection.root, easingSection.root)

        timelinePlay.clickEvent.addHandler {
            resetBoxes()
            timeline.restart(null, null)
        }
        timelineReset.clickEvent.addHandler {
            timeline.pause(null, null)
            resetBoxes()
        }
        easingPlay.clickEvent.addHandler { playDot() }
        // A native `<select>`: the value it reports is exactly the `ease` name gsap takes.
        easingSelect.onChange { playDot() }
    }

    /** The dot sits on the stage from the first paint; playing only restarts its position. */
    private fun attachDot() {
        easingStage.append(dotElement)
    }

    private fun resetBoxes() {
        boxes.forEach { each ->
            each.style.setProperty("transform", "")
            each.style.setProperty("opacity", "1")
        }
    }

    private fun playDot() {
        dotElement.style.setProperty("transform", "")
        gsap.to(
            TweenTarget(".dot"),
            tweenVars {
                x = TweenValue(DOT_TRAVEL)
                duration = 1.4
                ease = EaseLike(easingSelect.value)
            },
        )
    }

    override fun applyTranslation(lang: Lang) {
        val current = animationTexts[lang]
        setTitle(current.title)
        setSummary(current.summary)
        texts.apply(lang)
    }

    companion object : CustomElementReferenceAdapter<AnimationPage>(AnimationPage::class.js, "gsap-animation-page")
}
