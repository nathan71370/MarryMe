package fr.azrodorza.marryme.management

import fr.azrodorza.marryme.component.user.NewWedding
import hazae41.minecraft.kutils.bukkit.msg
import org.bukkit.entity.Player

class WeddingManagement {

    companion object {
        fun marryPlayers(firstMarry: Player, secondMarry: Player) {

            //adding couple to the database
            val newUser = NewWedding(firstMarry.uniqueId.toString(), secondMarry.uniqueId.toString())

            //probably useful later
            val insertId = newUser.insert()
            firstMarry.msg("You are now married to " + secondMarry.name + " until a /divorce!")
            secondMarry.msg("You are now married to " + firstMarry.name + " until a /divorce!")
        }
    }
}