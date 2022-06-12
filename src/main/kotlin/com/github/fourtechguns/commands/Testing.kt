package com.github.fourtechguns.commands

import com.github.kaktushose.jda.commands.annotations.Command
import com.github.kaktushose.jda.commands.annotations.CommandController
import com.github.kaktushose.jda.commands.dispatching.CommandEvent
import dev.minn.jda.ktx.messages.send

@CommandController
public class Testing {
    @Command(
        value=["hi", "hello", "greetings", "hallo", "konichiwa", "bonjour", "salut"],
        category = "Testing", desc = "a friendly greeting", usage = "{prefix}hi")
    fun hi(event: CommandEvent) {
        event.reply("hi")
    }

    @Command(value=["ping"], category = "Testing", desc = "what bot doesn't have a ping command?"
    , usage = "{prefix}ping")
    fun ping(event: CommandEvent) {
        val channel = event.channel
        val time = System.currentTimeMillis()

        channel.send("pong")
            .queue {
                it.editMessageFormat("pong: `%d ms`", System.currentTimeMillis() - time).queue();
            }
    }
}