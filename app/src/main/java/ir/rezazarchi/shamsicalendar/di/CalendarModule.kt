package ir.rezazarchi.shamsicalendar.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import io.github.persiancalendar.calendar.CivilDate
import io.github.persiancalendar.calendar.IslamicDate
import io.github.persiancalendar.calendar.PersianDate
import ir.rezazarchi.shamsicalendar.R
import ir.rezazarchi.shamsicalendar.utils.PersianDateItems.persianMonthsArray
import ir.rezazarchi.shamsicalendar.utils.PersianDateItems.persianWeekDaysArray
import ir.rezazarchi.shamsicalendar.utils.Utils.toPersianNumbers
import ir.rezazarchi.shamsicalendar.utils.model.Event
import java.util.Calendar
import javax.inject.Qualifier

@Qualifier
annotation class FullJalaliDate

@Module
@InstallIn(ServiceComponent::class)
object CalendarModule {
    @FullJalaliDate
    @Provides
    fun provideFullJalaliDateString(): String {
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

    @Provides
    fun provideCurrentDayEvents(context: Context): Event {
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
}