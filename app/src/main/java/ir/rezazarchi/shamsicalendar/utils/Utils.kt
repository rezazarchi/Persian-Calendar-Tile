package ir.rezazarchi.shamsicalendar.utils

import android.graphics.Color
import io.github.persiancalendar.calendar.CivilDate
import io.github.persiancalendar.calendar.PersianDate
import ir.rezazarchi.shamsicalendar.utils.PersianDateItems.persianMonthsArray
import ir.rezazarchi.shamsicalendar.utils.PersianDateItems.persianWeekDaysArray
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

    fun getCurrentDayColor(): Int {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return if (dayOfWeek == Calendar.FRIDAY) {
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

}