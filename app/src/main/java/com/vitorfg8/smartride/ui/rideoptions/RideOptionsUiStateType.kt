package com.vitorfg8.smartride.ui.rideoptions

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.json.Json


object RideOptionsUiStateType {

    val rideOptionsUiStateType = object : NavType<RideOptionsUiState>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): RideOptionsUiState? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): RideOptionsUiState {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: RideOptionsUiState): String {
            return Uri.encode(Json.encodeToString(RideOptionsUiState.serializer(), value))
        }

        override fun put(bundle: Bundle, key: String, value: RideOptionsUiState) {
            bundle.putString(key, Json.encodeToString(RideOptionsUiState.serializer(), value))
        }
    }
}