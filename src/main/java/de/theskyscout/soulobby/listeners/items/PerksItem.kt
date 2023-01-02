package de.theskyscout.soulobby.listeners.items

import de.theskyscout.soulobby.listeners.perks.FlyPerk
import de.theskyscout.soulobby.listeners.perks.GlowPerk
import de.theskyscout.soulobby.utils.ItemBuilder
import de.theskyscout.soulobby.utils.StartInventory
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent

object PerksItem:Listener {
    val mm = MiniMessage.miniMessage()
    val inventory = Bukkit.createInventory(null, 27, StartInventory.PERKS_ITEM.displayName())
    val BACKGROUND_ITEM = ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName("<gray>").toItemStack()
    val FLY_PERK_ITEM = ItemBuilder(Material.FEATHER).setDisplayName("<gradient:white:green><bold>Fly").addLore("<gray>").addLore("<gray>Fliege durch den Himmel wie ein Vogel!")
        .addLore("<gray>STATUS")
    val GLOW_PERK_ITEM = ItemBuilder(Material.GLOWSTONE_DUST).setDisplayName("<gradient:white:yellow><bold>Glow").addLore("<gray>").addLore("<gray>Leuchte wie eine Laterne!")
        .addLore("<gray>STATUS")
    val NAME_PERK_ITEM = ItemBuilder(Material.NAME_TAG).setDisplayName("<gradient:white:blue><bold>NameTag").addLore("<gray>").addLore("<gray>Ein Text unter deinem Namen!")
        .addLore("<gray>STATUS")
    @EventHandler
    fun onClick(event: PlayerInteractEvent) {
        val player = event.player
        if(event.item?.type == StartInventory.PERKS_ITEM.type) {
            setInventory(player)
        }
    }


    fun setInventory(player: Player) {
        for(i in 0..26) {
            inventory.setItem(i, BACKGROUND_ITEM)
        }
        if(player.allowFlight) {FLY_PERK_ITEM.editLore(3, "<green>Enabled")}else{FLY_PERK_ITEM.editLore(3, "<red>Disabled")}
        if(player.isGlowing) {GLOW_PERK_ITEM.editLore(3, "<green>Enabled")}else{GLOW_PERK_ITEM.editLore(3, "<red>Disabled")}
        inventory.setItem(10, FLY_PERK_ITEM.toItemStack())
        inventory.setItem(11, GLOW_PERK_ITEM.toItemStack())
        inventory.setItem(12, NAME_PERK_ITEM.toItemStack())



        player.openInventory(inventory)
    }

    @EventHandler
    fun onClick(event:InventoryClickEvent) {
        if(!event.clickedInventory?.contents.contentEquals(inventory.contents)) return
            val player:Player = event.whoClicked as Player
            when(event.currentItem?.type) {
                FLY_PERK_ITEM.material -> {
                    FlyPerk.fly(player)
                    setInventory(player)
                }
                GLOW_PERK_ITEM.material -> {
                    if(event.click == ClickType.LEFT) {
                        GlowPerk.glow(player)
                        setInventory(player)
                    } else {
                        GlowPerk.setGlowColorInventory(player)
                    }
                }

                else -> {return}
            }
            event.isCancelled = true
    }
}