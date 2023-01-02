package de.theskyscout.soulobby.listeners.items

import de.theskyscout.soulobby.Soulobby
import de.theskyscout.soulobby.utils.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

object SichtbarkeitsItem: Listener {

    val VIP_ITEM =  ItemBuilder(Material.PURPLE_DYE).setDisplayName("<gradient:#17004a:#844dff><bold>Sichtbarkeit")
        .addLore("<gray>")
        .addLore("<gray>Sichtbar: <#5214de>VIP's").setInvisibleEnchant(true).toItemStack()
    val NO_ONE_ITEM =  ItemBuilder(Material.RED_DYE).setDisplayName("<gradient:#de148d:#ff0000><bold>Sichtbarkeit")
        .addLore("<gray>")
        .addLore("<gray>Sichtbar: <red>Niemand").setInvisibleEnchant(true).toItemStack()
    val ALL_ITEM = ItemBuilder(Material.LIME_DYE).setDisplayName("<gradient:green:#03fce3><bold>Sichtbarkeit")
        .addLore("<gray>")
        .addLore("<gray>Sichtbar: <green>Alle").setInvisibleEnchant(true).toItemStack()
    @EventHandler
    fun onClick(event:PlayerInteractEvent) {
        val player = event.player
        when(event.item?.type) {
            ALL_ITEM.type -> setVIP(player)
            VIP_ITEM.type -> setNoOne(player)
            NO_ONE_ITEM.type -> setAll(player)
            else -> {
                return
            }
        }
    }

    fun setVIP(player: Player) {
        Bukkit.getOnlinePlayers().forEach {
            if(!it.hasPermission("soulobby.vip")) {
                player.hidePlayer(Soulobby.plugin, it)
            }
        }
        player.inventory.setItem(1, VIP_ITEM)
    }
    fun setAll(player: Player) {
        Bukkit.getOnlinePlayers().forEach {
            player.showPlayer(Soulobby.plugin, it)
        }
        player.inventory.setItem(1, ALL_ITEM)
    }
    fun setNoOne(player: Player) {
        Bukkit.getOnlinePlayers().forEach {
            player.hidePlayer(Soulobby.plugin, it)
        }
        player.inventory.setItem(1, NO_ONE_ITEM)
    }
}