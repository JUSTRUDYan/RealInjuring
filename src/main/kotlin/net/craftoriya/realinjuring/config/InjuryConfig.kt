package net.craftoriya.realinjuring.config

data class InjuryConfig(
    val shockingTime: Long = 10 * 1000,
    val postShokingTime: Long = 45 * 1000,

    val brokenBoneLvl1: Long = 10 * 1000,
    val brokenBoneLvl2: Long = 15 * 1000,
    val brokenBoneLvl3: Long = 30 * 1000,
)
