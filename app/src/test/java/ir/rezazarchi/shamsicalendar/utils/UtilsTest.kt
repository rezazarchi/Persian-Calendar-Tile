package ir.rezazarchi.shamsicalendar.utils

import android.graphics.Color
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import ir.rezazarchi.shamsicalendar.utils.Utils.anyEventIsHoliday
import ir.rezazarchi.shamsicalendar.utils.Utils.getCurrentDayColor
import ir.rezazarchi.shamsicalendar.utils.Utils.getEventsDescription
import ir.rezazarchi.shamsicalendar.utils.model.CalendarEvent
import ir.rezazarchi.shamsicalendar.utils.model.Event


class UtilsTest : FreeSpec({

    val farvardin1th =
        CalendarEvent(day = 1, holiday = true, month = 1, title = "آغاز نوروز", type = "")

    val farvardin10th =
        CalendarEvent(day = 10, holiday = false, month = 1, title = "", type = "")

    "Event description" - {
        "empty event description" {
            val description = getEventsDescription(
                events = Event(
                    persianCalendar = listOf(),
                    gregorianCalendar = listOf(),
                    hijriCalendar = listOf(),
                )
            )

            description.isEmpty()
        }
        "Farvardin 1th description" {
            val description = getEventsDescription(
                events = Event(
                    persianCalendar = listOf(farvardin1th),
                    gregorianCalendar = listOf(),
                    hijriCalendar = listOf(),
                )
            )

            description shouldBeEqual farvardin1th.title
        }
    }

    "Event is holiday" - {
        "Farvardin 1th should be holiday" {
            val isHoliday = anyEventIsHoliday(
                events = Event(
                    persianCalendar = listOf(farvardin1th),
                    gregorianCalendar = listOf(),
                    hijriCalendar = listOf(),
                )
            )

            isHoliday shouldBe true
        }
        "Farvardin 10th should be holiday" {
            val isHoliday = anyEventIsHoliday(
                events = Event(
                    persianCalendar = listOf(farvardin10th),
                    gregorianCalendar = listOf(),
                    hijriCalendar = listOf(),
                )
            )

            isHoliday shouldBe false
        }
    }

    "Day color" - {
        "Holiday color should be Red" {
            val color = getCurrentDayColor(anyEventIsHoliday = true)
            color shouldBe Color.RED
        }
        "Regular day color should be White" {
            val color = getCurrentDayColor(anyEventIsHoliday = false)
            color shouldBe Color.WHITE
        }
    }
})