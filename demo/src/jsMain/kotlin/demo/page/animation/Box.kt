package demo.page.animation

import io.github.shijianjs.ktjs.dom.element.inlineStyle
import io.github.shijianjs.ktjs.dom.element.invoke
import io.github.shijianjs.ktjs.dom.webcomponent.CustomElementAdapter
import io.github.shijianjs.ktjs.dom.webcomponent.CustomElementReferenceAdapter
import web.cssom.AlignItems
import web.cssom.ClassName
import web.cssom.Color
import web.cssom.Display
import web.cssom.FontWeight
import web.cssom.JustifyContent
import web.cssom.px

/** The class the end-to-end suite looks the squares up by; the styling sits on the element itself. */
private val BOX: ClassName = ClassName("box")

/**
 * `<gsap-box>`: one square of the staggered timeline, numbered so the order is readable.
 *
 * The number is the element's whole state, so it is set once through [show] at creation: a timeline
 * animation never rewrites it.
 */
internal class Box : CustomElementAdapter() {
    /** Painted on connect: the constructor of a custom element may not set the element's attributes. */
    override fun connectedCallback() {
        classList.add(BOX)
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

    /** Writes the square's number. */
    fun show(index: Int) {
        textContent = index.toString()
    }

    companion object : CustomElementReferenceAdapter<Box>(Box::class.js, "gsap-box")
}

/** Creates a defined `<gsap-box>` showing [index]. */
internal fun newBox(index: Int): Box {
    // The companion registers the tag in its `init`; reading `NAME` upgrades the element the page already spells.
    Box.NAME
    return Box.NAME().also { it.show(index) }
}
