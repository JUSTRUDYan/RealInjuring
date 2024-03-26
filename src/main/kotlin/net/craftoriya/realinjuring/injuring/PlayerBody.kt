package net.craftoriya.realinjuring.injuring

import com.github.shynixn.mccoroutine.bukkit.launch
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
    private val plugin: JavaPlugin,
    injuryConfig: InjuryConfig,
) {
    val head = Injuries(player, this, plugin)
    val torso = TorsoInjuries(player, this, plugin)
    val stomach = TorsoInjuries(player, this, plugin)
    val lHand = LimbInjuries(player, this, plugin)
    val rHand = LimbInjuries(player, this, plugin)
    val lLeg = LimbInjuries(player, this, plugin)
    val rLeg = LimbInjuries(player, this, plugin)

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
        plugin.launch {
            while (isActive) {
                when (shockState) {
                    ShockState.SHOCKED -> {
                        shock((injuryConfig.shockingTime / 50).toInt())
                        delay(injuryConfig.shockingTime)
                    }

                    ShockState.POST_SHOCKED -> {
                        println(333)
                        postShock((injuryConfig.postShokingTime / 50).toInt())
                        delay(injuryConfig.postShokingTime)
                    }

                    else -> {}
                }
                delay(500)
            }
        }
    }
    private suspend fun shock(time: Int){
        player.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, time, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, time, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, time, 0))
    }
    private suspend fun postShock(time: Int){
        player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_DIGGING, time, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, time, 0))
    }
}