package net.craftoriya.realinjuring

import net.craftoriya.realinjuring.config.InjuryConfig
import net.craftoriya.realinjuring.injuring.PlayerBody
import net.craftoriya.realinjuring.injuringlisteners.InjuringListener
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.ConcurrentHashMap


class RealInjuring : JavaPlugin() {
    private val playersBodys: ConcurrentHashMap<Player, PlayerBody> = ConcurrentHashMap()
    private val injuryConfig: InjuryConfig = InjuryConfig()
    override fun onDisable() {
        logger.info("Goodbye world!")
    }

    override fun onEnable() {
        logger.info("Hello world!")
        Bukkit.getPluginManager().registerEvents(InjuringListener(this, injuryConfig, playersBodys), this)
    }
}