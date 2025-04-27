package app.ynemreuslu.prayertimes.domain.usescase.skipbutton

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class IsSkipButtonEnabledUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    operator fun invoke(): Boolean {
        return repository.isSkipButtonEnabled()
    }
}