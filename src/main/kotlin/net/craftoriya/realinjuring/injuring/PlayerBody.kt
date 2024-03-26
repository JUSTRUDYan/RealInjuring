package net.craftoriya.realinjuring.injuring

import com.github.shynixn.mccoroutine.bukkit.minecraftDispatcher
import kotlinx.coroutines.*
import net.craftoriya.lib.actions.queue.ActionsQueue
import net.craftoriya.lib.bukkit.BukkitSynchronizer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.time.Duration

class PlayerBody(
    private val player: Player,
    plugin: JavaPlugin,
) {
    val head: Injuries = Injuries(player)
    val torso: Injuries = Injuries(player)
    val stomach: Injuries = Injuries(player)
    val lHand: Injuries = Injuries(player)
    val rHand: Injuries = Injuries(player)
    val lLeg: Injuries = Injuries(player)
    val rLeg: Injuries = Injuries(player)

    private enum class ShockState {
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
                    ShockState.SHOCKED -> shock()
                    ShockState.POST_SHOCKED -> postShock()
                    else -> continue
                }
                delay(1000)
            }
        }
    }
    private fun shock(){
        player.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 20, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 20, 0))
    }
    private fun postShock(){
        player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_DIGGING, 20, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, 20, 0))
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