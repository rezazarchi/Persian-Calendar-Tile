package ir.rezazarchi.shamsicalendar.tile

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.wear.tiles.GlanceTileService
import androidx.wear.tiles.EventBuilders
import ir.rezazarchi.shamsicalendar.tile.CalendarTileStateHolder.Companion.rememberCalendarTileState
import ir.rezazarchi.shamsicalendar.utils.Utils.getCurrentDayEvents
import ir.rezazarchi.shamsicalendar.utils.Utils.getFullJalaliDateString

/**
 * Skeleton for a tile with no images.
 */
class MainTileService : GlanceTileService() {

    override fun onTileEnterEvent(requestParams: EventBuilders.TileEnterEvent) {
        getUpdater(this).requestUpdate(MainTileService::class.java)
    }

    @Composable
    override fun Content() {
        val calendarTileState = rememberCalendarTileState(
            getFullJalaliDateString(),
            getCurrentDayEvents(applicationContext)
        )
        CalendarTile(state = calendarTileState)
    }

    @Composable
    fun CalendarTile(state: CalendarTileStateHolder) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = state.fullJalaliDateString,
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = state.textColor
                )
            )
            if (state.hasEvent) {
                Spacer(modifier = GlanceModifier.height(24.dp))
                Text(
                    modifier = GlanceModifier.padding(horizontal = 16.dp),
                    text = state.events.eventsDescription,
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = state.textColor
                    )
                )
            }
        }
    }
}