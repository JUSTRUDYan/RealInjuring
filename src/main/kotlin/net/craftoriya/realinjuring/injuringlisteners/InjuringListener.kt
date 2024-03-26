package net.craftoriya.realinjuring.injuringlisteners

import net.craftoriya.realinjuring.config.InjuryConfig
import net.craftoriya.realinjuring.injuring.PlayerBody
import org.bukkit.entity.Player
import org.bukkit.damage.DamageType
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.ConcurrentHashMap
import kotlin.random.Random

class InjuringListener(
    private val plugin: JavaPlugin,
    private val injuryConfig: InjuryConfig,
): Listener {
    val playersBodys: ConcurrentHashMap<Player,PlayerBody> = ConcurrentHashMap()
    @EventHandler
    fun onPlayerDamage(event: EntityDamageEvent){
        if (event.entity.type != EntityType.PLAYER)
            return
        when(event.damageSource.damageType){
            DamageType.ARROW -> onArrow(event)
            DamageType.EXPLOSION -> onExplosion(event)
            DamageType.FALL -> onFall(event)
        }
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent){
        val player: Player = event.player
        playersBodys[player] = PlayerBody(player, plugin, injuryConfig)
    }
    fun onArrow(event: EntityDamageEvent){

    }
    fun onExplosion(event: EntityDamageEvent){

    }
    fun onFall(event: EntityDamageEvent){
        val player: Player = event.entity as Player
        val fallDistance: Float = player.fallDistance
        val body: PlayerBody = playersBodys[player] ?: return

        body.shockAlgorithm.start()
        if(fallDistance >= 15f){
            body.lLeg.brokeBone((injuryConfig.brokenBoneLvl3/50).toInt())
            body.rLeg.brokeBone((injuryConfig.brokenBoneLvl3/50).toInt())
            return
        } else if (fallDistance >= 5) {
            if(Random.nextBoolean() == true) {
                body.lLeg.brokeBone(
                    when {
                        fallDistance >= 13f -> (injuryConfig.brokenBoneLvl1 / 50).toInt()
                        fallDistance >= 6f -> (injuryConfig.brokenBoneLvl2 / 50).toInt()
                        else -> return
                    }
                )
            }else{
                body.rLeg.brokeBone(
                    when {
                        fallDistance >= 13f -> (injuryConfig.brokenBoneLvl1 / 50).toInt()
                        fallDistance >= 6f -> (injuryConfig.brokenBoneLvl2 / 50).toInt()
                        else -> return
                    }
                )
            }
        } else {

        }
    }
}