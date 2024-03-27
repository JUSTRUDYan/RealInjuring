package net.craftoriya.realinjuring.config

import org.bukkit.damage.DamageSource
import org.bukkit.damage.DamageType

final data class InjuryConfig(

    /* SHOCK */

    val SHOCKING_DURATION: Long = 10 * 1000,
    val POST_SHOCKING_DURATION: Long = 45 * 1000,

    /* BONES */

    val BROKEN_BONE_DURATION_LVL1: Long = 10 * 1000,
    val BROKEN_BONE_DURATION_LVL2: Long = 15 * 1000,
    val BROKEN_BONE_DURATION_LVL3: Long = 30 * 1000,

    val HEALTHY_TO_SPRINED_BONE: Boolean = true,

    val CURED_TO_BROKEN_BONE_CHANCE: Double = 0.04,
    val SPRAINED_TO_BROKEN_BONE_CHANCE: Double = 0.05,
    val HEALTHY_TO_SPRINED_BONE_CHANCE: Double = 0.01,
    //FIXME
    //val JUMP_DAMAGE_SOURCE: DamageSource = DamageSource.builder(DamageType.FALL).build(),

    val BROKEN_BONE_JUMP_DAMAGE_LVL1: Double = 0.5,
    val BROKEN_BONE_JUMP_DAMAGE_LVL2: Double = 0.7,
    val BROKEN_BONE_JUMP_DAMAGE_LVL3: Double = 1.0,
    val SPRAINED_BONE_JUMP_DAMAGE: Double = 0.5,
    val CURED_BONE_JUMP_DAMAGE: Double = 0.5,

    /* BLEEDING */
    //FIXME
    //val BLEEDING_DAMAGE_SOURCE: DamageSource = DamageSource.builder(DamageType.MAGIC).build(),
    val DELAY_BETWEEN_DAMAGE: Long = 5 * 1000
)
