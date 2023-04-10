package com.likbus.app

import com.likbus.app.buses.repository.tables.BusTable
import com.likbus.app.routes.repository.tables.RouteStopsTable
import com.likbus.app.stops.repository.tables.FullStopTable
import com.likbus.app.stops.repository.tables.StopTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DB {
    private val host = System.getenv("DB_HOST") ?: "localhost"
    private val port = System.getenv("DB_PORT")?.toIntOrNull() ?: 5432
    private val dbName = System.getenv("DB_NAME") ?: "likbus"
    private val dbUser = System.getenv("DB_USER") ?: "user"
    private val dbPassword = System.getenv("DB_PASSWORD") ?: "password"

    fun connect() = Database.connect(
        url = "jdbc:postgresql://$host:$port/$dbName",
        driver = "org.postgresql.Driver",
        user = dbUser,
        password = dbPassword,
    )

    fun createTables() = transaction {
        SchemaUtils.create(StopTable)
        SchemaUtils.create(BusTable)
        SchemaUtils.create(FullStopTable)
        SchemaUtils.create(RouteStopsTable)
    }
}