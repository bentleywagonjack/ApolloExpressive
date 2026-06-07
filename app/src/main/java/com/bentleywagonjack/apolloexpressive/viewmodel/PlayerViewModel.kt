package com.bentleywagonjack.apolloexpressive.viewmodel

import android.app.Application
import android.content.ComponentName
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.bentleywagonjack.apolloexpressive.data.model.Song
import com.bentleywagonjack.apolloexpressive.data.repository.MusicRepository
import com.bentleywagonjack.apolloexpressive.service.MusicPlaybackService
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

data class PlayerState(
    val currentSong: Song? = null,
    val isPlaying: Boolean = false,
    val progress: Float = 0f,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val isFavorite: Boolean = false,
    val songs: List<Song> = emptyList()
)

class PlayerViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = MusicRepository(app)
    private var controllerFuture: ListenableFuture<MediaController>? = null
    private var controller: MediaController? = null

    private val _state = MutableStateFlow(PlayerState())
    val state: StateFlow<PlayerState> = _state.asStateFlow()

    init {
        loadSongs()
        startProgressUpdater()
    }

    fun connectToService() {
        val context = getApplication<Application>()
        val sessionToken = SessionToken(
            context,
            ComponentName(context, MusicPlaybackService::class.java)
        )
        controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        controllerFuture?.addListener({
            controller = controllerFuture?.get()
            controller?.addListener(playerListener)
        }, MoreExecutors.directExecutor())
    }

    private val playerListener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            _state.update { it.copy(isPlaying = isPlaying) }
        }
        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            val song = _state.value.songs.find { it.uri == mediaItem?.mediaId }
            _state.update { it.copy(currentSong = song) }
        }
    }

    private fun loadSongs() {
        viewModelScope.launch {
            repository.getAllSongs().collect { songs ->
                _state.update { it.copy(songs = songs) }
            }
        }
    }

    private fun startProgressUpdater() {
        viewModelScope.launch {
            while (true) {
                delay(500)
                controller?.let { c ->
                    val pos = c.currentPosition
                    val dur = c.duration.takeIf { it > 0 } ?: 1L
                    _state.update {
                        it.copy(
                            currentPosition = pos,
                            duration = dur,
                            progress = pos.toFloat() / dur.toFloat()
                        )
                    }
                }
            }
        }
    }

    fun playSong(song: Song) {
        val mediaItem = MediaItem.Builder()
            .setMediaId(song.uri)
            .setUri(song.uri)
            .build()
        controller?.apply {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
        _state.update { it.copy(currentSong = song) }
    }

    fun togglePlayPause() {
        controller?.let {
            if (it.isPlaying) it.pause() else it.play()
        }
    }

    fun skipNext() { controller?.seekToNextMediaItem() }
    fun skipPrev() { controller?.seekToPreviousMediaItem() }

    fun seekTo(fraction: Float) {
        val dur = controller?.duration ?: return
        controller?.seekTo((fraction * dur).toLong())
    }

    fun toggleFavorite() {
        _state.update { it.copy(isFavorite = !it.isFavorite) }
    }

    override fun onCleared() {
        controller?.removeListener(playerListener)
        controllerFuture?.let { MediaController.releaseFuture(it) }
        super.onCleared()
    }
}
