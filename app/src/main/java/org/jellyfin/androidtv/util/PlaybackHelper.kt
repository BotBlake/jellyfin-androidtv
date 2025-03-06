package org.jellyfin.androidtv.util

import android.content.Context
import org.jellyfin.androidtv.util.apiclient.Response
import org.jellyfin.sdk.model.api.BaseItemDto
import java.util.UUID

interface PlaybackHelper {
	fun getItemsToPlay(
		context: Context,
		mainItem: BaseItemDto,
		allowIntros: Boolean,
		shuffle: Boolean,
		outerResponse: Response<List<BaseItemDto>>,
	)

	suspend fun retrieveItems(ids: List<UUID>): List<BaseItemDto>

	fun retrieveAndPlay(id: UUID, shuffle: Boolean, position: Long?, context: Context)

	fun retrieveAndPlay(id: UUID, shuffle: Boolean, context: Context) =
		retrieveAndPlay(id, shuffle, null, context)

	fun startNewVideoQueue(context: Context, mainItem: BaseItemDto, items: List<BaseItemDto>, startposition: Int)

	fun addToVideoQueue(context: Context, items: List<BaseItemDto>, next: Boolean)

	fun playInstantMix(context: Context, item: BaseItemDto)
}
