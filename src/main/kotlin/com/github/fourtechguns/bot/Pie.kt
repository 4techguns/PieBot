package com.github.fourtechguns.bot

import com.github.kaktushose.jda.commands.JDACommands
import dev.minn.jda.ktx.events.listener
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent


class Pie(val jda: JDA) {

    fun start() {
        jda.awaitReady()

        jda.presence.setPresence(
            Activity.of(
                Activity.ActivityType.COMPETING,
                "${jda.guilds.size} guilds"),
            false)

        JDACommands.start(jda, Pie::class.java, "com.github.fourtechguns.commands")

        jda.listener<MessageReceivedEvent> {
            if (it.message.referencedMessage != null && it.message.contentRaw == "ratio")
            {
                it.message.addReaction("❤").queue()
            }
        }

        jda.listener<MessageReactionAddEvent> { re ->
            if (re.user?.isBot == true) return@listener

            when (re.reactionEmote.emoji)
            {
                "❤" -> { re.retrieveMessage().queue {m ->
                            m.reactions.find { re.reactionEmote.emoji == "❤" }!!.retrieveUsers().queue { r ->
                                    if (r.size > 10) {
                                        m.channel.sendMessage("${m.author.asMention}, you have successfully ratioed ${m.referencedMessage!!.author.asMention}")
                                            .queue {
                                                m.delete().queue()
                                            }

                                    }

                                }
                        }
                }
            }
        }
    }
}