@file:Suppress("unused", "UNUSED_PARAMETER")

package fr.azrodorza.marryme.component.user

import fr.azrodorza.marryme.Main
import fr.azrodorza.marryme.`object`.MarryObject
import fr.azrodorza.marryme.model.database.MarryModel
import org.bukkit.Material
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * Calling this class when adding a new wedding in database
 */
class NewWedding(private val marry1: String, private val marry2: String) {

    /**
     * Insert a wedding in database with IRON as default ring material
     */
    fun insert() = transaction {
        MarryModel.insert {
            it[firstMarry] = marry1
            it[secondMarry] = marry2
            it[ringMaterial] = "IRON"
        } get MarryModel.id

        val uuidFirstMarry = UUID.fromString(marry1)
        val uuidSecondMarry = UUID.fromString(marry2)

        val marryObject = MarryObject(
            uuidFirstMarry,
            uuidSecondMarry,
            Material.getMaterial("IRON"))

        //putting our marry object to our hashmap to be able to get it
        Main.marriedPlayers[uuidFirstMarry] = marryObject
        Main.marriedPlayers[uuidSecondMarry] = marryObject
    }

}
