package de.theskyscout.soulobby.listeners

import de.theskyscout.soulobby.Soulobby
import de.theskyscout.soulobby.listeners.items.PerksItem
import de.theskyscout.soulobby.listeners.items.SichtbarkeitsItem
import de.theskyscout.soulobby.listeners.perks.GlowPerk
import de.theskyscout.soulobby.listeners.perks.NamePerk
import de.theskyscout.soulobby.utils.StartInventory
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerJoinEvent

class AllListeners(): Listener {

    init {
        val manager = Bukkit.getPluginManager()
        manager.registerEvents(SichtbarkeitsItem, Soulobby.plugin)
        manager.registerEvents(PerksItem, Soulobby.plugin)
        manager.registerEvents(FreezingItems, Soulobby.plugin)
        manager.registerEvents(GlowPerk, Soulobby.plugin)
        manager.registerEvents(NamePerk, Soulobby.plugin)
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        StartInventory.startInventory(player)
    }

    @EventHandler
    fun onDamage(event:EntityDamageEvent) {
        if(event.entity is Player) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onHuger(event:FoodLevelChangeEvent) {
        event.isCancelled = true
    }
}