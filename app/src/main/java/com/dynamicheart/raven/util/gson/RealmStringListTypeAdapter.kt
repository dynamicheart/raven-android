package com.dynamicheart.raven.util.gson

import com.dynamicheart.raven.util.realm.RealmString
import com.google.gson.JsonDeserializer
import com.google.gson.JsonSerializer
import io.realm.RealmList
import com.google.gson.JsonElement
import com.google.gson.JsonArray
import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonSerializationContext
import java.lang.reflect.Type


class RealmStringListTypeAdapter : JsonSerializer<RealmList<RealmString>>, JsonDeserializer<RealmList<RealmString>> {
    override fun serialize(src: RealmList<RealmString>, typeOfSrc: Type,
                           context: JsonSerializationContext): JsonElement {
        val ja = JsonArray()
        src.forEach {
            ja.add(context.serialize(it))
        }
        return ja
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type,
                    context: JsonDeserializationContext): RealmList<RealmString> {
        val tags = RealmList<RealmString>()
        val ja = json.asJsonArray
        ja.mapTo(tags) { context.deserialize<Any>(it, RealmString::class.java) as RealmString }
        return tags
    }
}