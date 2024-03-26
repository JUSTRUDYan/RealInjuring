package net.craftoriya.realinjuring.injuring

import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.random.Random

open class Injuries(
    private val player: Player,
    private val body: PlayerBody,
    private val plugin: JavaPlugin,
) {
    open fun pain(time: Int){
        player.addPotionEffect(PotionEffect(PotionEffectType.DARKNESS, time, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, time, 0))
    }

    open fun poison(time: Int){
        player.addPotionEffect(PotionEffect(PotionEffectType.POISON, time,0))
    }
}