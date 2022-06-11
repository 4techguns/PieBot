package com.github.fourtechguns.bot

import com.github.kaktushose.jda.commands.JDACommands
import dev.minn.jda.ktx.events.listener
import dev.minn.jda.ktx.jdabuilder.default
import dev.minn.jda.ktx.jdabuilder.intents
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.api.requests.GatewayIntent
import java.util.*

fun main(args: Array<String>) {
    val shhhhhhhhhhhhhhhhhhhhhhhhhh: Properties = PropsManager.getProps("/conf/bot_secrets.properties")
    val botconf: Properties = PropsManager.getProps("/conf/bot.properties")

    var jda = default(shhhhhhhhhhhhhhhhhhhhhhhhhh.getProperty("token"), enableCoroutines = true)
    {
        intents += listOf(
            GatewayIntent.GUILD_MESSAGE_REACTIONS,
            GatewayIntent.GUILD_MESSAGES
        )
    }
    val pie: Pie = Pie(jda)
    pie.start()
}
