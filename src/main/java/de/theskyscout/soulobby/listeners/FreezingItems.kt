package de.theskyscout.soulobby.listeners

import de.theskyscout.soulobby.listeners.items.SichtbarkeitsItem
import de.theskyscout.soulobby.utils.StartInventory
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryMoveItemEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.inventory.ItemStack

object FreezingItems: Listener {

    val freezedItems = listOf<ItemStack>(StartInventory.PERKS_ITEM, StartInventory.SMP_Item, SichtbarkeitsItem.ALL_ITEM, SichtbarkeitsItem.NO_ONE_ITEM, SichtbarkeitsItem.VIP_ITEM)

    @EventHandler
    fun onDrop(event: PlayerDropItemEvent) {
        if(freezedItems.contains(event.itemDrop.itemStack)) {
           event.isCancelled = true
        }
    }
    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        if(freezedItems.contains(event.currentItem)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onItemMove(event: InventoryMoveItemEvent) {
        if(freezedItems.contains(event.item)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onItemMoveOffHand(event: PlayerSwapHandItemsEvent) {
        if(freezedItems.contains(event.offHandItem)) {
            event.isCancelled = true
        }
    }
}