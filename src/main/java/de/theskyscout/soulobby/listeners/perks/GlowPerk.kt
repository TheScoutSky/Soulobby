package de.theskyscout.soulobby.listeners.perks

import de.theskyscout.soulobby.config.Config
import de.theskyscout.soulobby.listeners.items.PerksItem
import de.theskyscout.soulobby.utils.ItemBuilder
import de.theskyscout.soulobby.utils.StartInventory
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

object GlowPerk: Listener {

    val inventory = Bukkit.createInventory(null, 27, PerksItem.GLOW_PERK_ITEM.toItemStack().displayName())
    val mm = MiniMessage.miniMessage()
    fun glow(player: Player) {
        if(player.isGlowing) {
           player.isGlowing = false
            PerksItem.GLOW_PERK_ITEM.editLore(3, "<red>Disabled")
            player.sendMessage(FlyPerk.mm.deserialize("${Config.prefix()} <gray>Du hast den GlowPerk <red>deaktiviert"))
            player.playSound(player, Sound.BLOCK_ANVIL_BREAK, 10F, 0F)
        } else {
            player.isGlowing = true
            PerksItem.GLOW_PERK_ITEM.editLore(3, "<green>Enabled")
            player.sendMessage(FlyPerk.mm.deserialize("${Config.prefix()} <gray>Du hast den GlowPerk <green>aktiviert"))
            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 10F, 0F)

        }
    }

    fun setGlowColor(player: Player, color: NamedTextColor) {
        if(player.isGlowing) {
            val scoreboard = Bukkit.getScoreboardManager().mainScoreboard
            player.isGlowing =false
            player.scoreboard = scoreboard
            val team = scoreboard.getTeam("$color.team")
            if(team == null) scoreboard.registerNewTeam("$color.team")
            team?.color(color)
            team?.addEntity(player)
            player.sendMessage(mm.deserialize("${Config.prefix()} Du leuchtest nun in der Farbe: <${color}>${color.toString().capitalize()}"))
            player.isGlowing=true
        } else {
            player.sendMessage(mm.deserialize("${Config.prefix()} Du musst erst Glow aktivieren"))
        }

    }

   fun setGlowColorInventory(player: Player) {
       for(i in 0..26) {
           inventory.setItem(i, PerksItem.BACKGROUND_ITEM)
       }
       inventory.setItem(10, ItemBuilder(Material.GREEN_WOOL).setDisplayName("<green>Gruen").toItemStack())
       inventory.setItem(11, ItemBuilder(Material.YELLOW_WOOL).setDisplayName("<yellow>Gelb").toItemStack())
       player.openInventory(inventory)
   }

    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        if(!event.clickedInventory?.contents.contentEquals(inventory.contents)) return
        val player:Player = event.whoClicked as Player
        when(event.currentItem?.type) {
            Material.GREEN_WOOL -> {
                setGlowColor(player, NamedTextColor.GREEN)
                player.closeInventory()
            }
            Material.YELLOW_WOOL -> {
                setGlowColor(player, NamedTextColor.YELLOW)
                player.closeInventory()
            }

            else -> {return}
        }
        event.isCancelled = true
    }
}