package com.example.adminportal.other

sealed class Screens(val route: String) {
    object PlaylistScreen : Screens("playlist")
    object VideosScreen: Screens("videos")
}
