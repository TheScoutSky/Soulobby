package de.theskyscout.soulobby.listeners

import de.theskyscout.soulobby.utils.Inventories
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class AllListeners: Listener {

    @EventHandler
    fun onJoin(event:PlayerJoinEvent) {
        val player = event.player
        Inventories.startInventory(player)
    }
}