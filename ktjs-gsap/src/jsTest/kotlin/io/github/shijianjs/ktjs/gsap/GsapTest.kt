package io.github.shijianjs.ktjs.gsap

import io.github.shijianjs.ktjs.gsap.core.TweenTarget
import io.github.shijianjs.ktjs.gsap.core.TweenValue
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.js.unsafeCast
import web.cssom.ClassName
import web.dom.document
import web.html.HTMLElement
import web.html.HtmlTagName

/**
 * Browser tests: each case drives the real `gsap` package, so a wrong `@JsName`, a wrong value type
 * or a dropped member fails here instead of at a consumer's runtime.
 *
 * Tweens are created paused or driven with `progress(1)`, so no case depends on the clock.
 */
class GsapTest {
    /** Creates a 100×100 box with [className] and appends it to the page. */
    private fun box(className: String): HTMLElement {
        val host = document.createElement(HtmlTagName.div)
        host.className = ClassName(className)
        host.style.width = "100px"
        host.style.height = "100px"
        document.body.appendChild(host)
        return host
    }

    @Test
    fun versionComesFromTheWrappedPackage() {
        assertTrue(gsap.version.startsWith("3."), "unexpected gsap version: ${gsap.version}")
    }

    @Test
    fun toReturnsATweenWithTheVarsItWasGiven() {
        val target = box("to-target")
        val tween = gsap.to(
            TweenTarget(".to-target"),
            tweenVars {
                x = TweenValue(100.0)
                duration = 2.0
                paused = true
            },
        )
        assertEquals(2.0, tween.duration())
        assertTrue(tween.targets<HTMLElement>().isNotEmpty())
        assertEquals(target, tween.targets<HTMLElement>().first())
        // The tween is paused, so nothing was written to the element yet.
        assertEquals("", target.style.transform)
        tween.progress(1.0, null)
        assertTrue(target.style.transform.contains("100"), target.style.transform)
    }

    @Test
    fun timelineChainsChildrenAndReportsItsDuration() {
        box("timeline-target")
        val timeline = gsap.timeline(timelineVars { paused = true })
        timeline.to(TweenTarget(".timeline-target"), tweenVars { x = TweenValue(10.0); duration = 1.0 }, null)
        timeline.to(TweenTarget(".timeline-target"), tweenVars { y = TweenValue(20.0); duration = 1.0 }, null)
        assertEquals(2.0, timeline.duration())
        assertEquals(2.0, timeline.totalDuration())
        assertEquals(0.0, timeline.progress())
    }

    @Test
    fun utilsClampAndMapRangeFollowTheDocumentedBehaviour() {
        assertEquals(4.0, gsap.utils.clamp(0.0, 4.0, 5.0))
        assertEquals(0.0, gsap.utils.clamp(0.0, 4.0, -1.0))
        assertEquals(50.0, gsap.utils.mapRange(0.0, 10.0, 0.0, 100.0, 5.0))
        assertEquals("px", gsap.utils.getUnit("12px"))
        assertEquals("vh", gsap.utils.getUnit("12vh"))
    }

    @Test
    fun setWritesTheValueImmediately() {
        val target = box("set-target")
        gsap.set(TweenTarget(".set-target"), tweenVars { opacity = TweenValue(0.5) })
        assertEquals("0.5", target.style.opacity)
    }

    @Test
    fun getTweensOfFindsTheTweenWeCreated() {
        box("tweens-target")
        gsap.to(TweenTarget(".tweens-target"), tweenVars { x = TweenValue(5.0); duration = 1.0; paused = true })
        val tweens = gsap.getTweensOf(TweenTarget(".tweens-target"), null)
        assertTrue(tweens.isNotEmpty())
        assertNotNull(tweens.first().vars)
        assertEquals(1.0, tweens.first().duration())
    }

    @Test
    fun typedTargetFactoriesRoundTripThroughGsap() {
        val element = box("element-target")
        val tween = gsap.to(TweenTarget(element.unsafeCast<kotlin.js.JsAny>()), tweenVars { x = TweenValue(1.0); duration = 1.0; paused = true })
        assertEquals(element, tween.targets<HTMLElement>().first())
        assertEquals(1.0, tween.duration())
    }
}
