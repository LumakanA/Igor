package ru.handh.school.igor.ui.screen.signin

import ru.handh.school.igor.ui.base.BaseViewModel

class SignInViewModel : BaseViewModel<SignInState, SignInViewAction>(InitialSignInState) {

    override fun onAction(action: SignInViewAction) =
        when (action) {
            SignInViewAction.SubmitClicked -> onSubmitClicked()
            is SignInViewAction.UpdateUsername -> onUpdateUsername(action.username)
        }

    private fun onSubmitClicked() = reduceState {
        it.copy(signInLoading = !it.signInLoading)
    }
    private fun onUpdateUsername(username: String) = reduceState {
        it.copy(username = username)
    }
}
