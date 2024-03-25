package net.craftoriya.realinjuring.injuringlisteners

import net.craftoriya.realinjuring.injuring.Injuring
import org.bukkit.entity.Player
import org.bukkit.damage.DamageType
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.util.Vector

class InjuringListener: Listener {
    @EventHandler
    fun onPlayerDamage(event: EntityDamageEvent){
        if (event.entity.type != EntityType.PLAYER)
            return
        println(2222)
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
        val injuring: Injuring = Injuring(player)
        println(23423)
        //hardCode
        injuring.brokenBone(event,
            when{
                fallDistance >= 15f -> 120*20
                fallDistance >= 13f -> 90*20
                fallDistance >= 6f -> 60*20
                else -> return
            }
        )
    }
}