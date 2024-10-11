package com.omerakkoyun.n11livesupportdemo.presentation.chat.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerakkoyun.n11livesupportdemo.data.models.ContentResponse
import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import com.omerakkoyun.n11livesupportdemo.databinding.ItemBotImageChatBinding

/**
 * Created by Omer AKKOYUN on 9.10.2024.
 */
class BotImageViewHolder(private val binding: ItemBotImageChatBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(stepItemData: StepItem){
        val testImage = "https://www.donanimhaber.com/images/images/haber/165631/src_340x1912xgetir-tarafindan-satin-alinan-n11-renklerini-degistirdi.jpg"
        val stepItem = stepItemData.content as ContentResponse.TextContent
        Glide.with(binding.imageView.context)
            .load(testImage)
            .into(binding.imageView);
    }

}