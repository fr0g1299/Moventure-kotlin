package com.fr0g.moventure.common.data

interface ApiMapper<Domain, Entity> {
    fun mapToDomain(apiDTO: Entity): Domain
}