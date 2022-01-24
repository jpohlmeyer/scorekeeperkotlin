package com.github.jpohlmeyer.scorekeeperkotlin.model

import kotlin.reflect.KClass

enum class GameType(val gameTypeName: String, val gameClass: KClass<out Game>){
    SIMPLE_GAME("Simple Game", SimpleGame::class)
}