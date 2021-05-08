package fr.azrodorza.marryme.component.user

import fr.azrodorza.marryme.model.database.MarryModel
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class NewWedding(private val marry1: String, private val marry2: String) {

    fun insert() = transaction {
        MarryModel.insert {
            it[firstMarry] = marry1
            it[secondMarry] = marry2
            it[ringMaterial] = "IRON"
        } get MarryModel.id
    }

    /*
    fun select() = transaction {
        MarryModel.select {

        }
    }*/
}
