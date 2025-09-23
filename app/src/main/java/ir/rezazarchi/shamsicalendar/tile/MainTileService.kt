package ir.rezazarchi.shamsicalendar.tile

import androidx.wear.protolayout.DimensionBuilders.expand
import androidx.wear.protolayout.DimensionBuilders.wrap
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.LayoutElementBuilders.HORIZONTAL_ALIGN_CENTER
import androidx.wear.protolayout.ResourceBuilders.Resources
import androidx.wear.protolayout.TimelineBuilders.Timeline
import androidx.wear.protolayout.material3.Typography
import androidx.wear.protolayout.material3.materialScope
import androidx.wear.protolayout.material3.primaryLayout
import androidx.wear.protolayout.material3.text
import androidx.wear.protolayout.modifiers.LayoutModifier
import androidx.wear.protolayout.modifiers.padding
import androidx.wear.protolayout.types.LayoutColor
import androidx.wear.protolayout.types.layoutString
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
                        materialScope(
                            applicationContext,
                            deviceConfiguration = requestParams.deviceConfiguration,
                        ) {
                            val hasAnyHoliday = events.hasAnyHoliday
                            primaryLayout(
                                titleSlot = {
                                    text(
                                        getFullJalaliDateString().layoutString,
                                        maxLines = 2,
                                        typography = Typography.DISPLAY_SMALL,
                                        color = LayoutColor(
                                            getCurrentDayColor(hasAnyHoliday)
                                        ),
                                    )
                                },
                                mainSlot = {
                                    LayoutElementBuilders.Column.Builder()
                                        .setWidth(expand())
                                        .setHeight(wrap())
                                        .setHorizontalAlignment(HORIZONTAL_ALIGN_CENTER).apply {
                                            val eventsDescription = events.eventsDescription
                                            if (eventsDescription.isNotBlank()) {
                                                this.addContent(
                                                    text(
                                                        eventsDescription.layoutString,
                                                        typography = Typography.BODY_EXTRA_SMALL,
                                                        color = LayoutColor(
                                                            getCurrentDayColor(hasAnyHoliday)
                                                        ),
                                                        maxLines = MAX_VALUE,
                                                        modifier = LayoutModifier.padding(all = 16f)
                                                    )
                                                )
                                            }
                                        }.build()
                                }
                            )
                        }
                    )
                ).build()
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