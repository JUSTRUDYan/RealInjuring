package net.craftoriya.realinjuring.injuringlisteners

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import net.craftoriya.realinjuring.config.InjuryConfig
import net.craftoriya.realinjuring.injuring.LimbInjuries
import net.craftoriya.realinjuring.injuring.PlayerBody
import net.craftoriya.realinjuring.injuring.TorsoInjuries
import net.craftoriya.realinjuring.injuring.TorsoInjuries.BoneStates.BROKEN
import net.craftoriya.realinjuring.injuring.TorsoInjuries.BoneStates.SPRAINED
import org.bukkit.damage.DamageSource
import org.bukkit.entity.Player
import org.bukkit.damage.DamageType
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.ConcurrentHashMap
import kotlin.random.Random

class InjuringListener(
    private val plugin: JavaPlugin,
    private val injuryConfig: InjuryConfig,
    private val playersBodys: ConcurrentHashMap<Player,PlayerBody>,
): Listener {
    @EventHandler
    fun onPlayerDamage(event: EntityDamageEvent) {
        if (event.entity.type != EntityType.PLAYER)
            return
        when (event.damageSource.damageType) {
            DamageType.ARROW -> onArrow(event)
            DamageType.EXPLOSION -> onExplosion(event)
            DamageType.FALL -> onFall(event)
        }
    }

    @EventHandler
    fun onPVPDamage(event: EntityDamageByEntityEvent) {
        if (event.entity.type != EntityType.PLAYER || event.damager.type != EntityType.PLAYER)
            return
        val sufferer: Player = event.entity as Player
        val offender: Player = event.damager as Player
        when (offender.inventory.itemInMainHand) {
            //TODO
        }
    }

    @EventHandler
    fun onJump(event: PlayerJumpEvent) {
        val player: Player = event.player
        val playerBody: PlayerBody = playersBodys[player] ?: return
        val lLeg: LimbInjuries = playerBody.lLeg
        val rLeg: LimbInjuries = playerBody.rLeg
        processLegInjury(lLeg, player)
        processLegInjury(rLeg, player)
    }

    private fun processLegInjury(leg: LimbInjuries, player: Player) {
        var legState: TorsoInjuries.BoneStates = leg.boneStates
        when (legState) {
            is SPRAINED -> {
                player.damage(injuryConfig.SPRAINED_BONE_JUMP_DAMAGE, DamageSource.builder(DamageType.FALL).build())
                if (Random.nextFloat() <= injuryConfig.SPRAINED_TO_BROKEN_BONE_CHANCE)
                    legState = BROKEN.LVL1
            }

            is BROKEN -> {
                when (legState) {
                    is BROKEN.LVL1 -> {
                        player.damage(injuryConfig.BROKEN_BONE_JUMP_DAMAGE_LVL1, DamageSource.builder(DamageType.FALL).build())
                    }

                    is BROKEN.LVL2 -> {
                        player.damage(injuryConfig.BROKEN_BONE_JUMP_DAMAGE_LVL2, DamageSource.builder(DamageType.FALL).build())
                    }

                    is BROKEN.LVL3 -> {
                        player.damage(injuryConfig.BROKEN_BONE_JUMP_DAMAGE_LVL3, DamageSource.builder(DamageType.FALL).build())
                    }
                }
            }

            is TorsoInjuries.BoneStates.CURED -> {
                player.damage(injuryConfig.CURED_BONE_JUMP_DAMAGE, DamageSource.builder(DamageType.FALL).build())
                if (Random.nextFloat() <= injuryConfig.CURED_TO_BROKEN_BONE_CHANCE)
                    legState = BROKEN.LVL1
            }

            is TorsoInjuries.BoneStates.HEALTHY -> {
                if (injuryConfig.HEALTHY_TO_SPRINED_BONE) return
                if (Random.nextFloat() <= injuryConfig.HEALTHY_TO_SPRINED_BONE_CHANCE)
                    legState = BROKEN.LVL1
            }
        }
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player: Player = event.player
        playersBodys[player] = PlayerBody(player, plugin, injuryConfig)
    }

    @EventHandler
    fun onDisconect(event: PlayerQuitEvent) {
        val player: Player = event.player
        val playerBody: PlayerBody = playersBodys[player] ?: return
        if (playerBody.isHealthy()){
            playersBodys.remove(player)
        }
    }

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        val player: Player = event.player
        playersBodys[player] = PlayerBody(player, plugin, injuryConfig)
    }

    fun onArrow(event: EntityDamageEvent) {
    }

    fun onExplosion(event: EntityDamageEvent) {

    }

    fun onFall(event: EntityDamageEvent) {
        val player: Player = event.entity as Player
        val fallDistance: Float = player.fallDistance
        val body: PlayerBody = playersBodys[player] ?: return

        body.shockAlgorithm.start()
        val random: Float = Random.nextFloat()
        when {
            fallDistance >= 15f -> {
                body.lLeg.boneStates = BROKEN.LVL3
                body.rLeg.boneStates = BROKEN.LVL3
            }
            fallDistance >= 7 -> {
                if (random <= 0.15) {
                    when {
                        fallDistance >= 13f -> body.lLeg.boneStates = BROKEN.LVL2
                        else -> body.lLeg.boneStates = BROKEN.LVL1
                    }
                } else if (random <= 0.3) {
                    when {
                        fallDistance >= 13f -> body.rLeg.boneStates = BROKEN.LVL2
                        else -> body.rLeg.boneStates = BROKEN.LVL1
                    }
                } else if (random <= 0.45) {
                    body.lLeg.boneStates = SPRAINED
                    body.rLeg.boneStates = SPRAINED
                } else return
            }
            fallDistance >= 6 -> {
                if (random <= 0.2) body.lLeg.boneStates = SPRAINED
                else if (random <= 0.4) body.rLeg.boneStates = SPRAINED
                else return
            }
            else -> return
        }
    }
}