package net.craftoriya.realinjuring.injuring

import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

open class TorsoInjuries (
    private val player: Player,
    private val body: PlayerBody
) : Injuries(player, body) {
    enum class BoneStatus{
        HEALTHY,
        SPRAINED,
        BROKEN,
        CURED,
    }

    open val boneStatus: BoneStatus = BoneStatus.HEALTHY
    open fun laceration(time: Int, strength: Float){

    }
    open fun lightBleeding(time: Int){

    }
    open fun heavyBleeding(){

    }
    override fun pain(time: Int) {
        super.pain(time)
    }
    open fun brokenBone(time: Int) {
        pain(time + time/3)
        player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, time,0))
    }
    open fun burn( time: Int) {

    }
    override fun poison(time: Int) {
        super.poison(time)
    }

}