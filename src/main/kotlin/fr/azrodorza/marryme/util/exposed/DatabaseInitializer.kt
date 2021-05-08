package fr.azrodorza.marryme.util.exposed

import fr.azrodorza.marryme.config.MarryMeConfiguration
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

class DatabaseInitializer {
    private val dataSource: HikariDataSource

    init {
        val hikariConfig = MarryMeConfiguration().get()
        dataSource = HikariDataSource(hikariConfig)
    }

    fun connect() = Database.connect(dataSource)
}
