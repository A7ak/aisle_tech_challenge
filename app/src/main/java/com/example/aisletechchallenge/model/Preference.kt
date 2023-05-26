package com.example.aisletechchallenge.model

data class Preference(
    val answer_id: Int,
    val id: Int,
    val preference_question: PreferenceQuestion,
    val value: Int
)