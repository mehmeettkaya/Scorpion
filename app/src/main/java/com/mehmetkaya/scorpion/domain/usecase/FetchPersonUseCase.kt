package com.mehmetkaya.scorpion.domain.usecase

import com.mehmetkaya.scorpion.data.datasource.PersonDataSource
import com.mehmetkaya.scorpion.domain.model.Person
import com.mehmetkaya.scorpion.domain.model.Result
import javax.inject.Inject

class FetchPersonUseCase @Inject constructor(
    private val personDataSource: PersonDataSource
) {
    suspend operator fun invoke(next: String?): Result<Pair<List<Person>, String?>> {
        val (response, error) = personDataSource.fetch(next)
        if (error != null) return Result.Error(error.errorDescription)

        if (response == null) return Result.Error("Response can not be null.")
        return Result.Success(response.data to response.next)
    }
}
