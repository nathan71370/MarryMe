@file:Suppress("unused")

package fr.azrodorza.marryme

import fr.azrodorza.marryme.model.DatabaseModelInitializer
import fr.azrodorza.marryme.model.others.InvitationModel
import fr.azrodorza.marryme.util.command.CommandInitializer
import fr.azrodorza.marryme.util.exposed.DatabaseInitializer
import fr.azrodorza.marryme.util.plugin.Instance
import fr.azrodorza.marryme.util.plugin.PluginConfig
import hazae41.minecraft.kutils.bukkit.info
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.collections.HashMap

class Main : JavaPlugin() {
    private val instance = Instance(this)
    private val config = PluginConfig()
    private val database = DatabaseInitializer()
    private val databaseModel = DatabaseModelInitializer()
    private val cmdInitializer = CommandInitializer()
    companion object {
        val invitationList:HashMap<UUID?,UUID?> = HashMap()
    }

    override fun onEnable() {
        database.connect()
        databaseModel.createSchema()
        cmdInitializer.defineCommands()
        info("Plugin MarryMe enabled")
    }
}
