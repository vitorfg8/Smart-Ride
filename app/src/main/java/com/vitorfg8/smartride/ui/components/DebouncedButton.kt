package com.vitorfg8.smartride.ui.components

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DebouncedButton(
    onClick: () -> Unit,
    debounceTime: Long = 300L,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var isClickable by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    Button(
        onClick = {
            if (isClickable) {
                onClick()
                isClickable = false
                // Reativar o clique após o tempo de debounce
                coroutineScope.launch {
                    delay(debounceTime)
                    isClickable = true
                }
            }
        },
        modifier = modifier,
        enabled = isClickable
    ) {
        content()
    }
}