package com.example.adminportal.model

data class Video(
    val created_at: String,
    val id: Int,
    val link: String,
    val playlist_id: Int,
    val thumbnail_link: Any,
    val title: String,
    val updated_at: String
)