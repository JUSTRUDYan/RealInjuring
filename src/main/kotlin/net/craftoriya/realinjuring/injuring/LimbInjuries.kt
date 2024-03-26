package net.craftoriya.realinjuring.injuring

import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class LimbInjuries (
    private val player: Player,
    private val body: PlayerBody,
    private val plugin: JavaPlugin,
) : TorsoInjuries(player, body, plugin) {
    override val boneState: BoneState
        get() = super.boneState
    override fun brokeBone(time: Int) {
        super.brokeBone(time)
    }
    fun sprainBone(time: Int){
        super.pain(time)
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