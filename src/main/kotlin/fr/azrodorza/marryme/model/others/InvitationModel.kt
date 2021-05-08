package fr.azrodorza.marryme.model.others

import com.github.shynixn.mccoroutine.launch
import fr.azrodorza.marryme.Main
import fr.azrodorza.marryme.util.plugin.Instance
import hazae41.minecraft.kutils.bukkit.msg
import kotlinx.coroutines.delay
import org.bukkit.Sound
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.util.*

class InvitationModel {

    companion object {
        fun sendInvitation(sender: Player, receiver: Player) {
            Main.invitationList[sender.uniqueId] = receiver.uniqueId
            sender.msg("Invitation for a wedding sent with love!")
            receiver.msg("Would you marry me ? -" + sender.name + " |/marry accept or /marry deny| 120 seconds to choose...")

            Instance.plugin.launch {
                for (i in 1..3) {
                    receiver.world.spawnEntity(receiver.location, EntityType.FIREWORK);
                    receiver.playSound(receiver.location, Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, -5.0f)
                    delay(250)
                }

                delay(120000)
                removeInvitationOfPlayer(receiver, sender)
            }

        }


        /**
         * Remove an invitation of a given player if he denied the invitation or if it has expires
         */
        fun removeInvitationOfPlayer(receiver: Player, sender: Player) {
            receiver.msg("Invitation from " + sender.name + " denied !")
            sender.msg(receiver.name + " denied your wedding invitation !")
            Main.invitationList.remove(sender.uniqueId)
        }

        /**
         * Return true if a playerX has been invited by the playerY, return false otherwise
         */
        fun isPlayerXInvitedByPlayerY(playerX: UUID, playerY: UUID): Boolean {
            if(Main.invitationList.contains(playerY))
                return (getPlayerInvited(playerY)?.equals(playerX) == true)
            return false
        }

        /**
         * Retrieve the player who invite from the invited player
         */
        private fun getPlayerWhoInvite(player: UUID): UUID? {
            return Main.invitationList.filterValues { it == player }.keys.elementAt(0)
        }

        /**
         * Retrieve the player invited from the player who invite
         */
        private fun getPlayerInvited(player: UUID): UUID? {
            return Main.invitationList[player]
        }
    }
}