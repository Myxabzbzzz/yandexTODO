package com.example.yandextodo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val LightColorScheme = lightColorScheme(
    outline = md_theme_light_support_separator,
    outlineVariant = md_theme_light_support_overlay,
    primary = md_theme_light_green,
    primaryContainer = md_theme_light_blue,
    secondary = md_theme_light_gray,
    tertiary = md_theme_light_gray_light,
    background = md_theme_light_background_primary,
    surface = md_theme_light_background_secondary,
    surfaceVariant = md_theme_light_background_elevated,
    error = md_theme_light_red,
    onPrimaryContainer = md_theme_light_label_primary,
    onSecondaryContainer = md_theme_light_label_secondary,
    onTertiaryContainer = md_theme_light_label_tertiary,
    onSurfaceVariant = md_theme_light_label_disabled,
    inverseOnSurface = md_white,
    surfaceTint = Color.Transparent
)

private val DarkColorScheme = darkColorScheme(
    outline = md_theme_dark_support_separator,
    outlineVariant = md_theme_dark_support_overlay,
    primary = md_theme_dark_green,
    primaryContainer = md_theme_dark_blue,
    secondary = md_theme_dark_gray,
    tertiary = md_theme_dark_gray_light,
    background = md_theme_dark_background_primary,
    surface = md_theme_dark_background_secondary,
    surfaceVariant = md_theme_dark_background_elevated,
    error = md_theme_dark_red,
    onPrimaryContainer = md_theme_dark_label_primary,
    onSecondaryContainer = md_theme_dark_label_secondary,
    onTertiaryContainer = md_theme_dark_label_tertiary,
    onSurfaceVariant = md_theme_dark_label_disabled,
    inverseOnSurface = md_white,
    surfaceTint = Color.Transparent
)

@Composable
fun ToDoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    onThemeChange: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()

            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}