package com.github.fourtechguns.commands

import dev.minn.jda.ktx.messages.send
import me.devoxin.flight.api.Context
import me.devoxin.flight.api.annotations.Command
import me.devoxin.flight.api.entities.Cog
import net.dv8tion.jda.api.entities.Activity.ActivityType
import net.dv8tion.jda.api.entities.Activity.of
import net.dv8tion.jda.api.entities.MessageChannel

class Hello : Cog {

    @Command(description = "hi")
    fun hi(ctx: Context) {
        ctx.send("hi")
    }

    @Command(description = "checks latency between bot and discord's servers")
    fun ping(ctx: Context) {
        val channel: MessageChannel = ctx.messageChannel
        val time = System.currentTimeMillis()

        ctx.messageChannel.send("pong")
            .queue {
                it.editMessageFormat("pong: `%d ms`", System.currentTimeMillis() - time).queue();
            }

    }

}