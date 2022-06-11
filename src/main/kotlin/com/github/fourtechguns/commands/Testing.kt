package com.github.fourtechguns.commands

import com.github.kaktushose.jda.commands.annotations.Command
import com.github.kaktushose.jda.commands.annotations.CommandController
import com.github.kaktushose.jda.commands.dispatching.CommandEvent
import dev.minn.jda.ktx.messages.send

@CommandController
class Testing {
    @Command(value=["hi"], category = "Testing")
    fun hi(event: CommandEvent) {
        event.reply("hi")
    }

    @Command(value=["ping"], category = "Testing")
    fun ping(event: CommandEvent) {
        val channel = event.channel
        val time = System.currentTimeMillis()

        channel.send("pong")
            .queue {
                it.editMessageFormat("pong: `%d ms`", System.currentTimeMillis() - time).queue();
            }
    }
}