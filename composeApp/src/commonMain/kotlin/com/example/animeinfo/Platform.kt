package com.example.animeinfo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform