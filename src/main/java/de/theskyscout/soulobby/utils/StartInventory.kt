package de.theskyscout.soulobby.utils

import de.theskyscout.soulobby.listeners.items.PerksItem
import de.theskyscout.soulobby.listeners.items.SichtbarkeitsItem
import de.theskyscout.soulobby.listeners.perks.FlyPerk
import de.theskyscout.soulobby.listeners.perks.GlowPerk
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.Calendar

object StartInventory {
    val mm = MiniMessage.miniMessage()
    val SMP_Item =  ItemBuilder(Material.COMPASS).setDisplayName("<gradient:red:#fcad03><bold>SMPs").toItemStack()
    val PERKS_ITEM = ItemBuilder(Material.FEATHER).setDisplayName("<gradient:blue:#c203fc><bold>Perks").toItemStack()
    fun startInventory(player: Player){
        val inventory = player.inventory
        inventory.clear()
        inventory.setItem(0, SMP_Item)
        inventory.setItem(1,  SichtbarkeitsItem.ALL_ITEM)
        SichtbarkeitsItem.setAll(player)
        inventory.setItem(8,  PERKS_ITEM)
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        player.level = year
    }
}