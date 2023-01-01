package de.theskyscout.soulobby.config

import de.theskyscout.soulobby.Soulobby
import org.apache.commons.io.FileUtils
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ConfigUtil(fileName: String) {
    val fileName = fileName
    lateinit var file:File
    lateinit var config:YamlConfiguration
    init {
        file = File(Soulobby.plugin.dataFolder, fileName)
        if(!file.exists()) {
            val stream = Soulobby.plugin.getResource(fileName)
            if(stream != null) {
                FileUtils.copyInputStreamToFile(stream, File(Soulobby.plugin.dataFolder, fileName))
            }
        }
        config = YamlConfiguration.loadConfiguration(file)
    }
    fun reload() {
        ConfigUtil(fileName)
    }
    fun save() {
        config.save(file)
    }
}