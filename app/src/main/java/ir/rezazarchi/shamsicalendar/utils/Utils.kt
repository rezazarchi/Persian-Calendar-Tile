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

    fun getCurrentDayColor(anyEventIsHoliday: Boolean): Int {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return if (dayOfWeek == Calendar.FRIDAY || anyEventIsHoliday) {
            Color.RED
        } else {
            Color.WHITE
        }
    }

    fun String.toPersianNumbers(): String {
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