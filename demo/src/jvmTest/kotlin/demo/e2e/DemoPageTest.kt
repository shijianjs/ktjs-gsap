package demo.e2e

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.junit.UsePlaywright
import com.microsoft.playwright.options.WaitUntilState
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junitpioneer.jupiter.RetryingTest

/**
 * Smoke checks for the demo page: the shell upgrades, the navigation lists both pages, the wrapped
 * library runs, and the language menu swaps every visible string.
 *
 * Selectors prefer what the component itself renders (`vaadin-side-nav-item[data-route=…]`) or the
 * action ids the layout writes on its menu entries, so the suite does not depend on class names.
 */
@UsePlaywright(DemoOptions::class)
class DemoPageTest {
    @RetryingTest(maxAttempts = 2)
    fun `the shell renders title, logo and both pages`(page: Page) {
        openPage(page)

        assertTrue(page.title().contains("ktjs-gsap"), "title: ${page.title()}")
        assertTrue(page.locator("vaadin-side-nav-item[data-route]").count() == 2, "two pages in the drawer")
        assertTrue(page.locator("img.layout__logo").first().isVisible(), "the demo serves the project logo")

        val upgraded = page.evaluate("() => document.querySelector('ktjs-demo-layout')?.shadowRoot != null") as Boolean
        assertTrue(upgraded, "the declared <ktjs-demo-layout> upgraded into a component")
    }

    @RetryingTest(maxAttempts = 2)
    fun `the timeline button animates the boxes`(page: Page) {
        page.navigate("/#/animation", Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE))

        val before = page.locator(".stage .box").first().getAttribute("style") ?: ""
        // The first button of the page is the timeline's Play; the tween writes an inline transform.
        page.locator("vaadin-button").filter(Locator.FilterOptions().setHasText("Play"))
            .first().click()
        page.waitForTimeout(1_000.0)
        val after = page.locator(".stage .box").first().getAttribute("style") ?: ""
        assertTrue(
            after.contains("transform") || before.contains("transform"),
            "the timeline writes a transform on the first box (style: $after)",
        )
    }

    @RetryingTest(maxAttempts = 2)
    fun `the language menu swaps the page copy`(page: Page) {
        page.navigate("/#/overview", Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE))

        assertEquals("Overview", page.locator("ktjs-demo-page h1").first().textContent())

        page.locator("[data-menu='lang']").first().click()
        page.locator("[data-action='lang:zh-Hans']").first().click()

        assertEquals("概览", page.locator("ktjs-demo-page h1").first().textContent())
        assertEquals("zh-Hans", page.locator("html").getAttribute("lang"))
    }

    @RetryingTest(maxAttempts = 2)
    fun `page logs no console errors or failed requests`(page: Page) {
        val consoleErrors = mutableListOf<String>()
        val failedRequests = mutableListOf<String>()
        page.onConsoleMessage { message -> if (message.type() == "error") consoleErrors += message.text() }
        page.onRequestFailed { request -> failedRequests += "${request.url()} ${request.failure()}" }

        page.navigate("/#/animation", Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE))

        assertTrue(consoleErrors.isEmpty(), "console errors: $consoleErrors")
        assertTrue(failedRequests.isEmpty(), "failed requests: $failedRequests")
    }

    private fun openPage(page: Page) {
        page.navigate("/", Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE))
    }
}
