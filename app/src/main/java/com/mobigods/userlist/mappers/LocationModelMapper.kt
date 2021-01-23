package com.mobigods.userlist.mappers

import com.mobigods.domain.base.BaseMapper
import com.mobigods.domain.models.Location
import com.mobigods.userlist.models.LocationModel
import javax.inject.Inject

class LocationModelMapper @Inject constructor(): BaseMapper<LocationModel, Location> {
    override fun mapTo(to: Location): LocationModel {
        return LocationModel(
            city = to.city,
            country = to.country,
            state = to.state,
            street = to.street,
            timezone = to.timezone
        )
    }

    override fun mapFrom(from: LocationModel): Location {
        return Location(
            city = from.city,
            country = from.country,
            state = from.state,
            street = from.street,
            timezone = from.timezone
        )
    }


}