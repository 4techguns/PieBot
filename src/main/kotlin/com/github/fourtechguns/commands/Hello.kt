package com.github.fourtechguns.commands

import com.github.kaktushose.jda.commands.annotations.Command
import com.github.kaktushose.jda.commands.annotations.CommandController
import com.github.kaktushose.jda.commands.dispatching.CommandEvent
import dev.minn.jda.ktx.messages.send
import net.dv8tion.jda.api.entities.MessageChannel

@CommandController
public class Hello {
    @Command(value=["hi"])
    fun hi(event: CommandEvent) {
        event.reply("hi")
    }
}