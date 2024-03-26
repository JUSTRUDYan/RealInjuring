package net.craftoriya.realinjuring.injuringlisteners

import net.craftoriya.realinjuring.injuring.PlayerBody
import org.bukkit.entity.Player
import org.bukkit.damage.DamageType
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.plugin.java.JavaPlugin

class InjuringListener(
    private val plugin: JavaPlugin
): Listener {
    @EventHandler
    fun onPlayerDamage(event: EntityDamageEvent){
        if (event.entity.type != EntityType.PLAYER)
            return
        when(event.damageSource.damageType){
            DamageType.ARROW -> onArrow(event)
            DamageType.EXPLOSION -> onExplosion(event)
            DamageType.FALL -> onFall(event)
        }
    }
    fun onArrow(event: EntityDamageEvent){

    }
    fun onExplosion(event: EntityDamageEvent){

    }
    fun onFall(event: EntityDamageEvent){
        val player: Player = event.entity as Player
        val fallDistance: Float = player.fallDistance
        val body: PlayerBody = PlayerBody(player, plugin)
        body.shockAlgorithm.start()
        body.lLeg.brokenBone(event,
            when{
                fallDistance >= 15f -> 12*20
                fallDistance >= 13f -> 9*20
                fallDistance >= 6f -> 2*20
                else -> return
            }
        )
    }
}