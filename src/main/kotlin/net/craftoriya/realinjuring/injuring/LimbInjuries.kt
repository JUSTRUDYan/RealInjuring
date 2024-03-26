package net.craftoriya.realinjuring.injuring

import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class LimbInjuries (
    private val player: Player,
    private val body: PlayerBody
) : TorsoInjuries(player, body) {
    override val boneStatus: BoneStatus
        get() = super.boneStatus
    override fun brokenBone(time: Int) {
        super.brokenBone(time)
    }
    override fun pain(time: Int) {
        super.pain(time)
    }
    override fun laceration(time: Int, strength: Float) {
        super.laceration(time, strength)
    }
    override fun heavyBleeding() {
        super.heavyBleeding()
    }
    override fun lightBleeding(time: Int) {
        super.lightBleeding(time)
    }
    override fun burn(time: Int) {
        super.burn(time)
    }

    override fun poison(time: Int) {
        super.poison(time)
    }
}