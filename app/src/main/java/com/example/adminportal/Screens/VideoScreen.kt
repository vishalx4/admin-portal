package com.example.adminportal.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.adminportal.NetworkResult
import com.example.adminportal.viewmodel.AdminPanelViewModel


@Composable
fun Videos(
    viewModel: AdminPanelViewModel,
    playlistId: String,
) {

    val videosList = viewModel.videosFlow.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getVideosOfPlaylist(id = playlistId)
    })

    when (videosList.value) {
        is NetworkResult.Success -> {
            videosList.value.data?.let { listOfVideo ->

                if (listOfVideo.isEmpty()) {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "no videos found in the playlist")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        items(
                            listOfVideo,
                            key = {
                                it.id
                            }
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .border(3.dp, Color.Green, RoundedCornerShape(10.dp))
                                    .padding(10.dp)
                            ) {
                                Text(text = "title: ${it.title}")
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "video link: ${it.link}"
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
        else -> {

        }
    }

}