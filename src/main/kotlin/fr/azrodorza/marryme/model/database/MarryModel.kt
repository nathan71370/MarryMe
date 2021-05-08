package fr.azrodorza.marryme.model.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

object MarryModel : Table("marry") {
    val id = integer("id").autoIncrement()

    val firstMarry = text("firstMarry")
    val secondMarry = text("secondMarry")
    val ringMaterial = text("ringMaterial")

    override val primaryKey = PrimaryKey(id, name = "id")
}
