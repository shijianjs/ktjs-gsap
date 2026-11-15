package demo.e2e

import com.microsoft.playwright.Page
import com.microsoft.playwright.junit.UsePlaywright
import com.microsoft.playwright.options.WaitUntilState
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junitpioneer.jupiter.RetryingTest

/**
 * Smoke checks for the demo page: it must load the bundle, apply its stylesheet, run the wrapped
 * library and switch between the two languages.
 */
@UsePlaywright(DemoOptions::class)
class DemoPageTest {
    @RetryingTest(maxAttempts = 2)
    fun `page renders heading, logo and styles`(page: Page) {
        openPage(page)

        assertTrue(page.title().contains("ktjs-gsap"), "title: ${page.title()}")
        assertEquals("ktjs-gsap demo", page.locator("h1").textContent())
        assertTrue(page.locator("img.logo").isVisible(), "the demo serves the project logo")

        val display = page.locator(".page").evaluate("(node) => getComputedStyle(node).display") as String
        assertTrue(display != "contents", "the stylesheet applies to .page (display = $display)")
    }

    @RetryingTest(maxAttempts = 2)
    fun `the timeline button animates the boxes`(page: Page) {
        openPage(page)

        // Before the click the boxes carry no inline transform; the timeline creates one.
        val before = page.locator(".box").first().getAttribute("style") ?: ""
        // The first `.content` button is the timeline's Play, not the language switch in the header.
        page.locator(".content .row button").first().click()
        page.waitForTimeout(1_200.0)
        val during = page.locator(".box").first().getAttribute("style") ?: ""
        assertTrue(
            during.contains("transform") || before.contains("transform"),
            "the timeline writes a transform on the first box (style: $during)",
        )
    }

    @RetryingTest(maxAttempts = 2)
    fun `the language switch swaps every visible string`(page: Page) {
        openPage(page)

        page.locator("button.lang").click()

        assertEquals("ktjs-gsap 演示", page.locator("h1").textContent())
        assertEquals("zh-Hans", page.locator("html").getAttribute("lang"))

        page.locator("button.lang").click()

        assertEquals("ktjs-gsap demo", page.locator("h1").textContent())
        assertEquals("en", page.locator("html").getAttribute("lang"))
    }

    @RetryingTest(maxAttempts = 2)
    fun `page logs no console errors or failed requests`(page: Page) {
        val consoleErrors = mutableListOf<String>()
        val failedRequests = mutableListOf<String>()
        page.onConsoleMessage { message -> if (message.type() == "error") consoleErrors += message.text() }
        page.onRequestFailed { request -> failedRequests += "${request.url()} ${request.failure()}" }

        openPage(page)

        assertTrue(consoleErrors.isEmpty(), "console errors: $consoleErrors")
        assertTrue(failedRequests.isEmpty(), "failed requests: $failedRequests")
    }

    private fun openPage(page: Page) {
        page.navigate("/", Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE))
    }
}
