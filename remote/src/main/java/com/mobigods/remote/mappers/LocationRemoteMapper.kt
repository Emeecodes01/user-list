package com.mobigods.remote.mappers

import com.mobigods.domain.models.Location
import com.mobigods.remote.BaseMapper
import com.mobigods.remote.models.LocationRemoteModel
import javax.inject.Inject

class LocationRemoteMapper @Inject constructor() : BaseMapper<LocationRemoteModel, Location> {
    override fun mapTo(to: Location): LocationRemoteModel {

        return LocationRemoteModel(
            city = to.city,
            country = to.country,
            state = to.state,
            street = to.street,
            timezone = to.timezone
        )
    }

    override fun mapFrom(from: LocationRemoteModel): Location {
        return Location(
            city = from.city,
            country = from.country,
            state = from.state,
            street = from.street,
            timezone = from.timezone
        )
    }
}
