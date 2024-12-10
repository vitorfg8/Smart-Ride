package com.vitorfg8.smartride.ui.rideoptions

sealed class RideOptionsEvent {
    object GoBack : RideOptionsEvent()
    object NavigateToHistory : RideOptionsEvent()
}