package com.example.timeryt.model

import java.lang.StringBuilder

data class FullTime(
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0
) {
    fun toSeconds() = seconds + minutes * 60 + hours * 3600

    fun minusSecond() = fromSeconds(toSeconds() - 1)

    fun isDefault() = seconds == 0 && minutes == 0 && hours == 0

    override fun toString(): String {
        val time = StringBuilder()
        if (hours != 0) {
            time.append(if (hours > 10) hours else "0$hours")
            time.append(":")
            time.append(if (minutes > 10) minutes else "0$minutes")
            time.append(":")
            time.append(if (seconds > 10) seconds else "0$seconds")
        }
        else if (minutes != 0) {
            time.append(if (minutes > 10) minutes else "0$minutes")
            time.append(":")
            time.append(if (seconds > 10) seconds else "0$seconds")
        }
        else if (seconds != 0) {
            time.append(seconds)
        }
        return time.toString()
    }

    companion object {
        fun fromSeconds(seconds: Int): FullTime {
            val hours = seconds / 3600
            val minutes = (seconds - 3600 * hours) / 60
            val secondsValue = seconds - hours * 3600 - minutes * 60
            return FullTime(
                hours = hours,
                minutes = minutes,
                seconds = secondsValue
            )
        }
    }
}