package de.theskyscout.soulobby.utils

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

object Inventories {
    val mm = MiniMessage.miniMessage()
    fun startInventory(player: Player){
        val inventory = player.inventory
        inventory.setItem(0, ItemBuilder(Material.COMPASS).setDisplayName("<gradient:red:#fcad03><bold>SMPs").toItemStack())
        inventory.setItem(4,  ItemBuilder(Material.BOOK).setDisplayName("<gradient:green:#03fce3><bold>SMPs").toItemStack())
        inventory.setItem(8,  ItemBuilder(Material.FEATHER).setDisplayName("<gradient:blue:#c203fc><bold>Perks").toItemStack())
    }
}