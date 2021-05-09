@file:Suppress("unused", "UNUSED_PARAMETER")
package fr.azrodorza.marryme.management

import fr.azrodorza.marryme.component.user.NewWedding
import hazae41.minecraft.kutils.bukkit.msg
import org.bukkit.entity.Player

/**
 * Manage the wedding
 */
class WeddingManagement {

    companion object {
        /**
         * Marry both players and add them to the database
         */
        fun marryPlayers(firstMarry: Player, secondMarry: Player) {

            //adding couple to the database
            val newUser = NewWedding(firstMarry.uniqueId.toString(), secondMarry.uniqueId.toString())

            //probably useful later
            val insertId = newUser.insert()
            
            firstMarry.msg("You are now married to " + secondMarry.name + " until a /divorce!")
            secondMarry.msg("You are now married to " + firstMarry.name + " until a /divorce!")
        }

        /**
         * Divorce and remove them from database
         */
        fun divorce(){

        }
    }
}