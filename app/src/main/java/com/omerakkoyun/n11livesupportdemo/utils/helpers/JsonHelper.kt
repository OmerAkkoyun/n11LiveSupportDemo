package com.omerakkoyun.n11livesupportdemo.utils.helpers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.omerakkoyun.n11livesupportdemo.R
import com.omerakkoyun.n11livesupportdemo.data.models.ContentResponse
import com.omerakkoyun.n11livesupportdemo.data.models.StepItem

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */
object JsonHelper {


    // tüm steplerin listesi MODEL
    fun loadStepListFromJsonRaw(context: Context): List<StepItem> {
        val gson = GsonBuilder()
            .registerTypeAdapter(ContentResponse::class.java, ContentResponseDeserializer())  // Custom deserializer
            .create()

        val inputStream = context.resources.openRawResource(R.raw.live_support_flow)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        return gson.fromJson(jsonString, object : TypeToken<List<StepItem>>() {}.type)  // JSON'u liste olarak parse et
    }

    // json'a göre tek bir step MODEL
    fun loadStepItemFromJson(jsonString: String): StepItem {
        val gson : Gson = GsonBuilder()
            .registerTypeAdapter(ContentResponse::class.java, ContentResponseDeserializer())
            .create()

        return (gson.fromJson(jsonString, object : TypeToken<StepItem>() {}.type) ) as StepItem
    }
}


