package ir.rezazarchi.shamsicalendar.utils

import android.content.Context
import android.graphics.Color
import com.google.gson.Gson
import io.github.persiancalendar.calendar.CivilDate
import io.github.persiancalendar.calendar.IslamicDate
import io.github.persiancalendar.calendar.PersianDate
import ir.rezazarchi.shamsicalendar.R
import ir.rezazarchi.shamsicalendar.utils.PersianDateItems.persianMonthsArray
import ir.rezazarchi.shamsicalendar.utils.PersianDateItems.persianWeekDaysArray
import ir.rezazarchi.shamsicalendar.utils.model.Event
import java.util.Calendar

object Utils {

    fun getFullJalaliDateString(): String {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val civilCal = CivilDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        val persianCal = PersianDate(civilCal.toJdn())
        val dateText = StringBuilder()
        dateText.append("${persianWeekDaysArray[dayOfWeek]}\n")
        dateText.append(persianCal.dayOfMonth)
        dateText.append(" ${persianMonthsArray[persianCal.month - 1]} ")
        dateText.append(persianCal.year)
        return dateText.toString().toPersianNumbers()
    }

    fun getCurrentDayColor(anyEventIsHoliday: Boolean): Int {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return if (dayOfWeek == Calendar.FRIDAY || anyEventIsHoliday) {
            Color.RED
        } else {
            Color.WHITE
        }
    }

    private fun String.toPersianNumbers(): String {
        return replace("1", "۱")
            .replace("2", "۲")
            .replace("3", "۳")
            .replace("4", "۴")
            .replace("5", "۵")
            .replace("6", "۶")
            .replace("7", "۷")
            .replace("8", "۸")
            .replace("9", "۹")
            .replace("0", "۰")
    }

    fun getCurrentDayEvents(context: Context): Event {
        val calendar = Calendar.getInstance()
        val jdn = CivilDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        ).toJdn()
        val eventReader = context.resources.openRawResource(R.raw.events).bufferedReader()
        val event = Gson().fromJson(eventReader, Event::class.java)
        val persianDate = PersianDate(jdn)
        val hijriDate = IslamicDate(jdn)
        val miladiDate = CivilDate(jdn)
        return Event(
            event.persianCalendar.filter {
                it.day == persianDate.dayOfMonth && it.month == persianDate.month
            },
            event.gregorianCalendar.filter {
                it.day == miladiDate.dayOfMonth && it.month == miladiDate.month
            },
            event.hijriCalendar.filter {
                it.day == hijriDate.dayOfMonth && it.month == hijriDate.month
            }
        )
    }

    fun getEventsDescription(events: Event): String {
        return listOf(
            events.hijriCalendar,
            events.persianCalendar,
            events.gregorianCalendar
        ).joinToString("\n") {
            it.joinToString("، ") { event ->
                event.title
            }
        }
    }

    fun anyEventIsHoliday(events: Event): Boolean {
        var isHoliday = false
        events.persianCalendar.forEach {
            isHoliday = it.holiday || isHoliday
        }
        events.hijriCalendar.forEach {
            isHoliday = it.holiday || isHoliday
        }
        events.gregorianCalendar.forEach {
            isHoliday = it.holiday || isHoliday
        }
        return isHoliday
    }

}