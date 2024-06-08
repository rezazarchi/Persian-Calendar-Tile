package ir.rezazarchi.shamsicalendar.tile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.glance.unit.ColorProvider
import ir.rezazarchi.shamsicalendar.utils.model.Event

class CalendarTileStateHolder(fullJalaliDateString: String, event: Event) {

    companion object {
        @Composable
        fun rememberCalendarTileState(
            fullJalaliDateString: String,
            event: Event
        ): CalendarTileStateHolder =
            remember {
                CalendarTileStateHolder(fullJalaliDateString, event)
            }
    }

    val fullJalaliDateString by mutableStateOf(fullJalaliDateString)

    val events by mutableStateOf(event)

    val textColor by
    derivedStateOf {
        ColorProvider(if (events.hasAnyHoliday) Color.Red else Color.White)
    }

    val hasEvent by
    derivedStateOf {
        events.eventsDescription.isNotBlank()
    }
}