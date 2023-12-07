package ru.handh.school.igor.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.handh.school.igor.R
import ru.handh.school.igor.ui.screen.signin.SignInScreen
import ru.handh.school.igor.ui.screen.signin.SignInViewModel
import ru.handh.school.igor.ui.theme.AppTheme

/**
 * Это Активити выступает как стартовая точка нашего приложения. В данном проекте используется
 * Compose, поэтому особенности отображения конкретных экранов смотрите в соответствующих модулях.
 *
 * В приложении используется "полноэкранный режим" и активно используются инсеты, поэтому в Активити
 * происходит предварительная настройка этого режима.
 *
 * В зависимости от версии SDK API устройства системные бары на пост-сплэш теме будут такими:
 *
 * Lollipop – pre-Marshmallow: оба системных бара темные
 *
 * Marhmallow – pre-Oreo: статус-бар прозрачный, навигационный бар темный
 *
 * Oreo – (...): оба системных бара прозрачные
 */
class MainActivity : ComponentActivity() {

    private val shouldSplashScreenDismiss: Boolean
        get() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupWindow()
        setupRootComponent()
    }

    /**
     * Переводим наше приложение в "полноэкранный режим", когда наш контент располагается под
     * системными барами и его расположением мы управляем с помощью WindowInsets
     *
     * Устанавливаем сплэш из Splash API
     */
    private fun setupWindow() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
    }

    /**
     * Создается корневой компонент приложения, устанавливается глобальная тема Compose и задается
     * стартовая точка входа в экраны приложения.
     *
     * Именно в [RootComponent] определяется, перейдем ли мы при старте на экран авторизации или
     * же на стартовые экраны авторизованного пользователя.
     *
     * Глобальная тема Compose позволяет нам в будущем для всех вложенных реализаций @Composable
     * использовать кастомные атрибуты, такие как смещения или цвета. Так же в рамках глобальной
     * темы происходит первоначальная настройка таких параметров, как цвет системного и
     * навигационного бара. Подробнее: [AppTheme]
     */
    private fun setupRootComponent() {
        setContent {
            AppTheme {
                SignInScreen(vm = viewModel())
            }
        }
        keepSplashScreenUntilAllComplete()
    }

    /**
     * Splash API закроет сплэш если приложение нарисует первый фрейм.
     *
     * Поэтому мы приостанавливаем отмену сплэша до того момента, пока не будет загружен граф
     * зависимостей. Подробнее: [shouldSplashScreenDismiss]
     *
     * Так же производится ручная установка темы для API < 31.
     * Подробнее: [manualApplyPostSplashScreenThemeForOlderApis]
     */
    private fun keepSplashScreenUntilAllComplete() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener {
            if (shouldSplashScreenDismiss) {
                manualApplyPostSplashScreenThemeForOlderApis()
                true
            } else {
                false
            }
        }
    }

    /**
     * Вручную делаем то, что Splash API делает автоматически с помощью атрибута
     * postSplashScreenTheme темы
     */
    private fun manualApplyPostSplashScreenThemeForOlderApis() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            window.setBackgroundDrawableResource(R.color.window_background)
            window.statusBarColor =
                ResourcesCompat.getColor(resources, R.color.status_bar_color, null)
            window.navigationBarColor =
                ResourcesCompat.getColor(resources, R.color.navigation_bar_color, null)
            WindowInsetsControllerCompat(window, window.decorView).apply {
                isAppearanceLightStatusBars = true
                isAppearanceLightNavigationBars = true
            }
        }
    }
}
