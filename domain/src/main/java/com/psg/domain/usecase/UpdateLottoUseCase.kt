package com.psg.domain.usecase

import com.psg.domain.model.Lotto
import com.psg.domain.repository.AppRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class UpdateLottoUseCase(
    private val repository: AppRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Lotto, Unit>(coroutineDispatcher) {
    override fun execute(parameter: Lotto): Flow<Result<Unit>> {
        return repository.updateLotto(parameter)
    }

}