package com.github.jpohlmeyer.scorekeeperkotlin.screens.selectplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

data class TouchPoint(
    val pointerId: Int,
    val x: Float,
    val y: Float,
    val color: Int
)

@HiltViewModel
class SelectPlayerViewModel @Inject constructor() : ViewModel() {

    private val _touchPoints = MutableLiveData<Map<Int, TouchPoint>>(emptyMap())
    val touchPoints: LiveData<Map<Int, TouchPoint>> get() = _touchPoints

    private val _selectedPointerId = MutableLiveData<Int?>(null)
    val selectedPointerId: LiveData<Int?> get() = _selectedPointerId

    private var countdownJob: Job? = null

    private val colors = listOf(
        android.graphics.Color.RED,
        android.graphics.Color.BLUE,
        android.graphics.Color.GREEN,
        android.graphics.Color.YELLOW,
        android.graphics.Color.MAGENTA,
        android.graphics.Color.CYAN,
        android.graphics.Color.rgb(255, 165, 0), // Orange
        android.graphics.Color.rgb(128, 0, 128), // Purple
        android.graphics.Color.rgb(255, 192, 203), // Pink
        android.graphics.Color.rgb(0, 255, 127) // Spring Green
    )

    private val activeColors = mutableMapOf<Int, Int>()
    private val availableColors = colors.toMutableList()

    fun updateTouch(pointerId: Int, x: Float, y: Float, isRemoved: Boolean) {
        if (_selectedPointerId.value != null) return // Already selected a player

        val currentMap = _touchPoints.value.orEmpty().toMutableMap()
        val countBefore = currentMap.size

        if (isRemoved) {
            currentMap.remove(pointerId)
            activeColors.remove(pointerId)?.let { color ->
                availableColors.add(color)
            }
        } else {
            val color = activeColors.getOrPut(pointerId) {
                if (availableColors.isNotEmpty()) {
                    availableColors.removeAt(0)
                } else {
                    colors.random()
                }
            }
            currentMap[pointerId] = TouchPoint(pointerId, x, y, color)
        }

        _touchPoints.value = currentMap
        val countAfter = currentMap.size

        // If fingers count changed or fingers added/removed, restart countdown timer
        if (countBefore != countAfter || isRemoved) {
            restartCountdown(currentMap)
        } else if (currentMap.isEmpty()) {
            cancelCountdown()
        }
    }

    private fun restartCountdown(currentMap: Map<Int, TouchPoint>) {
        countdownJob?.cancel()
        if (currentMap.size < 2) {
            _selectedPointerId.value = null
            return
        }

        countdownJob = viewModelScope.launch {
            delay(3000L.milliseconds) // 3 seconds of no fingers added or removed
            if (currentMap.isNotEmpty() && _selectedPointerId.value == null) {
                val keys = currentMap.keys.toList()
                val randomKey = keys[Random.nextInt(keys.size)]
                _selectedPointerId.value = randomKey
            }
        }
    }

    private fun cancelCountdown() {
        countdownJob?.cancel()
        countdownJob = null
        _selectedPointerId.value = null
    }

    fun resetSelection() {
        cancelCountdown()
        activeColors.clear()
        availableColors.clear()
        availableColors.addAll(colors)
        _touchPoints.value = emptyMap()
        _selectedPointerId.value = null
    }
}
