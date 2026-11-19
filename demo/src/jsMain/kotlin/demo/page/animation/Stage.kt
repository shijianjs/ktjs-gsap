package demo.page.animation

import io.github.shijianjs.ktjs.demo.style.Palette
import io.github.shijianjs.ktjs.dom.element.inlineStyle
import io.github.shijianjs.ktjs.dom.element.invoke
import io.github.shijianjs.ktjs.dom.element.unaryPlus
import io.github.shijianjs.ktjs.gsap.core.TweenTarget
import kotlin.js.JsAny
import kotlin.js.unsafeCast
import web.cssom.AlignItems
import web.cssom.Border
import web.cssom.ClassName
import web.cssom.Color
import web.cssom.CustomPropertyName
import web.cssom.Display
import web.cssom.FontWeight
import web.cssom.JustifyContent
import web.cssom.LineStyle
import web.cssom.Overflow
import web.cssom.Padding
import web.cssom.Position
import web.cssom.`var`
import web.cssom.px
import web.html.HTMLElement
import web.html.HTMLSelectElement
import web.html.HtmlTagName

/** The class the end-to-end suite uses to find the animated boxes; it carries no styling. */
private val STAGE: ClassName = ClassName("stage")

private val BOX: ClassName = ClassName("box")

/** Wraps a DOM element as a gsap target; the only conversion an element needs. */
internal fun HTMLElement.target(): TweenTarget = TweenTarget(this.unsafeCast<JsAny>())

/**
 * The accent the animated nodes are painted with; Lumo's success colour keeps it on-theme, and the
 * literal is the fallback for the plain-HTML case where the token is not defined.
 */
private val ACCENT: Color = `var`(CustomPropertyName<Color>("--lumo-success-color"), Color("rgb(47, 158, 68)"))

/**
 * The dashed board the animated nodes move inside; it clips them at its edges.
 *
 * A fixed width, because the nodes travel inside it: gsap animates `x` in pixels, so the board has to
 * have a known size (the page's section body is content-sized, as every Vaadin vertical layout is).
 */
internal fun stage(): HTMLElement = HtmlTagName.div {
    classList.add(STAGE)
    inlineStyle.apply {
        position = Position.relative
        width = 640.px
        display = Display.flex
        alignItems = AlignItems.flexEnd
        columnGap = 12.px
        minHeight = 170.px
        padding = Padding(16.px, 16.px)
        border = Border(1.px, LineStyle.dashed, Palette.border)
        borderRadius = 12.px
        overflow = Overflow.hidden
    }
}

/** One square of the staggered timeline, numbered so the order is readable. */
internal fun box(index: Int): HTMLElement = HtmlTagName.div {
    classList.add(BOX)
    textContent = index.toString()
    inlineStyle.apply {
        width = 52.px
        height = 52.px
        display = Display.flex
        alignItems = AlignItems.center
        justifyContent = JustifyContent.center
        borderRadius = 10.px
        backgroundColor = ACCENT
        color = Color("rgb(255, 255, 255)")
        fontWeight = FontWeight.bold
    }
}

/** The single dot of the easing example. */
internal fun dot(): HTMLElement = HtmlTagName.div {
    inlineStyle.apply {
        width = 36.px
        height = 36.px
        minWidth = 36.px
        borderRadius = 18.px
        backgroundColor = ACCENT
    }
}

/** The easing picker; the returned element is typed so its `value` is the chosen easing name. */
internal fun picker(): HTMLSelectElement = HtmlTagName.select {
    inlineStyle.apply { fontSize = 14.px }
}
