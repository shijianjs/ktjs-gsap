package demo.page.animation

import io.github.shijianjs.ktjs.dom.element.inlineStyle
import io.github.shijianjs.ktjs.dom.element.invoke
import io.github.shijianjs.ktjs.dom.webcomponent.CustomElementAdapter
import web.cssom.px

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

    companion object : DotDefaultReference()
}
