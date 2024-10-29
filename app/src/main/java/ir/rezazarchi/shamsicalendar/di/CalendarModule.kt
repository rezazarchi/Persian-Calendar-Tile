package ir.rezazarchi.shamsicalendar.di

import ir.rezazarchi.shamsicalendar.utils.Utils
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val FULL_JALALI_DATE = "FullJalaliDateString"
const val CURRENT_DAY_EVENTS = "CurrentDayEvents"

val calendarModules = module {
    factory<String>(named(FULL_JALALI_DATE)) {
        Utils.getFullJalaliDateString()
    }
    factoryOf(Utils::getCurrentDayEvents)
}

//object CalendarModule {
//    fun provideFullJalaliDateString(): String {
//        val calendar = Calendar.getInstance()
//        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
//        val civilCal = CivilDate(
//            calendar.get(Calendar.YEAR),
//            calendar.get(Calendar.MONTH) + 1,
//            calendar.get(Calendar.DAY_OF_MONTH)
//        )
//        val persianCal = PersianDate(civilCal.toJdn())
//        val dateText = StringBuilder()
//        dateText.append("${persianWeekDaysArray[dayOfWeek]}\n")
//        dateText.append(persianCal.dayOfMonth)
//        dateText.append(" ${persianMonthsArray[persianCal.month - 1]} ")
//        dateText.append(persianCal.year)
//        return dateText.toString().toPersianNumbers()
//    }
//
//    fun provideCurrentDayEvents(context: Context): Event {
//        val calendar = Calendar.getInstance()
//        val jdn = CivilDate(
//            calendar.get(Calendar.YEAR),
//            calendar.get(Calendar.MONTH) + 1,
//            calendar.get(Calendar.DAY_OF_MONTH)
//        ).toJdn()
//        val eventReader = context.resources.openRawResource(R.raw.events).bufferedReader()
//        val event = Gson().fromJson(eventReader, Event::class.java)
//        val persianDate = PersianDate(jdn)
//        val hijriDate = IslamicDate(jdn)
//        val miladiDate = CivilDate(jdn)
//        return Event(
//            event.persianCalendar.filter {
//                it.day == persianDate.dayOfMonth && it.month == persianDate.month
//            },
//            event.gregorianCalendar.filter {
//                it.day == miladiDate.dayOfMonth && it.month == miladiDate.month
//            },
//            event.hijriCalendar.filter {
//                it.day == hijriDate.dayOfMonth && it.month == hijriDate.month
//            }
//        )
//    }
//}