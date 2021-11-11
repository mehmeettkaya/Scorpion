package com.mehmetkaya.scorpion.domain

import com.mehmetkaya.scorpion.data.datasource.PersonDataSource
import com.mehmetkaya.scorpion.data.model.Response
import com.mehmetkaya.scorpion.domain.model.Person
import com.mehmetkaya.scorpion.domain.model.Result
import com.mehmetkaya.scorpion.domain.usecase.FetchPersonUseCase
import com.mehmetkaya.scorpion.utils.CoroutineTestRule
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchPersonUseCaseTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var personDataSource: PersonDataSource

    private lateinit var fetchPersonUseCase: FetchPersonUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        fetchPersonUseCase = FetchPersonUseCase(personDataSource)
    }

    @Test
    fun `Return error result when fetchError is not null`() =
        coroutineTestRule.runBlockingTest {
            val next = "2"
            val errorMessage = "error!!"
            val fetchError = Response.FetchError(errorMessage)
            val resultWithError = null to fetchError
            coEvery { personDataSource.fetch(next) } returns resultWithError

            val result = fetchPersonUseCase(next)

            coVerify { personDataSource.fetch(next) }
            result shouldBe Result.Error(errorMessage)
        }

    @Test
    fun `Return error result when fetchResponse is null and fetchError is null`() =
        coroutineTestRule.runBlockingTest {
            val next = "2"
            val errorMessage = "Response can not be null."
            val resultWithNullResponse = null to null
            coEvery { personDataSource.fetch(next) } returns resultWithNullResponse

            val result = fetchPersonUseCase(next)

            coVerify { personDataSource.fetch(next) }
            result shouldBe Result.Error(errorMessage)
        }

    @Test
    fun `Return success result when fetchResponse is not null and fetchError is null`() =
        coroutineTestRule.runBlockingTest {
            val data = emptyList<Person>()
            val next = "3"
            val fetchResponse = Response.FetchResponse(data, next)
            val resultWithNullResponse = fetchResponse to null
            coEvery { personDataSource.fetch(next = null) } returns resultWithNullResponse

            val result = fetchPersonUseCase(next = null)

            coVerify { personDataSource.fetch(next = null) }
            result shouldBe Result.Success(data to next)
        }
}
