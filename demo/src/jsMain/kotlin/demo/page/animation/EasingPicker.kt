package demo.page.animation

import io.github.shijianjs.ktjs.dom.element.inlineStyle
import io.github.shijianjs.ktjs.dom.element.invoke
import io.github.shijianjs.ktjs.dom.webcomponent.CustomElementAdapter
import io.github.shijianjs.ktjs.dom.webcomponent.attachShadowRoot
import web.cssom.px
import web.events.EventHandler
import web.html.HTMLSelectElement
import web.html.HtmlTagName

/**
 * `<gsap-easing-picker>`: the easing drop-down of the single-tween example.
 *
 * The native `<select>` lives inside the element, so what a caller reads ([value]) and what it waits on
 * ([onChange]) are still the native selection's — the element only adds the option list and the type.
 */
internal class EasingPicker : CustomElementAdapter() {
    private val select: HTMLSelectElement = HtmlTagName.select {
        inlineStyle.apply { fontSize = 14.px }
    }

    init {
        attachShadowRoot().append(select)
    }

    /** Fills the drop-down with the easings gsap ships; the option's value is its name. */
    fun offer(easings: List<String>) {
        easings.forEach { name ->
            select.append(HtmlTagName.option { value = name; textContent = name })
        }
    }

    /** The chosen easing name, which is exactly the `ease` value the wrapper takes. */
    val value: String get() = select.value

    /** Runs [action] whenever the selection changes. */
    fun onChange(action: () -> Unit) {
        select.onchange = EventHandler { action() }
    }

    companion object : EasingPickerDefaultReference()
}
