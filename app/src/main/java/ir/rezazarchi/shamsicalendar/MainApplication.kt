package ir.rezazarchi.shamsicalendar

import android.app.Application
import ir.rezazarchi.shamsicalendar.di.calendarModules
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup.onKoinStartup

class MainApplication : Application() {

    init {
        onKoinStartup {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    calendarModules,
                )
            )
        }
    }

}