package com.example.myapplication.model

import com.google.gson.*
import java.lang.reflect.Type

class EntityDeserializer : JsonDeserializer<Entity> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Entity {
        val jsonObject = json.asJsonObject
        val dataMap = mutableMapOf<String, String?>()

        for ((key, value) in jsonObject.entrySet()) {
            dataMap[key] = if (value.isJsonNull) null else value.asString
        }

        return Entity(data = dataMap)
    }
}
