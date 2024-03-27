package net.craftoriya.realinjuring.injuring

import com.github.shynixn.mccoroutine.bukkit.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import net.craftoriya.realinjuring.config.InjuryConfig
import net.craftoriya.realinjuring.injuring.TorsoInjuries.BleedingStates.*
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

open class TorsoInjuries (
    private val player: Player,
    private val body: PlayerBody,
    plugin: JavaPlugin,
    private val injuryConfig: InjuryConfig
) {

    init {
        plugin.launch {
            while (isActive){
                when{
                    bleedingState == LIGHT -> {
                        player.damage(0.2, injuryConfig.BLEEDING_DAMAGE_SOURCE)
                    }
                    bleedingState == HEAVY -> {
                        player.damage(0.35, injuryConfig.BLEEDING_DAMAGE_SOURCE)
                    }
                    bleedingState == LACERATION -> {
                        player.damage(0.5, injuryConfig.BLEEDING_DAMAGE_SOURCE)
                    }
                }
                delay(injuryConfig.DELAY_BETWEEN_DAMAGE)
            }
        }
    }
    enum class painState{
        PAIN,
        SEVER_PAIN,
        HEALTHY
    }
    sealed class BoneStates {
        object HEALTHY : BoneStates()
        object SPRAINED : BoneStates()
        sealed class BROKEN : BoneStates() {
            object LVL1 : BROKEN()
            object LVL2 : BROKEN()
            object LVL3 : BROKEN()
        }
        object CURED : BoneStates()
    }
    open var boneStates: BoneStates = BoneStates.HEALTHY

    enum class BleedingStates {
        HEALTHY,
        LIGHT,
        HEAVY,
        LACERATION,
    }
    open var bleedingState: BleedingStates = HEALTHY
    open fun burn( time: Int) {

    }
}