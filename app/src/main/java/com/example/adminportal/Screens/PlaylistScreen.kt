package com.example.adminportal.Screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.adminportal.NetworkResult
import com.example.adminportal.other.Screens
import com.example.adminportal.viewmodel.AdminPanelViewModel


@Composable
fun PlaylistComposable(
    viewModel: AdminPanelViewModel,
    navController: NavHostController,
) {
    val playlistState = viewModel.playlistFlow.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getAllPlaylists()
    })

    when (playlistState.value) {
        is NetworkResult.Success -> {
            playlistState.value.data?.let {  listOfPlaylist ->
                LazyColumn(
                    modifier = Modifier.padding(12.dp)
                ) {
                    items(
                        listOfPlaylist,
                        key = {
                            it.id
                        }
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp)
                                .border(2.dp, Color.Green, RoundedCornerShape(10.dp))
                                .clickable {
                                    navController.navigate(Screens.VideosScreen.route + "/${it.id}")
                                },
                            contentAlignment = Alignment.Center
                        ){

                            Text(
                                modifier = Modifier.padding(10.dp),
                                fontSize = 15.sp,
                                text = it.name,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                    }
                }

            }
        }

        is NetworkResult.Loading -> {
            Log.d("vishal", "PlaylistComposable: Loading")
        }

        else -> {
            Log.d("vishal", "PlaylistComposable: else")
        }
    }
}