package net.craftoriya.realinjuring.injuring

import com.github.shynixn.mccoroutine.bukkit.minecraftDispatcher
import kotlinx.coroutines.*
import net.craftoriya.lib.actions.queue.ActionsQueue
import net.craftoriya.lib.bukkit.BukkitSynchronizer
import net.craftoriya.realinjuring.config.InjuryConfig
import net.craftoriya.realinjuring.injuringlisteners.InjuringListener
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.time.Duration

class PlayerBody(
    private val player: Player,
    plugin: JavaPlugin,
    injuryConfig: InjuryConfig,
) {
    val head: Injuries = Injuries(player, this)
    val torso: Injuries = Injuries(player, this)
    val stomach: Injuries = Injuries(player, this)
    val lHand: Injuries = Injuries(player, this)
    val rHand: Injuries = Injuries(player, this)
    val lLeg: Injuries = Injuries(player, this)
    val rLeg: Injuries = Injuries(player, this)

    enum class ShockState {
        HEALTHY,
        SHOCKED,
        POST_SHOCKED,
    }

    private var shockState: ShockState = ShockState.HEALTHY

    val shockAlgorithm = ActionsQueue.createQueue(
        BukkitSynchronizer(plugin)
    ) {
        action {
            outDelay(Duration.ofSeconds(5))
            onRun {
                println(1111)
                shockState = ShockState.SHOCKED
            }
        }
        action {
            outDelay(Duration.ofSeconds(10))
            onRun {
                println(2222)
                shockState = ShockState.POST_SHOCKED
            }
        }
        action {
            onRun {
                println(3333)
                shockState = ShockState.HEALTHY
            }
        }
    }

    init {
        CoroutineScope(plugin.minecraftDispatcher).launch {
            while (isActive){
                when (shockState) {
                    ShockState.SHOCKED -> {
                        shock((injuryConfig.shockingTime / 50).toInt())
                        delay(injuryConfig.shockingTime)
                    }
                    ShockState.POST_SHOCKED -> {
                        postShock((injuryConfig.postShokingTime / 50).toInt())
                        delay(injuryConfig.postShokingTime)
                    }
                    else -> continue
                }
                delay(100)
            }
        }
    }
    private fun shock(time: Int){
        player.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, time, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, time, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, time, 0))
    }
    private fun postShock(time: Int){
        player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_DIGGING, time, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, time, 0))
    }

    fun isLeftHandInjured(): Boolean {
        if(lLeg.laceration) return false
        return true
    }
    fun isRightHandInjured(): Boolean{
        if(rLeg.laceration) return false
        return true
    }
}