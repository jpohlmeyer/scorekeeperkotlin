package com.github.jpohlmeyer.scorekeeperkotlin.model

import com.github.jpohlmeyer.scorekeeperkotlin.model.simplegame.SimpleGame
import kotlin.reflect.KClass

enum class GameType(val gameTypeName: String, val gameClass: KClass<out Game>) {
    SIMPLE_GAME("Simple Game", SimpleGame::class),

    // TODO: enable the Skull King game mode.
}