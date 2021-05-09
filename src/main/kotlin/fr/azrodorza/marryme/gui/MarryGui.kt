@file:Suppress("unused", "UNUSED_PARAMETER")
package fr.azrodorza.marryme.gui

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class MarryGui {

    companion object{
        private fun create(player: Player): Inventory{
            val inventory: Inventory = Bukkit.createInventory(player,54)

            return inventory
        }

        fun open(player: Player){
            player.openInventory(create(player))
        }
    }
}