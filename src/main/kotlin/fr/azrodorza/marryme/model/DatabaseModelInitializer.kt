package fr.azrodorza.marryme.model

import fr.azrodorza.marryme.Main.Companion.marriedPlayers
import fr.azrodorza.marryme.`object`.MarryObject
import fr.azrodorza.marryme.model.database.MarryModel
import org.bukkit.Material
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * Initialise the database and get all married players
 */
class DatabaseModelInitializer {

    /**
     * Initialise the database
     */
    fun createSchemaAndGetMarriedPlayers() = transaction {
        SchemaUtils.create(
            MarryModel
        )
        getMarriedPlayers()
    }

    /**
     * Retrieve all married players from database and put them in marriedPlayers hashmap
     */
    private fun getMarriedPlayers() = transaction {
        MarryModel.selectAll().forEach{
            val uuidFirstMarry = UUID.fromString(it[MarryModel.firstMarry])
            val uuidSecondMarry = UUID.fromString(it[MarryModel.secondMarry])
            val marryObject = MarryObject(uuidFirstMarry,
                uuidSecondMarry,
                Material.getMaterial(it[MarryModel.ringMaterial]))

            marriedPlayers[uuidFirstMarry] = marryObject
            marriedPlayers[uuidSecondMarry] = marryObject
        }
    }
}
