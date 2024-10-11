package com.omerakkoyun.n11livesupportdemo.data.models

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */
sealed class ContentResponse {
    data class TextContent(val text: String) : ContentResponse()
    data class ImageContent(val text: String) : ContentResponse()
    data class ButtonContent(
        val buttons: List<Button?>?,
        val text: String?
    ) : ContentResponse()

}