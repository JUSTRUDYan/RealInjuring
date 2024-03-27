package net.craftoriya.realinjuring.injuring

import com.github.shynixn.mccoroutine.bukkit.launch
import kotlinx.coroutines.*
import net.craftoriya.lib.actions.queue.ActionsQueue
import net.craftoriya.lib.bukkit.BukkitSynchronizer
import net.craftoriya.realinjuring.config.InjuryConfig
import net.craftoriya.realinjuring.injuring.TorsoInjuries.BoneStates.HEALTHY
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.time.Duration

class PlayerBody(
    private val player: Player,
    private val plugin: JavaPlugin,
    injuryConfig: InjuryConfig,
) {
    val torso = TorsoInjuries(player, this, plugin, injuryConfig)
    val stomach = TorsoInjuries(player, this, plugin, injuryConfig)
    val lHand = LimbInjuries(player, this, plugin, injuryConfig)
    val rHand = LimbInjuries(player, this, plugin, injuryConfig)
    val lLeg = LimbInjuries(player, this, plugin, injuryConfig)
    val rLeg = LimbInjuries(player, this, plugin, injuryConfig)

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
            outDelay(Duration.ofMillis(injuryConfig.SHOCKING_DURATION))
            onRun {
                shockState = ShockState.SHOCKED
            }
        }
        action {
            outDelay(Duration.ofMillis(injuryConfig.POST_SHOCKING_DURATION))
            onRun {
                shockState = ShockState.POST_SHOCKED
            }
        }
        action {
            onRun {
                shockState = ShockState.HEALTHY
            }
        }
    }

    init {
        plugin.launch {
            while (isActive) {
                when (shockState) {
                    ShockState.SHOCKED -> {
                        shock()
                        delay(1000)
                    }

                    ShockState.POST_SHOCKED -> {
                        postShock()
                        delay(1000)
                    }

                    ShockState.HEALTHY -> {
                        delay(1000)
                    }
                }
            }
        }
    }
    private fun shock(){
        player.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, 23, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 23, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 23, 0))
    }
    private fun postShock(){
        player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_DIGGING, 23, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, 23, 0))
    }
    fun isHealthy(): Boolean {
        return (this.isLegHealthy() && this.isHandHealthy())
    }
    fun isLegHealthy(): Boolean{
        return (lLeg.boneStates == HEALTHY && rLeg.boneStates == HEALTHY)
    }
    fun isHandHealthy(): Boolean{
        return (lHand.boneStates == HEALTHY && rHand.boneStates == HEALTHY)
    }
}