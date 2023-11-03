package ir.rezazarchi.shamsicalendar.tile

import androidx.wear.protolayout.ColorBuilders.argb
import androidx.wear.protolayout.DimensionBuilders
import androidx.wear.protolayout.DimensionBuilders.expand
import androidx.wear.protolayout.DimensionBuilders.wrap
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.LayoutElementBuilders.HORIZONTAL_ALIGN_CENTER
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.ResourceBuilders.Resources
import androidx.wear.protolayout.TimelineBuilders.Timeline
import androidx.wear.protolayout.material.Text
import androidx.wear.protolayout.material.Typography
import androidx.wear.tiles.EventBuilders
import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.TileBuilders.Tile
import androidx.wear.tiles.TileService
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import ir.rezazarchi.shamsicalendar.utils.Utils.getCurrentDayColor
import ir.rezazarchi.shamsicalendar.utils.Utils.getCurrentDayEvents
import ir.rezazarchi.shamsicalendar.utils.Utils.getFullJalaliDateString
import java.lang.Integer.MAX_VALUE

private const val RESOURCES_VERSION = "1"

/**
 * Skeleton for a tile with no images.
 */
class MainTileService : TileService() {

    override fun onTileEnterEvent(requestParams: EventBuilders.TileEnterEvent) {
        getUpdater(this).requestUpdate(MainTileService::class.java)
    }

    override fun onTileRequest(requestParams: RequestBuilders.TileRequest): ListenableFuture<Tile> {
        val events = getCurrentDayEvents(applicationContext)
        return Futures.immediateFuture(
            Tile.Builder()
                .setResourcesVersion(RESOURCES_VERSION)
                .setTileTimeline(
                    Timeline.fromLayoutElement(
                        LayoutElementBuilders.Column.Builder()
                            .setWidth(expand())
                            .setHeight(wrap())
                            .setHorizontalAlignment(HORIZONTAL_ALIGN_CENTER)
                            .apply {
                                val hasAnyHoliday = events.hasAnyHoliday
                                this.addContent(
                                    Text.Builder(applicationContext, getFullJalaliDateString())
                                        .setMaxLines(2)
                                        .setTypography(Typography.TYPOGRAPHY_DISPLAY3)
                                        .setColor(argb(getCurrentDayColor(hasAnyHoliday)))
                                        .build()
                                )
                                val eventsDescription = events.eventsDescription
                                if (eventsDescription.isNotBlank()) {
                                    this.addContent(
                                        Text.Builder(applicationContext, eventsDescription)
                                            .setTypography(Typography.TYPOGRAPHY_CAPTION3)
                                            .setColor(argb(getCurrentDayColor(hasAnyHoliday)))
                                            .setMaxLines(MAX_VALUE)
                                            .setModifiers(
                                                ModifiersBuilders.Modifiers.Builder().setPadding(
                                                    ModifiersBuilders.Padding.Builder()
                                                        .setAll(DimensionBuilders.dp(16f))
                                                        .setRtlAware(true)
                                                        .build()
                                                ).build()
                                            )
                                            .build()
                                    )
                                }
                            }
                            .build()
                    )
                )
                .build()
        )
    }

    override fun onTileResourcesRequest(requestParams: RequestBuilders.ResourcesRequest)
            : ListenableFuture<Resources> {
        return Futures.immediateFuture(
            Resources.Builder()
                .setVersion(RESOURCES_VERSION)
                .build()
        )
    }

}