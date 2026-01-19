package com.fr0g.moventure.common.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credits(
    @SerialName("cast")
    val cast: List<Cast?>? = null,
    @SerialName("crew")
    val crew: List<Crew?>? = null
)