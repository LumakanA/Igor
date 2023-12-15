package ru.handh.school.igor.ui.screen.signin

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.handh.school.igor.R
import ru.handh.school.igor.ui.components.AppButton
import ru.handh.school.igor.ui.components.AppTextField
import ru.handh.school.igor.ui.theme.AppTheme

@Composable
fun SignInScreen(
    vm: SignInViewModel,
    navController: NavController
) {
//    LaunchedEffect(vm.) {
//        navController.navigate("profile")
//    }
    val state by vm.state.collectAsState()
    SignInContent(
        state = state,
        onAction = vm::onAction,
        navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignInContent(
    state: SignInState,
    onAction: (SignInViewAction) -> Unit = {},
    navController: NavController
) {
    val quarterScreenHeight = with(LocalDensity.current) {
        (LocalConfiguration.current.screenHeightDp.dp / 8)
    }
    Scaffold { containerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = quarterScreenHeight)
                .padding(containerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppTheme.offsets.medium)
                    .verticalScroll(rememberScrollState()) /* работает не так как надо
                     * элементы уходят за невидимую границу
                     * последний элемент обрезается */
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
                    onClick = {
                        if (state.showVerificationCodeInput) {
                            Log.d("qwerty", "nav_to_prof_button")
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
        state = InitialSignInState,
        navController = rememberNavController()
    )
}
