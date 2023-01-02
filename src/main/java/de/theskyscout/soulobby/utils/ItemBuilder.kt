package de.theskyscout.soulobby.utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta


class ItemBuilder(material: Material) {
    val mm = MiniMessage.miniMessage()
    val material = material
    var amount:Int = 1
    var lore= ArrayList<Component>()
    lateinit var itemStack:ItemStack
    lateinit var itemMeta:ItemMeta
    init {
        itemStack = ItemStack(material, amount)
        itemMeta = itemStack.itemMeta
    }

    fun setDisplayName(displayName:String):ItemBuilder {
        itemMeta.displayName(mm.deserialize(displayName))
        return this
    }
    fun setAmount(amount: Int):ItemBuilder {
        itemStack.amount = amount
        return this
    }
    fun setLore(list:ArrayList<Component>):ItemBuilder {
        lore = list
        return this
    }
    fun addLore(text: String):ItemBuilder {
        lore.add(mm.deserialize(text))
        return this
    }
    fun editLore(line:Int, text: String):ItemBuilder {
        lore.removeAt(line - 1)
        lore.add(line-1, mm.deserialize(text))
        return this
    }
    fun setInvisibleEnchant(boolean: Boolean):ItemBuilder {
        if(boolean) {
            this.itemMeta.addEnchant(Enchantment.CHANNELING, 1, true);
            this.itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return this
    }

    fun toItemStack(): ItemStack {
        itemMeta.lore(lore)
        itemStack.itemMeta = itemMeta
        return itemStack
    }
}
