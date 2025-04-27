package app.ynemreuslu.prayertimes.domain.usescase.skipbutton

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class SetSkipButtonStateUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    suspend operator fun invoke(isEnabled: Boolean) {
        repository.setSkipButtonState(isEnabled)
    }
}