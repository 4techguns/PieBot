package com.github.fourtechguns.bot

import dev.minn.jda.ktx.events.listener
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent

class Pie {
    val jda: JDA

    constructor(jda: JDA) {
        this.jda = jda
    }

    fun start() {
        jda.awaitReady()

        jda.presence.setPresence(
            Activity.of(
                Activity.ActivityType.COMPETING,
                "${jda.guilds.size} guilds"),
            false)

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
                "❤" -> { it.retrieveMessage().queue {m ->
                            m.reactions.find { it.reactionEmote.emoji == "❤" }!!.retrieveUsers().queue { r ->
                                    if (r.size > 10) {
                                        m.channel.sendMessage("${m.author.asMention}, you have successfully ratioed ${m.referencedMessage!!.author.asMention}")
                                            .queue {fm ->
                                                fm.delete().queue()
                                            }

                                    }

                                }
                        }
                }
            }
        }
    }
}