package com.example.newsapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.junit.Test

class LoginFeature:BaseAndroidTest() {

    val userName:String =  "tharuka"
    val password:String =  "1234567"

    @Test
    fun showErrorOnEmptyUserName(){
        onView(withId(R.id.et_username)).perform(typeText(""))
        onView(withId(R.id.btn_login)).perform(click())
        assertDisplayed("This field cannot be empty")
    }

    @Test
    fun showErrorOnEmptyPassword(){
        onView(withId(R.id.et_password)).perform(typeText(""))
        onView(withId(R.id.btn_login)).perform(click())
        assertDisplayed("This field cannot be empty")
    }

    @Test
    fun showErrorOnInvalidCredentials(){
        onView(withId(R.id.et_username)).perform(typeText("ruwani"))
        onView(withId(R.id.et_password)).perform(typeText("123"))
        onView(withId(R.id.btn_login)).perform(click())
        assertDisplayed("Invalid Credentials")
    }

    @Test
    fun successfullySignedUp(){
        onView(withId(R.id.et_username)).perform(typeText(userName))
        onView(withId(R.id.et_password)).perform(typeText(password))
        onView(withId(R.id.btn_login)).perform(click())
        assertDisplayed(R.id.parent_home)
    }

    @Test
    fun gotoCreateAccountOnClickCreateAccount(){
        onView(withId(R.id.tv_create_account)).perform(click())
        assertDisplayed(R.id.btn_signup)
    }

}