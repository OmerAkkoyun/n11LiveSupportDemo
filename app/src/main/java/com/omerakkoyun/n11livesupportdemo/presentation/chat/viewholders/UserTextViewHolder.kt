package com.omerakkoyun.n11livesupportdemo.presentation.chat.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.omerakkoyun.n11livesupportdemo.data.models.ContentResponse
import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import com.omerakkoyun.n11livesupportdemo.databinding.ItemUserTextChatBinding

/**
 * Created by Omer AKKOYUN on 9.10.2024.
 */
class UserTextViewHolder(private val binding: ItemUserTextChatBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(stepItemData: StepItem){
        val stepItem = stepItemData.content as ContentResponse.TextContent
        binding.tvText.text = stepItem.text
    }

}