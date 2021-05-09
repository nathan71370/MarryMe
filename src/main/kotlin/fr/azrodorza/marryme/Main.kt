@file:Suppress("unused")

package fr.azrodorza.marryme

import fr.azrodorza.marryme.`object`.MarryObject
import fr.azrodorza.marryme.model.DatabaseModelInitializer
import fr.azrodorza.marryme.util.command.CommandInitializer
import fr.azrodorza.marryme.util.exposed.DatabaseInitializer
import fr.azrodorza.marryme.util.plugin.Instance
import fr.azrodorza.marryme.util.plugin.PluginConfig
import hazae41.minecraft.kutils.bukkit.info
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.collections.HashMap

/**
 * Our main javaplugin class
 */
class Main : JavaPlugin() {
    private val instance = Instance(this)
    private val config = PluginConfig()
    private val database = DatabaseInitializer()
    private val databaseModel = DatabaseModelInitializer()
    private val cmdInitializer = CommandInitializer()

    //our static hashmaps
    companion object {
        val invitationList:HashMap<UUID?,UUID?> = HashMap()
        val marriedPlayers:HashMap<UUID, MarryObject> = HashMap()
    }

    /**
     * trigger when the server starts
     */
    override fun onEnable() {
        database.connect()
        databaseModel.createSchemaAndGetMarriedPlayers()
        cmdInitializer.defineCommands()
        info("Plugin MarryMe enabled")
    }
}
