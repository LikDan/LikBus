package com.likbus.plugins

import com.likbus.app.buses.repository.BusEntity
import com.likbus.app.routes.repository.tables.RouteStopsTable
import com.likbus.app.stops.repository.StopEntity
import com.likbus.app.stops.repository.tables.FullStopTable
import io.github.serpro69.kfaker.Faker
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.random.Random

fun Route.faker() = post ("faker") {
    val faker = Faker()

    transaction {
        for (i in 0..50)
            StopEntity.new {
                this.title = faker.address.streetName()
                this.lat = Random.nextDouble(-90.0, 90.0).toFloat()
                this.lng = Random.nextDouble(-180.0, 180.0).toFloat()
            }

        for (i in 0..100)
            BusEntity.new {
                this.number = faker.address.buildingNumber()
            }


        val buses = BusEntity.all().distinctBy { it.id }.map { it.id }
        val stops = StopEntity.all().distinctBy { it.id }.map { it.id }

        for (i in 0..2000)
            FullStopTable.insert {
                val busID = buses.random()
                val stopID = stops.random()
                it[this.bus] = busID
                it[this.stop] = stopID
            }


        for (i in 0..100000)
            RouteStopsTable.insert {
                val busID = buses.random()
                val stopID = stops.random()
                it[this.bus] = busID
                it[this.stop] = stopID
                it[this.time] = Random.nextLong(0, 1440)
            }
        }
}