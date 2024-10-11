package com.omerakkoyun.n11livesupportdemo.utils.helpers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.omerakkoyun.n11livesupportdemo.data.models.ContentResponse
import java.lang.reflect.Type

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */

class ContentResponseDeserializer : JsonDeserializer<ContentResponse> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ContentResponse {
        return if (json.isJsonObject && json.asJsonObject.has("buttons")) {
            context.deserialize(json, ContentResponse.ButtonContent::class.java)
        } else if (json.isJsonObject && json.asJsonObject.has("text")) {
            context.deserialize(json, ContentResponse.TextContent::class.java)
        } else {
            ContentResponse.TextContent(json.asString)
        }
    }
}