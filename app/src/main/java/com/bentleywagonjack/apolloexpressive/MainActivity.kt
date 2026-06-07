package com.bentleywagonjack.apolloexpressive

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.core.content.ContextCompat
import com.bentleywagonjack.apolloexpressive.ui.screens.LibraryScreen
import com.bentleywagonjack.apolloexpressive.ui.screens.NowPlayingScreen
import com.bentleywagonjack.apolloexpressive.ui.theme.ApolloExpressiveTheme
import com.bentleywagonjack.apolloexpressive.viewmodel.PlayerViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: PlayerViewModel by viewModels()

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { viewModel.connectToService() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val missing = arrayOf(
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.POST_NOTIFICATIONS
        ).filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (missing.isEmpty()) viewModel.connectToService()
        else permissionLauncher.launch(missing.toTypedArray())

        setContent {
            ApolloExpressiveTheme {
                val state by viewModel.state.collectAsState()
                var screen by remember { mutableStateOf("library") }

                when (screen) {
                    "nowplaying" -> {
                        BackHandler { screen = "library" }
                        NowPlayingScreen(
                            title       = state.currentSong?.title ?: "No song selected",
                            artist      = state.currentSong?.artist ?: "Select from library",
                            albumArtUri = state.currentSong?.albumArtUri,
                            isPlaying   = state.isPlaying,
                            progress    = state.progress,
                            duration    = state.duration,
                            currentPos  = state.currentPosition,
                            isFavorite  = state.isFavorite,
                            onPlayPause = { viewModel.togglePlayPause() },
                            onNext      = { viewModel.skipNext() },
                            onPrev      = { viewModel.skipPrev() },
                            onFavorite  = { viewModel.toggleFavorite() },
                            onSeek      = { viewModel.seekTo(it) },
                            onBack      = { screen = "library" }
                        )
                    }
                    else -> {
                        LibraryScreen(
                            songs           = state.songs,
                            currentSong     = state.currentSong,
                            isPlaying       = state.isPlaying,
                            onSongClick     = { song ->
                                viewModel.playSong(song)
                                screen = "nowplaying"
                            },
                            onMiniPlayerClick = { screen = "nowplaying" },
                            onPlayPause     = { viewModel.togglePlayPause() }
                        )
                    }
                }
            }
        }
    }
}
