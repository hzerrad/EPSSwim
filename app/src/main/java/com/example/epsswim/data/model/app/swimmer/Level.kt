package com.example.epsswim.data.model.app.swimmer

data class Level(
    val levelid: String,
    val levelname: String,
    val swimmers: List<Swimmer>
)