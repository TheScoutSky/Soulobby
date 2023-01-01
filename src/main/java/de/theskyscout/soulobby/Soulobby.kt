package de.theskyscout.soulobby

import de.theskyscout.soulobby.commands.LobbyCommand
import de.theskyscout.soulobby.config.Config
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Soulobby : JavaPlugin() {
    companion object {
        lateinit var plugin: Soulobby
    }
    override fun onEnable() {
        plugin = this
        register()
        Config.loadConfig()
    }
    private fun register() {
        val manager = Bukkit.getPluginManager()
        getCommand("soulobby")?.setExecutor(LobbyCommand())
        getCommand("soulobby")?.setTabCompleter(LobbyCommand())
    }
}