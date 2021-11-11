package com.mehmetkaya.scorpion.ui

import com.mehmetkaya.scorpion.domain.model.Person
import io.kotest.matchers.shouldBe
import org.junit.Test

class MainItemViewDataTest {

    @Test
    fun `Verify personWithId is correct when the person is given`() {
        val person = Person(id = 123, fullName = "Mehmet Kaya")
        val mainItemViewData = MainItemViewData(person)

        mainItemViewData.personWithId shouldBe "Mehmet Kaya (123)"
    }
}
