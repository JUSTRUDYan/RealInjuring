package net.craftoriya.realinjuring.injuring

import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Injuries(
    private val player: Player
) {
    var laceration: Boolean = false
    private set
    fun laceration(event: EntityDamageEvent, time: Int, strength: Float){
        laceration = true
    }
    fun lightBleeding(event: EntityDamageEvent, time: Int){

    }
    fun heavyBleeding(event: EntityDamageEvent){

    }
    fun burn(event: EntityDamageEvent, time: Int) {

    }
    fun brokenBone(event: EntityDamageEvent, time: Int) {
        val player: Player = event.entity as Player
        pain(time)
        //shock(time/10)
        player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, time,0))
    }
    private fun pain(time: Int){
        player.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, time, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, time, 0))
    }

    private fun poison(time: Int){
        player.addPotionEffect(PotionEffect(PotionEffectType.POISON, time,0))
    }
}