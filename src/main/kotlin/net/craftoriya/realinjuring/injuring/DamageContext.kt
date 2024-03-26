package net.craftoriya.realinjuring.injuring

import org.bukkit.damage.DamageType
import org.bukkit.entity.Entity

data class DamageContext(
    val source: Entity?,
    val type: DamageType,
    val damage: Double,
)
