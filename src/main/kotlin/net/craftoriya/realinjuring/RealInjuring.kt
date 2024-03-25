package net.craftoriya.realinjuring

import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin


class RealInjuring : JavaPlugin() {
    val eventHandler: EventHandler = EventHandler()

    override fun onDisable() {
        logger.info("Goodbye world!")
    }

    override fun onEnable() {
        logger.info("Hello world!")
    }

    @EventHandler
    fun onPLayerJoin(event: PlayerJoinEvent){
    }

}