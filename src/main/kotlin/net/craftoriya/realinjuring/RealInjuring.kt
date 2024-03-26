package net.craftoriya.realinjuring

import net.craftoriya.realinjuring.config.InjuryConfig
import net.craftoriya.realinjuring.injuringlisteners.InjuringListener
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin


class RealInjuring : JavaPlugin() {
    private val injuryConfig: InjuryConfig = InjuryConfig()
    override fun onDisable() {
        logger.info("Goodbye world!")
    }

    override fun onEnable() {
        logger.info("Hello world!")
        Bukkit.getPluginManager().registerEvents(InjuringListener(this, injuryConfig), this)
    }
}