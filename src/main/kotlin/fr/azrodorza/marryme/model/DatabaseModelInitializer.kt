package fr.azrodorza.marryme.model

import fr.azrodorza.marryme.model.database.MarryModel
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseModelInitializer {

    fun createSchema() = transaction {
        SchemaUtils.create(
            MarryModel
        )
    }
}
