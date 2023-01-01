package de.theskyscout.soulobby.commands

import de.theskyscout.soulobby.config.Config
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class LobbyCommand: CommandExecutor, TabCompleter {
    val mm = MiniMessage.miniMessage()
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(sender is Player) {
            val player:Player = sender
            if(args?.size!! > 0) {
                when(args[0]) {
                    "test" -> {
                        player.sendMessage(mm.deserialize("<gray>Der Prefix lautet <green> ${Config.prefix()}"))
                    }
                }
            }
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String>? {
        val completions = ArrayList<String>()
        if(args?.size == 1) {
            completions.add("test")
        }
        return completions
    }
}