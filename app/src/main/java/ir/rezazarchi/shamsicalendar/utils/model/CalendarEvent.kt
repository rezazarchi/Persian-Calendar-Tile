package ir.rezazarchi.shamsicalendar.utils.model

data class CalendarEvent(
    val day: Int,
    val holiday: Boolean,
    val month: Int,
    val title: String,
    val type: String,
)