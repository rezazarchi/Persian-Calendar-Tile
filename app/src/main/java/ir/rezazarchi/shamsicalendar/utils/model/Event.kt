package ir.rezazarchi.shamsicalendar.utils.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("Persian Calendar")
    val persianCalendar: List<CalendarEvent>,
    @SerializedName("Gregorian Calendar")
    val gregorianCalendar: List<CalendarEvent>,
    @SerializedName("Hijri Calendar")
    val hijriCalendar: List<CalendarEvent>,
)