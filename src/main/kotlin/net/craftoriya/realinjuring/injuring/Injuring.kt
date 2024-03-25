package net.craftoriya.realinjuring.injuring

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import org.bukkit.entity.Player
import net.minecraft.world.item.alchemy.Potion
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
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
        player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, time,0))
    }


    private fun pain(player: Player, time: Int){
        player.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, time, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, time, 0))
    }

    private fun shock(player: Player, time: Int){
        player.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, time, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, time, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, time, 0))
    }
    private fun afterShock(player: Player, time: Int){
        player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_DIGGING, time, 0))
        player.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, time, 0))
    }

    private fun poison(player: Player, time: Int){
        player.addPotionEffect(PotionEffect(PotionEffectType.POISON, time,0))
    }
}