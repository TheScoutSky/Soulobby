package de.theskyscout.soulobby.listeners.perks

import de.theskyscout.soulobby.Soulobby
import de.theskyscout.soulobby.config.Config
import de.theskyscout.soulobby.listeners.items.PerksItem
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Sound
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.scheduler.BukkitRunnable


object NamePerk: Listener {
    val mm = MiniMessage.miniMessage()
    val players = HashMap<Player, ArmorStand>()
    val send =  ArrayList<Player>()
    fun setNameInfo(player: Player, text:String) {
        val entity= player.world.spawnEntity(player.location.add(0.0, 2.0, 0.0), EntityType.ARMOR_STAND) as ArmorStand
        entity.isInvisible = true
        entity.customName(mm.deserialize(text))
        entity.isCustomNameVisible = true
        entity.setGravity(false)
        entity.isInvulnerable = false
        entity.isMarker = true
        val slime = player.world.spawnEntity(player.location.add(0.0, 2.0, 0.0), EntityType.SLIME) as Slime
        slime.size = -3
        slime.isCollidable = false
        slime.setAI(false)
        slime.addPassenger(entity)
        slime.isInvulnerable = true
        players.put(player, entity)
        player.addPassenger(slime)
        PerksItem.NAME_PERK_ITEM.editLore(3, "<green>Enabled")
    }

    fun changeNameInfo(player: Player) {
        if(!players.contains(player)) {
            send.add(player)
            player.sendMessage(mm.deserialize("${Config.prefix()} Schreibe den Text nun in den Chat!"))
            player.closeInventory()
        } else {
            players.get(player)?.remove()
            players.remove(player)
            player.sendMessage(mm.deserialize("${Config.prefix()} Du hast das Nameperk <red>deaktiviert"))
            player.playSound(player, Sound.BLOCK_ANVIL_BREAK, 10F, 0F)
            PerksItem.NAME_PERK_ITEM.editLore(3, "<red>Disabeld")
        }
    }

    @EventHandler
    fun onChat(event:PlayerChatEvent) {
        val player:Player = event.player
        if(send.contains(player)) {
            send.remove(player)
            setNameInfo(player, event.message)
            player.sendMessage(mm.deserialize("${Config.prefix()} Du hast das Nameperk <green>aktiviert"))
            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 10F, 0F)
            event.isCancelled = true
        }
    }
    @EventHandler
    fun atEntityInteract(event:PlayerInteractAtEntityEvent) {
        val player = event.player
        if(event.rightClicked is ArmorStand) {
            event.isCancelled = true
        }
    }
    @EventHandler
    fun onEntityInteract(event: PlayerInteractEntityEvent) {
        val player = event.player
        if(event.rightClicked is ArmorStand) {
            event.isCancelled = true
        }
    }
}