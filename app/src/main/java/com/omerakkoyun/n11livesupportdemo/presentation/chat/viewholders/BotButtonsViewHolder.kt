package com.omerakkoyun.n11livesupportdemo.presentation.chat.viewholders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.omerakkoyun.n11livesupportdemo.data.models.ContentResponse
import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import com.omerakkoyun.n11livesupportdemo.databinding.ItemBotButtonsVerticalChatBinding
import com.omerakkoyun.n11livesupportdemo.databinding.ItemButtonBinding

/**
 * Created by Omer AKKOYUN on 9.10.2024.
 */
class BotButtonsViewHolder(private val binding: ItemBotButtonsVerticalChatBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stepItemData: StepItem, botVerticalButtonClick: (chatData: String,selectedButton: String) -> Unit){
        val stepItem = stepItemData.content as ContentResponse.ButtonContent
        binding.tvText.text = stepItem.text

        binding.lnLayoutButtons.removeAllViews()
        stepItem.buttons?.forEach { buttonData ->
            // item button view
            val buttonBinding = ItemButtonBinding.inflate(LayoutInflater.from(binding.root.context), binding.lnLayoutButtons, false)
            buttonBinding.buttonItem.text = buttonData?.label
            binding.lnLayoutButtons.addView(buttonBinding.root)

            buttonBinding.buttonItem.setOnClickListener {
                buttonData?.action?.let { action -> botVerticalButtonClick.invoke(action, buttonData.label!!) }
            }
        }

    }

}