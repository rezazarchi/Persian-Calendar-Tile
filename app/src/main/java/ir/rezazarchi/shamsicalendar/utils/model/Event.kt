package ir.rezazarchi.shamsicalendar.utils.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Event(
    @SerializedName("Persian Calendar")
    val persianCalendar: List<CalendarEvent>,
    @SerializedName("Gregorian Calendar")
    val gregorianCalendar: List<CalendarEvent>,
    @SerializedName("Hijri Calendar")
    val hijriCalendar: List<CalendarEvent>,
) {
    val eventsDescription: String
        get() {
            return entries.joinToString("\n") {
                it(this).joinToString("، ") { event ->
                    event.title
                }
            }.trim()
        }

    val hasAnyHoliday: Boolean get() = entries.any { it(this).any { it.holiday } }

    companion object {
        private val entries =
            listOf(Event::hijriCalendar, Event::persianCalendar, Event::gregorianCalendar)
    }
}