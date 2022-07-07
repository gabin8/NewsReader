package com.gabin.newsreader.data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.*

object DateSerializer : KSerializer<Date> {

    // Date has following format: "2022-07-06T18:46:28Z"
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    override fun deserialize(decoder: Decoder): Date {
        return simpleDateFormat.parse(decoder.decodeString())!!
    }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(value.toString())
    }
}
