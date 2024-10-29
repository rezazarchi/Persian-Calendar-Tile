package ir.rezazarchi.shamsicalendar

import android.app.Application
import ir.rezazarchi.shamsicalendar.di.calendarModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    calendarModules,
                )
            )
        }
    }

}