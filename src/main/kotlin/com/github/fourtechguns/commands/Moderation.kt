package com.github.fourtechguns.commands

import com.github.kaktushose.jda.commands.annotations.*
import com.github.kaktushose.jda.commands.dispatching.CommandEvent
import dev.minn.jda.ktx.messages.send
import net.dv8tion.jda.api.entities.Member
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@CommandController
public class Moderation {
    @Command("ban", category = "Moderation", usage = "{prefix}ban <user> [reason]")
    @Permission("BAN_MEMBERS")
    fun ban(event: CommandEvent, user: Member, @Optional @Concat reason: String?) {
        val banGifs = listOf(
            "https://tenor.com/view/when-your-team-too-good-ban-salt-bae-gif-7580925",
            "https://tenor.com/view/spongebob-ban-pubg-lite-banned-rainbow-gif-16212382",
            "https://tenor.com/view/itskinglyme-banned-bye-gif-22722831",
            "https://tenor.com/view/ban-banned-mario-mad-angry-gif-17356254"
        )
        user.ban(0, reason).queue()

        event.reply("successfully banned!")
        event.reply(banGifs[Random(69).nextInt(banGifs.size)])
    }

    @Command("timeout", usage = "{prefix}timeout <user> <seconds>", category = "Moderation")
    @Permission("MODERATE_MEMBERS")
    fun timeout(event: CommandEvent, user: Member, seconds: Long)
    {
        user.timeoutFor(seconds, TimeUnit.SECONDS)
        event.reply("timed out")
    }
}