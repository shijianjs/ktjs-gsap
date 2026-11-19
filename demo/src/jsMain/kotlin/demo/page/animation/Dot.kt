package demo.page.animation

import io.github.shijianjs.ktjs.dom.element.inlineStyle
import io.github.shijianjs.ktjs.dom.element.invoke
import io.github.shijianjs.ktjs.dom.webcomponent.CustomElementAdapter
import io.github.shijianjs.ktjs.dom.webcomponent.defineIfMissing
import web.components.CustomElementReference
import web.cssom.px
import web.dom.TagName

/** `<gsap-dot>`: the single node of the easing example. */
internal class Dot : CustomElementAdapter() {
    /** Painted on connect: the constructor of a custom element may not set the element's attributes. */
    override fun connectedCallback() {
        inlineStyle.apply {
            width = 36.px
            height = 36.px
            minWidth = 36.px
            borderRadius = 18.px
            backgroundColor = ACCENT
        }
    }

    companion object : CustomElementReference<Dot>(Dot::class.js) {
        val NAME: TagName<Dot> = TagName("gsap-dot")

        fun define() {
            defineIfMissing(NAME, this)
        }
    }
}

/** Creates a defined `<gsap-dot>`. */
internal fun newDot(): Dot {
    Dot.define()
    return Dot.NAME()
}
