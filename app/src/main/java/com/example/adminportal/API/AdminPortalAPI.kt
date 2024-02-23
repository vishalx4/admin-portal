package com.example.adminportal.API

import com.example.adminportal.model.Playlist
import com.example.adminportal.model.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AdminPortalAPI {

    @GET("playlists/")
    suspend fun getAllPlaylists(): Response<List<Playlist>>

    @GET("playlists/{id}/videos")
    suspend fun getAllVideosOfPlaylist(
        @Path("id") id: String
    ): Response<List<Video>>

}