package ir.rezazarchi.shamsicalendar.utils

import android.graphics.Color
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import ir.rezazarchi.shamsicalendar.utils.Utils.getCurrentDayColor


class UtilsTest : FreeSpec({

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