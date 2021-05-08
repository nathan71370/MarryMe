@file:Suppress("unused", "UNUSED_PARAMETER")

package fr.azrodorza.marryme.controller

import fr.azrodorza.marryme.util.command.CommandMapping
import hazae41.minecraft.kutils.bukkit.msg
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

/**
 * Marry command which allow the /marry
 */
class NormalCommand {
//good to know

    @CommandMapping("abc", permission = "command.marry")
    fun abcCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>) {
        sender.msg("yep thats a non-subcommand command")
        args.forEach { s: String ->
            println(s)
        }
    }


    @CommandMapping("abc", "test")
    fun abcTestCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>) {
        sender.msg("Abc test sub-command works :p")
        Thread.sleep(2000L)
        sender.msg("Look! Its not blocking the server")
    }

    @CommandMapping("abc", "yes", permission = "plugin.test")
    fun abcYesCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>) {
        sender.msg("the second sub-command")
    }

}
