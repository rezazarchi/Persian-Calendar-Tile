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
