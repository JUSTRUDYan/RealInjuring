package net.craftoriya.realinjuring.realinjuringmanager

import kotlinx.coroutines.*
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import javax.swing.text.StyledEditorKit.BoldAction

class InjuringManager(
    player: Player
) {
    private val lightBleeding: Boolean = false
    var bleedingEndTime: Int? = null
    private val hevayBleeding: Boolean = false
    private val brokenBone: Boolean = false
    init {
        CoroutineScope(Dispatchers.Default).launch {
            while (isActive){
                if (lightBleeding){

                }
            }
        }
    }
}