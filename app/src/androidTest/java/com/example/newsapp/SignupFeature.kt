package com.example.newsapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test

class SignupFeature:BaseAndroidTest() {

    val userName:String =  "ishani"
    val password:String =  "1234567"

    @Before
    fun navigate(){
        onView(withId(R.id.tv_create_account)).perform(click())
    }

    @Test
    fun showErrorOnEmptyUserName(){
        onView(withId(R.id.et_username)).perform(typeText(""))
        onView(withId(R.id.btn_signup)).perform(click())
        assertDisplayed("This field cannot be empty")
    }

    @Test
    fun showErrorOnEmptyPassword(){
        onView(withId(R.id.et_password)).perform(typeText(""))
        onView(withId(R.id.btn_signup)).perform(click())
        assertDisplayed("This field cannot be empty")
    }

    @Test
    fun showErrorOnShortPassword(){
        onView(withId(R.id.et_username)).perform(typeText("ruwani"))
        onView(withId(R.id.et_password)).perform(typeText("123"))
        onView(withId(R.id.btn_signup)).perform(click())
        assertDisplayed("Password is too short(min length 6)")
    }

    @Test
    fun showErrorOnUserNameAlreadyTaken(){
        onView(withId(R.id.et_username)).perform(typeText(userName))
        onView(withId(R.id.et_password)).perform(typeText(password))
        onView(withId(R.id.btn_signup)).perform(click())
        assertDisplayed("Username already taken")
    }

    @Test
    fun successfullySignedUp(){
        onView(withId(R.id.et_username)).perform(typeText(userName))
        onView(withId(R.id.et_password)).perform(typeText(password))
        onView(withId(R.id.btn_signup)).perform(click())
        assertDisplayed(R.id.parent_home)
    }

}