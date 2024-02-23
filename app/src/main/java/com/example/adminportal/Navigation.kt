package com.example.adminportal

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.adminportal.Screens.PlaylistComposable
import com.example.adminportal.Screens.Videos
import com.example.adminportal.other.Screens
import com.example.adminportal.viewmodel.AdminPanelViewModel


@Composable
fun NavigationHelper(
    navController: NavHostController,
    viewModel: AdminPanelViewModel
) {

    NavHost(
        navController = navController,
        startDestination = Screens.PlaylistScreen.route
    ) {

        composable(Screens.PlaylistScreen.route) {
            PlaylistComposable(viewModel, navController)
        }

        composable(
            Screens.VideosScreen.route + "/{playlistId}"
        ) {
            val id = it.arguments?.getString("playlistId") ?: "0"
            Videos(viewModel, id)
        }

    }

}