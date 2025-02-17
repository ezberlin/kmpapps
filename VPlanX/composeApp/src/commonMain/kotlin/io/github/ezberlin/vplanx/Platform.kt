package io.github.ezberlin.vplanx

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform