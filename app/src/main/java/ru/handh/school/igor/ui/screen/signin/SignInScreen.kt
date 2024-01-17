package ru.handh.school.igor.ui.screen.signin

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.handh.school.igor.R
import ru.handh.school.igor.domain.results.ResultAuth
import ru.handh.school.igor.ui.components.AppButton
import ru.handh.school.igor.ui.components.AppTextField
import ru.handh.school.igor.ui.theme.AppTheme

@Composable
fun SignInScreen(
    vm: SignInViewModel,
    navController: NavController,
    context: Context
) {
    LaunchedEffect(vm) {
        vm.codeResult.collect { result ->
            when (result) {
                is ResultAuth.UserAuth -> {
                    Log.d("SessionCollect", "UserAuth result received")
                    Toast.makeText(context, R.string.email_send, Toast.LENGTH_LONG).show()
                }

                is ResultAuth.ReceivedSession -> {
                    Log.d("SessionCollect", "ReceivedSession result received")
                    Toast.makeText(context, R.string.you_logged, Toast.LENGTH_LONG).show()
                    navController.navigate("homepage")
                }

                is ResultAuth.UnknownError -> {
                    Log.d("SessionCollect", "UnknownError result received")
                    Toast.makeText(context, R.string.error_occurred, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    val state by vm.state.collectAsState()
    SignInContent(
        state = state,
        onAction = vm::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignInContent(
    state: SignInState,
    onAction: (SignInViewAction) -> Unit = {},
) {
    val quarterScreenHeight = LocalConfiguration.current.screenHeightDp.dp / 8

    Scaffold { containerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.surface)
                .padding(top = quarterScreenHeight)
                .padding(containerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppTheme.offsets.medium)
            ) {
                BasicText(
                    modifier = Modifier.padding(bottom = AppTheme.offsets.huge),
                    text = stringResource(R.string.login),
                    style = AppTheme.textStyles.text2
                )

                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = AppTheme.offsets.large),
                    hint = stringResource(R.string.email),
                    value = state.email,
                    onValueChange = {
                        onAction(SignInViewAction.UpdateEmail(it))
                    }
                )

                AnimatedVisibility(visible = state.showVerificationCodeInput) {
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = AppTheme.offsets.large),
                        hint = stringResource(R.string.verification_code),
                        value = state.code,
                        onValueChange = {
                            onAction(SignInViewAction.UpdateVerificationCode(it))
                        }
                    )
                }

                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(R.string.loginButton),
                    loading = state.signInLoading,
                    enabled = state.buttonEnabled,
                    onClick = {
                        if (state.showVerificationCodeInput) {
                            onAction(SignInViewAction.SubmitClickedCode)
                        } else {
                            onAction(SignInViewAction.SubmitClicked)
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignInContentPreview() {
    SignInContent(
        state = InitialSignInState
    )
}
