package com.example.aisletechchallenge.model

data class ProfileData(
    val invitation_type: String,
    val preferences: List<PreferenceX>,
    val question: String
)