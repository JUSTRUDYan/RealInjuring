package net.craftoriya.realinjuring.injuring

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.alchemy.Potion
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffect
import kotlin.contracts.Effect

class Injuring(player: Player) {
    fun bleeding(event: EntityDamageEvent, time: Int, size: Float) {

    }
    fun laceration(event: EntityDamageEvent, time: Int, size: Float){

    }
    fun lightBleeding(event: EntityDamageEvent, time: Int, size: Float){

    }
    fun burn(event: EntityDamageEvent, time: Int) {

    }
    fun brokenBone(event: EntityDamageEvent, time: Int) {
        val player: Player = event.entity as Player
        pain(player, time)
        shock(player,time/10)
        player.addEffect(MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, time))
    }


    private fun pain(player: Player, time: Int){
        player.addEffect(MobEffectInstance(MobEffects.BLINDNESS, time))
        player.addEffect(MobEffectInstance(MobEffects.WEAKNESS, time))
    }

    private fun shock(player: Player, time: Int){
        player.addEffect(MobEffectInstance(MobEffects.DAMAGE_BOOST, time))
        player.addEffect(MobEffectInstance(MobEffects.MOVEMENT_SPEED, time))
        player.addEffect(MobEffectInstance(MobEffects.REGENERATION, time))
    }
    private fun afterShock(player: Player, time: Int){
        player.addEffect(MobEffectInstance(MobEffects.DIG_SLOWDOWN, time))
        player.addEffect(MobEffectInstance(MobEffects.HUNGER, time))
    }

    private fun poison(player: Player, time: Int){
        player.addEffect(MobEffectInstance(MobEffects.POISON, time))
    }
}