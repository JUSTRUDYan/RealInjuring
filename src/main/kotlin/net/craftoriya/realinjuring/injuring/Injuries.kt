package net.craftoriya.realinjuring.injuring

import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.random.Random

class Injuries(
    private val player: Player,
    private val body: PlayerBody,
) {
    enum class BoneStatus{
        HEALTHY,
        SPRAINED,
        BROKEN,
        CURED,
    }

    val boneStatus: BoneStatus = BoneStatus.HEALTHY

    var laceration: Boolean = false
    private set
    fun laceration(time: Int, strength: Float){
        laceration = true
    }
    fun lightBleeding(time: Int){

    }
    fun heavyBleeding(){

    }
    fun burn( time: Int) {

    }
    fun brokenBone(time: Int) {
        pain(time + time/3)
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