package org.jellyfin.androidtv.ui.playback

import org.jellyfin.sdk.model.api.BaseItemDto

class VideoQueueManager {
	private var _currentVideoQueue: MutableList<BaseItemDto> = mutableListOf()
	private var _currentMediaPosition = -1

	fun setCurrentVideoQueue(items: List<BaseItemDto>?) {
		if (items.isNullOrEmpty()) {
			clearVideoQueue()
			return
		}
		_currentVideoQueue = items.toMutableList()
		_currentMediaPosition = 0
	}

	fun getCurrentVideoQueue(): List<BaseItemDto> = _currentVideoQueue

	fun setCurrentMediaPosition(currentMediaPosition: Int) {
		if (currentMediaPosition !in 0 until _currentVideoQueue.size) return
		_currentMediaPosition = currentMediaPosition
	}

	fun getCurrentMediaPosition() = _currentMediaPosition

	fun clearVideoQueue() {
		_currentVideoQueue.clear()
		_currentMediaPosition = -1
	}

	fun addToQueue(items: List<BaseItemDto>) {
		if (items.isEmpty()) return

		_currentVideoQueue.addAll(items)
		// If no item was playing before, start at the beginning.
		if (_currentMediaPosition == -1 && _currentVideoQueue.isNotEmpty()) {
			_currentMediaPosition = 0
		}
	}

	fun playNext(items: List<BaseItemDto>) {
		if (items.isEmpty()) return

		// If no video is currently playing, insert at the beginning.
		val insertIndex = if (_currentMediaPosition == -1) 0 else _currentMediaPosition + 1
		_currentVideoQueue.addAll(insertIndex, items)
		// If the queue was empty before inserting, set the playback to the first item.
		if (_currentMediaPosition == -1) {
			_currentMediaPosition = 0
		}
	}
}
