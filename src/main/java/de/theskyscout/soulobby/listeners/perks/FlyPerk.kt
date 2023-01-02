package de.theskyscout.soulobby.listeners.perks

import de.theskyscout.soulobby.config.Config
import de.theskyscout.soulobby.listeners.items.PerksItem
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Sound
import org.bukkit.entity.Player

object FlyPerk {
    val mm = MiniMessage.miniMessage()

    fun fly(player: Player) {
        if(player.allowFlight) {
            player.allowFlight = false
            PerksItem.FLY_PERK_ITEM.editLore(3, "<red>Disabled")
            player.sendMessage(mm.deserialize("${Config.prefix()} <gray>Du hast Fly <red>deaktiviert"))
            player.playSound(player, Sound.BLOCK_ANVIL_BREAK, 10F, 0F)
        } else {
            player.allowFlight = true
            PerksItem.FLY_PERK_ITEM.editLore(3, "<green>Enabled")
            player.sendMessage(mm.deserialize("${Config.prefix()} <gray>Du hast Fly <green>aktiviert"))
            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 10F, 0F)
        }
    }
}