package com.example.adminportal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.adminportal.API.AdminPortalAPI
import com.example.adminportal.other.RetrofitHelper
import com.example.adminportal.repository.AdminPanelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create

class AdminPanelViewModel(
    private val adminPortalRepository: AdminPanelRepository
): ViewModel() {


    val playlistFlow = adminPortalRepository.playlist
    val videosFlow = adminPortalRepository.videosFlow


    fun getAllPlaylists() {
        viewModelScope.launch(Dispatchers.IO) {
            adminPortalRepository.getAllPlaylist()
        }
    }

    fun getVideosOfPlaylist(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            adminPortalRepository.getVideosOfPlaylist(id)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val adminPortalAPI = RetrofitHelper.getInstance().value.create<AdminPortalAPI>()
                return AdminPanelViewModel(AdminPanelRepository(adminPortalAPI))  as T
            }
        }
    }
}