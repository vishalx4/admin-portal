package com.example.adminportal.repository

import com.example.adminportal.API.AdminPortalAPI
import com.example.adminportal.NetworkResult
import com.example.adminportal.Utilitiy.Utility
import com.example.adminportal.model.Playlist
import com.example.adminportal.model.Video
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AdminPanelRepository(
    val adminPortalAPI: AdminPortalAPI
) {

    private val _playlistFlow = MutableStateFlow<NetworkResult<List<Playlist>>>(NetworkResult.Loading())
    val playlist = _playlistFlow.asStateFlow()

    private val _videosFlow = MutableStateFlow<NetworkResult<List<Video>>>(NetworkResult.Loading())
    val videosFlow = _videosFlow.asStateFlow()

    suspend fun getAllPlaylist() {
        try {
            val response = adminPortalAPI.getAllPlaylists()
            if (response.isSuccessful) {
                response.body()?.let {
                    _playlistFlow.emit(NetworkResult.Success(it))
                }
            } else {
                response.errorBody()?.let {
                    _playlistFlow.emit(NetworkResult.Error(message = Utility.getErrorObject(it)))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getVideosOfPlaylist(id: String) {
        try {
            val response = adminPortalAPI.getAllVideosOfPlaylist(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    _videosFlow.emit(NetworkResult.Success(data = it))
                }
            } else {
                response.errorBody()?.let {
                    _videosFlow.emit(NetworkResult.Error(message = Utility.getErrorObject(it)))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}