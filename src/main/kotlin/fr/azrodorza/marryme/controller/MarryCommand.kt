@file:Suppress("unused", "UNUSED_PARAMETER")

package fr.azrodorza.marryme.controller

import fr.azrodorza.marryme.gui.MarryGui
import fr.azrodorza.marryme.management.InvitationManagement
import fr.azrodorza.marryme.management.WeddingManagement
import fr.azrodorza.marryme.util.command.CommandMapping
import hazae41.minecraft.kutils.bukkit.msg
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * This is the marry command, you need to precise who you want to marry to
 */
class MarryCommand {

    /**
     * Here we have our marry command
     */
    @CommandMapping("marry")
    fun marryCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>) {

        //checking for correct inputs
        if (sender !is Player) return

        //getting both players
        val firstMarry = sender.player
        val secondMarry = Bukkit.getPlayer(args[0])

        //verifying that both players are not null
        if(firstMarry == null || secondMarry == null) return


        if(args.isEmpty()){
            MarryGui.open(firstMarry)
            return
        }

        //if he ask to marry to himself
        if(firstMarry.name == secondMarry.name) {
            firstMarry.msg("You can't marry yourself!")
            return
        }

        //if the commandSender never send invitation to player, send one
        if(!InvitationManagement.isPlayerXInvitedByPlayerY(secondMarry.uniqueId, firstMarry.uniqueId)){
            if(!InvitationManagement.isPlayerXInvitedByPlayerY(firstMarry.uniqueId, secondMarry.uniqueId))
                InvitationManagement.sendInvitation(firstMarry, secondMarry)
            else
            // if the commandSender already received an invitation from the player he is trying to marry, marry them
                WeddingManagement.marryPlayers(firstMarry, secondMarry)
        } else {
            firstMarry.msg("You already asked your love!")
        }

    }

    /**
     * Here we have our marry accept command
     */
    @CommandMapping("marry", "accept")
    fun marryAcceptCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>) {

        //checking for correct inputs
        if (sender !is Player) return
        if(args.isEmpty()) return


        //retrieving unique ids
        val firstMarry = Bukkit.getPlayer(args[1])
        val secondMarry = sender.player

        //verifying that both players are not null
        if(firstMarry == null || secondMarry == null) return

        //if he hasn't been invited, exit
        if(!InvitationManagement.isPlayerXInvitedByPlayerY(secondMarry.uniqueId, firstMarry.uniqueId)) return

        //marry the players
        WeddingManagement.marryPlayers(firstMarry, secondMarry)
    }

    /**
     * Here we have our marry deny command
     */
    @CommandMapping("marry", "deny")
    fun marryDenyCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>) {

        //checking for correct inputs
        if (sender !is Player) return
        if(args.isEmpty()) return


        //retrieving unique ids
        val firstMarry = Bukkit.getPlayer(args[1])
        val secondMarry = sender.player

        //verifying that both players are not null
        if(firstMarry == null || secondMarry == null) return

        //removeInvitation
        InvitationManagement.removeInvitationOfPlayer(secondMarry, firstMarry);
    }
}