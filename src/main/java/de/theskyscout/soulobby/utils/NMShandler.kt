package de.theskyscout.soulobby.utils

import net.minecraft.network.protocol.Packet
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer
import org.bukkit.entity.Player

object NMShandler {

    fun sendPacket(player: Player, packet: Packet<*>) {
        (player as CraftPlayer).handle.connection.send(packet)
    }
}