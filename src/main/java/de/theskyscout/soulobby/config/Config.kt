package de.theskyscout.soulobby.config

object Config {

    fun get(key: String): Any? {
        val config = ConfigUtil("config.yml")
        return config.config.get(key)
    }

    fun prefix():String {
        val config = ConfigUtil("config.yml")
        return config.config.getString("prefix").toString()
    }

    fun set(key: String, value: Any) {
        val config = ConfigUtil("config.yml")
        config.config.set(key, value)
    }

    fun loadConfig() {
        ConfigUtil("config.yml")
    }
}