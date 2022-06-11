package com.github.fourtechguns.bot

import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*


class PropsManager {
    companion object {
        fun GetProps(resource: String): Properties {
            val rsrc = javaClass.getResource(resource);
            val loadedFile: InputStream = ByteArrayInputStream(rsrc.readBytes());

            var props = Properties()
            props.load(loadedFile)

            return props
        }
    }
}