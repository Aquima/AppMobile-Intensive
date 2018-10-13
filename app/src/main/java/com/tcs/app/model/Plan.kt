package com.tcs.app.model

data class Plan(
        val name: String,
        val space: Int,
        val collaborators: Int,
        val private_repos: Int
)