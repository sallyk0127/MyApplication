package com.example.myapplication.model

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class EntityDeserializer : JsonDeserializer<Entity> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Entity {
        val jsonObject = json.asJsonObject

        val type = object : TypeToken<Map<String, Any?>>() {}.type
        val data: Map<String, Any?> = context.deserialize(jsonObject, type)

        // Extract optional "description" if needed
        val mutableData = data.toMutableMap()
        val description = mutableData.remove("description")?.toString()

        return Entity(data = mutableData, description = description)
    }
}
