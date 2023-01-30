package com.example.newsapp.utils

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.google.android.material.chip.Chip
import org.hamcrest.Matchers

fun chipContainsText(text: String) {
    Espresso.onView(
        Matchers.allOf(
            ViewMatchers.withText(Matchers.containsString(text)),
            ViewMatchers.isAssignableFrom(Chip::class.java)
        )
    ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}