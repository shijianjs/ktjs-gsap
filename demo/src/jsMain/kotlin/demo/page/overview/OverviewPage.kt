package demo.page.overview

import demo.API_URL
import demo.DOCS_URL
import demo.SOURCE_URL
import io.github.shijianjs.ktjs.demo.i18n.Binder
import io.github.shijianjs.ktjs.demo.i18n.Lang
import io.github.shijianjs.ktjs.demo.i18n.Langs
import io.github.shijianjs.ktjs.demo.i18n.Translations
import io.github.shijianjs.ktjs.demo.i18n.texts
import io.github.shijianjs.ktjs.demo.nav.Page
import io.github.shijianjs.ktjs.demo.page.anchor
import io.github.shijianjs.ktjs.demo.page.codeText
import io.github.shijianjs.ktjs.demo.page.newPage
import io.github.shijianjs.ktjs.demo.page.paragraph
import io.github.shijianjs.ktjs.demo.page.row
import io.github.shijianjs.ktjs.demo.page.sectionBlock
import io.github.shijianjs.ktjs.dom.element.invoke
import io.github.shijianjs.ktjs.dom.element.unaryPlus
import web.html.HtmlTagName

/** Every string this page shows, in one language. */
private class OverviewTexts(
    val title: String,
    val summary: String,
    val docsLink: String,
    val sourceLink: String,
    val apiLink: String,
    val purposeHeading: String,
    val body: String,
    val bullets: List<String>,
    val coordinatesHeading: String,
    val coordinatesNote: String,
    val installLabel: String,
    val nextHeading: String,
    val nextBody: String,
)

private val overviewEnglish: OverviewTexts = OverviewTexts(
    title = "Overview",
    summary = "A Kotlin/JS wrapper for gsap: typed tweens, timelines, eases and plugin state, " +
        "with no `JsAny` in the public API.",
    docsLink = "Documentation",
    sourceLink = "Source repository",
    apiLink = "API reference",
    purposeHeading = "What it wraps",
    body = "gsap takes plain objects: `gsap.to(el, { x: 280, duration: 1.4, ease: \"power2.out\" })`. " +
        "In Kotlin those objects would be `JsAny` literals; this wrapper models the same options as " +
        "declarations, so a misspelled property or a wrong value type fails at compile time instead " +
        "of doing nothing at runtime.",
    bullets = listOf(
        "TweenTarget — a DOM node, a selector or an array of either, typed.",
        "tweenVars { } and timelineVars { } — the option objects of `to` / `from` / `set` and of " +
            "`timeline`.",
        "EaseLike, TweenValue, Position — the value types gsap accepts for `ease`, the animated " +
            "properties and the start of a child animation.",
        "Timeline — `from`, `to`, `set` return the timeline itself, so children chain.",
    ),
    coordinatesHeading = "Coordinates",
    coordinatesNote = "The demo next to this site is built with the library itself, so every page " +
        "doubles as an integration test.",
    installLabel = "Gradle coordinate",
    nextHeading = "Reading order",
    nextBody = "The overview, then the animation page: a staggered timeline and a single tween whose " +
        "easing comes from a picker.",
)

private val overviewChinese: OverviewTexts = OverviewTexts(
    title = "概览",
    summary = "gsap 的 Kotlin/JS 封装：类型化的补间、时间线、缓动与插件状态，公开 API 里没有 `JsAny`。",
    docsLink = "使用文档",
    sourceLink = "源码仓库",
    apiLink = "API 文档",
    purposeHeading = "它封装了什么",
    body = "gsap 收的是普通对象：`gsap.to(el, { x: 280, duration: 1.4, ease: \"power2.out\" })`。" +
        "在 Kotlin 里这些对象只能写成 `JsAny` 字面量；这个封装把同一套选项变成声明，属性拼错或类型不对" +
        "在编译期就报错，而不是运行时静默失效。",
    bullets = listOf(
        "TweenTarget —— DOM 节点、选择器，或两者的数组，带类型。",
        "tweenVars { } 与 timelineVars { } —— `to` / `from` / `set` 与 `timeline` 的选项对象。",
        "EaseLike、TweenValue、Position —— gsap 接受的 `ease`、动画属性值与子动画起点的取值类型。",
        "Timeline —— `from` / `to` / `set` 返回时间线自身，子动画因此可以链式书写。",
    ),
    coordinatesHeading = "坐标",
    coordinatesNote = "这个站点旁边的 demo 本身就用这个库搭建，每页同时充当它的集成测试。",
    installLabel = "Gradle 坐标",
    nextHeading = "建议顺序",
    nextBody = "先看概览，再看动画页：一条带错开的时间线，和一条缓动取自下拉框的补间。",
)

private val overviewTexts: Translations<OverviewTexts> = texts(
    Langs.EN to overviewEnglish,
    Langs.ZH_HANS to overviewChinese,
)

/**
 * Landing page: what the library wraps, how it is consumed, where the reference lives.
 *
 * The sections are plain blocks, not components: they have nothing to swap at run time, so a component
 * per section would only add indirection.
 */
internal class OverviewPage : Page {
    private val page = newPage()

    private val texts = Binder(overviewTexts)

    override val node get() = page

    init {
        page.addLink(texts.bind(anchor("", DOCS_URL, external = true), { it.docsLink }))
        page.addLink(texts.bind(anchor("", API_URL, external = true), { it.apiLink }))
        page.addLink(texts.bind(anchor("", SOURCE_URL, external = true), { it.sourceLink }))

        val purpose = sectionBlock()
        texts.bind(purpose.title, { it.purposeHeading })

        // One `li` per bullet, each bound to its own string: a language switch rewrites the text and
        // leaves the list itself alone.
        val bullets = HtmlTagName.ul {
            List(4) { index -> texts.bind(+HtmlTagName.li()) { it.bullets.getOrNull(index).orEmpty() } }
        }
        purpose.body.append(texts.bind(paragraph(), { it.body }), bullets)

        val coordinates = sectionBlock()
        texts.bind(coordinates.title, { it.coordinatesHeading })
        texts.bind(coordinates.hint, { it.coordinatesNote })
        coordinates.body.append(
            row(codeText("implementation(\"io.github.shijianjs:ktjs-gsap:3.15.1-kt.1\")")),
            row(texts.bind(anchor("", SOURCE_URL, external = true), { it.sourceLink })),
        )

        val next = sectionBlock()
        texts.bind(next.title, { it.nextHeading })
        next.body.append(texts.bind(paragraph(), { it.nextBody }))

        page.body.append(purpose.root, coordinates.root, next.root)
    }

    override fun applyTranslation(lang: Lang) {
        val current = overviewTexts[lang]
        page.setTitle(current.title)
        page.setSummary(current.summary)
        texts.apply(lang)
    }
}
