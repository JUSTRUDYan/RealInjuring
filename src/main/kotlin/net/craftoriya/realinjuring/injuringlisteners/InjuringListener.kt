package net.craftoriya.realinjuring.injuringlisteners

import net.craftoriya.realinjuring.injuring.Injuring
import org.bukkit.damage.DamageType
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.util.Vector

class InjuringListener {
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
        val fallHigh: Double = (event.damageSource.damageLocation?.let {
            event.damageSource.sourceLocation?.distance(it)
        }) ?: return

        val player: Player = event.entity as Player
        val injuring: Injuring = Injuring(player)
        //hardCode
        injuring.brokenBone(event,
            when{
                fallHigh >= 20 -> 1200
                fallHigh >= 15 -> 900
                fallHigh >= 10 -> 600
                else -> 0
            }
        )
    }
}