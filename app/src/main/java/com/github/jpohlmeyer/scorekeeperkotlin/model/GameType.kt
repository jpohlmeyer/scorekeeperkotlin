package com.github.jpohlmeyer.scorekeeperkotlin.model

import com.github.jpohlmeyer.scorekeeperkotlin.model.simplegame.SimpleGame
import com.github.jpohlmeyer.scorekeeperkotlin.model.skullkinggame.SkullKingGame
import kotlin.reflect.KClass

enum class GameType(val gameTypeName: String, val gameClass: KClass<out Game>){
    SIMPLE_GAME("Simple Game", SimpleGame::class),
    SKULL_KING_GAME("Skull King Game", SkullKingGame::class)
}