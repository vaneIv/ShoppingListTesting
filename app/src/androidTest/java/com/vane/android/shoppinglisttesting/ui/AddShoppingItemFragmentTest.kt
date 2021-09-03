package com.vane.android.shoppinglisttesting.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.vane.android.shoppinglisttesting.R
import com.vane.android.shoppinglisttesting.getOrAwaitValue
import com.vane.android.shoppinglisttesting.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class AddShoppingItemFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun clickImageViewShoppingImage_navigateToImagePickFragment() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.image_view_shopping_image)).perform(click())

        val action =
            AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
        verify(navController).navigate(action)
    }

    @Test
    fun pressBackButton_popBackStack() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        pressBack()

        verify(navController).popBackStack()
    }

//    @Test
//    fun pressBackButton_imageUrlSetToEmptyString() {
//        val testViewModel = ShoppingViewModel(FakeShoppingRepository())
//        val navController = mock(NavController::class.java)
//        launchFragmentInHiltContainer<AddShoppingItemFragment> {
//            Navigation.setViewNavController(requireView(), navController)
//            viewModel = testViewModel
//        }
//
//        pressBack()
//
//        val value = testViewModel.curImageUrl.getOrAwaitValue()
//        assertThat(value).isEmpty()
//    }
}