package com.mobigods.cache.mappers

import com.mobigods.cache.models.LocationCacheModel
import com.mobigods.domain.base.BaseMapper
import com.mobigods.domain.models.Location
import javax.inject.Inject

class LocationCacheModelMapper @Inject constructor(): BaseMapper<LocationCacheModel, Location> {

    override fun mapTo(to: Location): LocationCacheModel {
        return LocationCacheModel(
            city = to.city,
            country = to.country,
            street = to.street,
            state = to.state,
            timezone = to.timezone
        )
    }

    override fun mapFrom(from: LocationCacheModel): Location {
        return Location(
            city = from.city,
            country = from.country,
            street = from.street,
            state = from.state,
            timezone = from.timezone
        )
    }

}