package net.craftoriya.realinjuring.injuring

import net.craftoriya.lib.actions.queue.ActionsQueue
import net.craftoriya.lib.bukkit.BukkitSynchronizer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.time.Duration

open class TorsoInjuries (
    private val player: Player,
    private val body: PlayerBody,
    private val plugin: JavaPlugin,
) : Injuries(player, body, plugin) {
    enum class BoneState{
        HEALTHY,
        SPRAINED,
        BROKEN,
        CURED,
    }
    open var boneState: BoneState = BoneState.HEALTHY

    open fun laceration(time: Int, strength: Float){

    }
    open fun lightBleeding(time: Int){

    }
    open fun heavyBleeding(){

    }
    override fun pain(time: Int) {
        super.pain(time)
    }
    open fun brokeBone(time: Int) {
        pain(time)
        player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, time,0))
    }
    open fun burn( time: Int) {

    }
    override fun poison(time: Int) {
        super.poison(time)
    }

}