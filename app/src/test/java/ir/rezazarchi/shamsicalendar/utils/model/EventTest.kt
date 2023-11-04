package ir.rezazarchi.shamsicalendar.utils.model

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe

class EventTest : FreeSpec({

    val farvardin1th =
        CalendarEvent(day = 1, holiday = true, month = 1, title = "آغاز نوروز", type = "")

    val farvardin10th =
        CalendarEvent(day = 10, holiday = false, month = 1, title = "", type = "")

    val emptyEvent = Event(
        persianCalendar = listOf(),
        gregorianCalendar = listOf(),
        hijriCalendar = listOf(),
    )

    val farvardin1thEvent = Event(
        persianCalendar = listOf(farvardin1th),
        gregorianCalendar = listOf(),
        hijriCalendar = listOf(),
    )

    val farvardin10thEvent = Event(
        persianCalendar = listOf(farvardin10th),
        gregorianCalendar = listOf(),
        hijriCalendar = listOf(),
    )

    "Event description" - {
        "empty event description" {
            val description = emptyEvent.eventsDescription
            description.isEmpty()
        }
        "Farvardin 1th description" {
            val description = farvardin1thEvent.eventsDescription
            description shouldBeEqual farvardin1th.title
        }
    }

    "Event is holiday" - {
        "Farvardin 1th should be holiday" {
            val isHoliday = farvardin1thEvent.hasAnyHoliday
            isHoliday shouldBe farvardin1th.holiday
        }
        "Farvardin 10th should be holiday" {
            val isHoliday = farvardin10thEvent.hasAnyHoliday
            isHoliday shouldBe farvardin10th.holiday
        }
    }
})
