package com.example.newsapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.example.newsapp.ui.helpers.searchCategories
import com.example.newsapp.ui.viewHolders.HeadlineViewHolder
import com.example.newsapp.utils.chipContainsText
import com.google.android.material.chip.Chip
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.junit.Test


class HomeFeature: BaseAndroidTest() {

    @Test
    fun showsHomeScreenWithSearchBar(){
         assertDisplayed(R.id.til_search)
    }

    @Test
    fun showsHeadlineItems(){

        Thread.sleep(1000)
        assertDisplayed(R.id.rv_headlines)
        assertRecyclerViewItemCount(R.id.rv_headlines, 20)
//        first item
        onView(withId(R.id.rv_headlines)).perform(RecyclerViewActions.scrollToPosition<HeadlineViewHolder>(0))
        onView(withText("Andy Rose, Paradise Afshar")).check(matches(isDisplayed()))
        onView(withText("Colorado police release video of police car with suspect inside hit by a train - CNN")).check(matches(isDisplayed()))
        onView(withText("Colorado police have released videos showing a train hitting a police cruiser in which a detained suspect was handcuffed.")).check(matches(isDisplayed()))
//        10th item
        onView(withId(R.id.rv_headlines)).perform(RecyclerViewActions.scrollToPosition<HeadlineViewHolder>(9))
        onView(withText("Riley Cardoza")).check(matches(isDisplayed()))
        onView(withText("Hilaria Baldwin gives birth to Ilaria, her 7th baby with Alec Baldwin, his 8th - Page Six")).check(matches(isDisplayed()))
        onView(withText("“She’s here! We are so excited to introduce you to our tiny dream come true,” the mom of seven shared in a heartwarming Instagram post.")).check(matches(isDisplayed()))
//        last item
        onView(withId(R.id.rv_headlines)).perform(RecyclerViewActions.scrollToPosition<HeadlineViewHolder>(19))
        onView(withText("Ashley Strickland")).check(matches(isDisplayed()))
        onView(withText("NASA spacecraft will reveal first look at an asteroid, then slam into it - CNN")).check(matches(isDisplayed()))
        onView(withText("This week, learn everything you need to know about the DART spacecraft's plan to crash into an asteroid, gain insights from dinosaur eggs, meet a cloned Arctic wolf cub, hear space rocks crash into Mars, and more.")).check(matches(isDisplayed()))
    }

    @Test
    fun showsCategoryChips(){
        assertDisplayed(R.id.cg_category)
        chipContainsText(searchCategories[0])
        chipContainsText(searchCategories[1])
        chipContainsText(searchCategories[2])
        chipContainsText(searchCategories[3])
        chipContainsText(searchCategories[4])
    }

    @Test
    fun selectsChip() {
//        check all any chip is not selected initially
        onView(withText("Health")).check(matches(isNotChecked()))
        onView(withText("Science")).check(matches(isNotChecked()))
        onView(withText("Entertainment")).check(matches(isNotChecked()))
        onView(withText("Political")).check(matches(isNotChecked()))
        onView(withText("Sports")).check(matches(isNotChecked()))

//        check health chip
        onView(allOf(withText("Health"), withId(R.id.chip_category)))
            .perform(click())
        onView(withText("Health")).check(matches(isChecked()))

//        check science chip
        onView(allOf(withText("Science"), withId(R.id.chip_category)))
            .perform(click())
        onView(withText("Science")).check(matches(isChecked()))

//        check entertainment chip
        onView(allOf(withText("Entertainment"), withId(R.id.chip_category)))
            .perform(click())
        onView(withText("Entertainment")).check(matches(isChecked()))

//        check political chip
        onView(allOf(withText("Political"), withId(R.id.chip_category)))
            .perform(click())
        onView(withText("Political")).check(matches(isChecked()))

//        check sports chip
        onView(allOf(withText("Sports"), withId(R.id.chip_category))).perform(scrollTo())
        onView(allOf(withText("Sports"), withId(R.id.chip_category)))
            .perform(click())
        onView(withText("Sports")).check(matches(isChecked()))

        Thread.sleep(2000)
    }
}