package com.github.fourtechguns.bot

import dev.minn.jda.ktx.events.listener
import dev.minn.jda.ktx.jdabuilder.default
import dev.minn.jda.ktx.jdabuilder.intents
import me.devoxin.flight.api.CommandClient
import me.devoxin.flight.api.CommandClientBuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.api.requests.GatewayIntent
import java.util.*

@OptIn(ExperimentalStdlibApi::class)
fun main(args: Array<String>) {
    var r: MutableSet<String> = mutableSetOf()

    val shhhhhhhhhhhhhhhhhhhhhhhhhh: Properties = PropsManager.GetProps("/conf/bot_secrets.properties")
    val botconf: Properties = PropsManager.GetProps("/conf/bot.properties")

    val commandClient: CommandClient = CommandClientBuilder()
        .setPrefixes("pie ", "🥧 ", ":pie: ")
        .setOwnerIds(752617663888359444)
        .registerDefaultParsers()
        .build()

    var jda = default(shhhhhhhhhhhhhhhhhhhhhhhhhh.getProperty("token"), enableCoroutines = true)
    {
        intents += listOf(
            GatewayIntent.GUILD_MESSAGE_REACTIONS,
            GatewayIntent.GUILD_MESSAGES
        )
    }
        .awaitReady()
    jda.presence.setPresence(
        Activity.of(
            Activity.ActivityType.COMPETING,
            "${jda.guilds.size} guilds"),
        false)
    jda.addEventListener(commandClient)
    commandClient.commands.register("com.github.fourtechguns.commands")

    jda.listener<MessageReceivedEvent> {
        if (it.message.referencedMessage != null && it.message.contentRaw == "ratio")
        {
            it.message.addReaction("❤").queue()
        }
    }

    jda.listener<MessageReactionAddEvent> {
        if (it.user?.isBot == true) return@listener

        when (it.reactionEmote.emoji)
        {
            "❤" -> {
                it.retrieveMessage()
                    .queue {m ->
                        m.reactions.find { it.reactionEmote.emoji == "❤" }!!.retrieveUsers()
                            .queue {
                                r ->
                                if (r.size > 2) {
                                    m.channel.sendMessage("${m.author.asMention}, you have successfully ratioed ${m.referencedMessage!!.author.asMention}")
                                        .queue()
                                    m.delete().queue()

                                }

                            }
                    }
            }
        }
    }
}
