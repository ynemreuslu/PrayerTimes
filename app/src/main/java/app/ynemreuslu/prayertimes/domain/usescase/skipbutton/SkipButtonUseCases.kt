package app.ynemreuslu.prayertimes.domain.usescase.skipbutton

import javax.inject.Inject

data class SkipButtonUseCases @Inject constructor(
    val setSkipButtonState: SetSkipButtonStateUseCase,
    val isSkipButtonEnabled: IsSkipButtonEnabledUseCase
)