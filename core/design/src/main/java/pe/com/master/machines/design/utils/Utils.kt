package pe.com.master.machines.design.utils

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.bitmapConfig
import coil3.request.crossfade
import coil3.size.Scale
import java.io.IOException

object Utils {

    val forwardEnterTransition: EnterTransition
        get() = fadeIn(tween(1000))
    val forwardExitTransition: ExitTransition
        get() = fadeOut(tween(1000))
    val backEnterTransition: EnterTransition
        get() {
            val easing = FastOutLinearInEasing // Cambiar a una curva de aceleración más suave
            return slideInHorizontally(
                initialOffsetX = { -600 }, // Desliza desde la izquierda
                animationSpec = tween(
                    1000,
                    easing = easing
                ) // Aumentar la duración para mayor suavidad
            ) + fadeIn(
                initialAlpha = 0.0f, // Comenzar completamente transparente
                animationSpec = tween(1000, easing = easing)
            ) + scaleIn(
                initialScale = 0.95f, // Comenzar un poco más pequeño
                animationSpec = tween(1000, easing = easing)
            )
        }

    val backExitTransition: ExitTransition
        get() {
            val easing = FastOutLinearInEasing // Cambiar a una curva de aceleración más suave
            return slideOutHorizontally(
                targetOffsetX = { 600 }, // Desliza hacia la derecha
                animationSpec = tween(
                    1000,
                    easing = easing
                ) // Aumentar la duración para mayor suavidad
            ) + fadeOut(
                targetAlpha = 0.0f, // Terminar completamente transparente
                animationSpec = tween(1000, easing = easing)
            ) + scaleOut(
                targetScale = 0.95f, // Terminar un poco más pequeño
                animationSpec = tween(1000, easing = easing)
            )
        }

    @get:RequiresPermission(anyOf = [Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET])
    val isInternetReachable: Boolean
        get() = try {
            val p = Runtime.getRuntime().exec("ping -c 1 www.google.com")
            val valor = p.waitFor()
            valor == 0
        } catch (e: IOException) {
            false
        }

    fun getModelImage(context: Context, photoUrl: String) =
        ImageRequest.Builder(context)
            .data(photoUrl)
            .crossfade(true)
            .scale(Scale.FIT)
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .allowHardware(true)
            .memoryCacheKey(photoUrl)
            .diskCacheKey(photoUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .networkCachePolicy(CachePolicy.ENABLED)
            .build()

    val Context.isConnected: Boolean
        @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
        get() {
            val connectivityManager =
                this.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
                    ?: return false

            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            // Se comprueban las capacidades más importantes
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        }
}