package net.craftoriya.realinjuring.injuring

import net.craftoriya.lib.actions.queue.ActionsQueue
import net.craftoriya.lib.bukkit.BukkitSynchronizer
import net.craftoriya.realinjuring.config.InjuryConfig
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.time.Duration

class LimbInjuries (
    private val player: Player,
    private val body: PlayerBody,
    private val plugin: JavaPlugin,
    private val injuryConfig: InjuryConfig,
) : TorsoInjuries(player, body, plugin, injuryConfig) {

    val brokeBoneAlgorithm = ActionsQueue.createQueue(
        BukkitSynchronizer(plugin)
    ) {
        action {
            outDelay(Duration.ofMillis(injuryConfig.SHOCKING_DURATION))
            onRun {
            }
        }
        action {
            outDelay(Duration.ofMillis(injuryConfig.POST_SHOCKING_DURATION))
            onRun {
            }
        }
        action {
            onRun {
            }
        }
    }
    override var bleedingState: BleedingStates
        get() = super.bleedingState
        set(value) {}

    override var boneStates: BoneStates = BoneStates.HEALTHY
        get() = super.boneStates
    override fun burn(time: Int) {
        super.burn(time)
    }
}