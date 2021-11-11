package com.mehmetkaya.scorpion.ui

import com.mehmetkaya.scorpion.domain.model.Person
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.Test

class MainViewDataTest {

    @Test
    fun `Verify isLoadingVisibility is true when isLoading is true`() {
        val mainViewData = MainViewData(isLoading = true)

        mainViewData.isLoadingVisibility.shouldBeTrue()
    }

    @Test
    fun `Verify isLoadingVisibility is false when isLoading is false`() {
        val mainViewData = MainViewData(isLoading = false)

        mainViewData.isLoadingVisibility.shouldBeFalse()
    }

    @Test
    fun `Verify hasNextPage is true when items are null and next is null`() {
        val mainViewData = MainViewData(items = null, next = null)

        mainViewData.hasNextPage.shouldBeTrue()
    }

    @Test
    fun `Verify hasNextPage is true when items are empty and next is null`() {
        val mainViewData = MainViewData(items = listOf(), next = null)

        mainViewData.hasNextPage.shouldBeTrue()
    }

    @Test
    fun `Verify hasNextPage is false when items are not empty and next is null`() {
        val person = Person(id = 123, "Mehmet Kaya")
        val mainViewData = MainViewData(items = listOf(MainItemViewData(person)), next = null)

        mainViewData.hasNextPage.shouldBeFalse()
    }

    @Test
    fun `Verify hasNextPage is true when items are empty and next is not null`() {
        val mainViewData = MainViewData(items = listOf(), next = "2")

        mainViewData.hasNextPage.shouldBeTrue()
    }

    @Test
    fun `Verify hasNextPage is true when items are not empty and next is not null`() {
        val person = Person(id = 123, "Mehmet Kaya")
        val mainViewData = MainViewData(items = listOf(MainItemViewData(person)), next = "2")

        mainViewData.hasNextPage.shouldBeTrue()
    }

    @Test
    fun `Verify isEmptyCaseVisibility is true when items are not null and items are empty`() {
        val mainViewData = MainViewData(items = listOf())

        mainViewData.isEmptyCaseVisibility.shouldBeTrue()
    }

    @Test
    fun `Verify isEmptyCaseVisibility is false when items are not null and items are not empty`() {
        val person = Person(id = 123, "Mehmet Kaya")
        val mainViewData = MainViewData(items = listOf(MainItemViewData(person)))

        mainViewData.isEmptyCaseVisibility.shouldBeFalse()
    }

    @Test
    fun `Verify isEmptyCaseVisibility is false when items are null`() {
        val mainViewData = MainViewData(items = null)

        mainViewData.isEmptyCaseVisibility.shouldBeFalse()
    }

    @Test
    fun `Verify isItemsVisibility is true when items are not null and items are not empty`() {
        val person = Person(id = 123, "Mehmet Kaya")
        val mainViewData = MainViewData(items = listOf(MainItemViewData(person)))

        mainViewData.isItemsVisibility.shouldBeTrue()
    }

    @Test
    fun `Verify isItemsVisibility is false when items are not null and items are empty`() {
        val mainViewData = MainViewData(items = listOf())

        mainViewData.isItemsVisibility.shouldBeFalse()
    }

    @Test
    fun `Verify isItemsVisibility is false when items are null`() {
        val mainViewData = MainViewData(items = null)

        mainViewData.isItemsVisibility.shouldBeFalse()
    }
}
