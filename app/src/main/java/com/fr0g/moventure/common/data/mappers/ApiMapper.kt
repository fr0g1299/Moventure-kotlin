package com.fr0g.moventure.common.data.mappers

interface ApiMapper<Domain, Entity> {
    fun mapToDomain(apiDTO: Entity): Domain
}