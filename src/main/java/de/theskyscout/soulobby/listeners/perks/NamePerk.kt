package de.theskyscout.soulobby.listeners.perks

import de.theskyscout.soulobby.Soulobby
import de.theskyscout.soulobby.config.Config
import de.theskyscout.soulobby.listeners.items.PerksItem
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Sound
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask


object NamePerk: Listener {
    val mm = MiniMessage.miniMessage()
    val players = ArrayList<Player>()
    val send =  ArrayList<Player>()
    lateinit var runnable: BukkitTask
    fun setNameInfo(player: Player, text:String) {
        val armorStand = player.world.spawnEntity(player.eyeLocation.add(0.0, -0.5, 0.0), EntityType.ARMOR_STAND) as ArmorStand
        armorStand.isInvisible = true
        armorStand.customName(mm.deserialize(text))
        armorStand.isCustomNameVisible = true
        armorStand.isInvulnerable = true
        runnable = object : BukkitRunnable() {
            override fun run() {
                armorStand.teleport(player.eyeLocation.add(0.0, -0.5, 0.0))
            }
        }.runTaskTimer(Soulobby.plugin, 0, 1)
        runnable.cancel()
        players.add(player)
        PerksItem.NAME_PERK_ITEM.editLore(3, "<green>Enabled")
    }

    fun changeNameInfo(player: Player) {
        if(!players.contains(player)) {
            send.add(player)
            player.sendMessage(mm.deserialize("${Config.prefix()} Schreibe den Text nun in den Chat!"))
        } else {
            players.remove(player)
            runnable.cancel()
            player.sendMessage(mm.deserialize("${Config.prefix()} Du hast das Nameperk <red>deaktiviert"))
            player.playSound(player, Sound.BLOCK_ANVIL_BREAK, 10F, 0F)
            PerksItem.NAME_PERK_ITEM.editLore(3, "<red>Disabeld")
        }
    }

    @EventHandler
    fun chat(event:PlayerChatEvent) {
        val player:Player = event.player
        if(send.contains(player)) {
            send.remove(player)
            setNameInfo(player, event.message)
            player.closeInventory()
            player.sendMessage(mm.deserialize("${Config.prefix()} Du hast das Nameperk <green>aktiviert"))
            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 10F, 0F)
            event.isCancelled = true
        }
    }


}