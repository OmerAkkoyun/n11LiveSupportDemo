package com.omerakkoyun.n11livesupportdemo.presentation.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.omerakkoyun.n11livesupportdemo.presentation.chat.viewholders.BotButtonsViewHolder
import com.omerakkoyun.n11livesupportdemo.presentation.chat.viewholders.BotTextViewHolder
import com.omerakkoyun.n11livesupportdemo.presentation.chat.viewholders.BotImageViewHolder
import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import com.omerakkoyun.n11livesupportdemo.databinding.ItemBotButtonsVerticalChatBinding
import com.omerakkoyun.n11livesupportdemo.databinding.ItemBotImageChatBinding
import com.omerakkoyun.n11livesupportdemo.databinding.ItemBotTextChatBinding
import com.omerakkoyun.n11livesupportdemo.databinding.ItemUserTextChatBinding
import com.omerakkoyun.n11livesupportdemo.databinding.ItemWelcomeViewBinding
import com.omerakkoyun.n11livesupportdemo.presentation.chat.viewholders.UserTextViewHolder
import com.omerakkoyun.n11livesupportdemo.presentation.chat.viewholders.BotWelcomeViewHolder
import com.omerakkoyun.n11livesupportdemo.utils.enums.ChatViewType

/**
 * Created by Omer AKKOYUN on 9.10.2024.
 */
class ChatBotAdapter(
    private val stepItems: ArrayList<StepItem>,
    private val botButtonClick: (action: String, selectedButton: String) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val typeName = stepItems[position].type
        return when (typeName) {
            ChatViewType.BOT_WELCOME.typeName -> 0
            ChatViewType.BOT_BUTTON.typeName -> 1
            ChatViewType.BOT_TEXT.typeName -> 2
            ChatViewType.BOT_IMAGE.typeName -> 3
            ChatViewType.USER_TEXT.typeName -> 4
            else -> -1
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            1 -> BotButtonsViewHolder(ItemBotButtonsVerticalChatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            2 -> BotTextViewHolder(ItemBotTextChatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            3 -> BotImageViewHolder(ItemBotImageChatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            4 -> UserTextViewHolder(ItemUserTextChatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            0 -> BotWelcomeViewHolder(ItemWelcomeViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> EmptyViewHolder(View(parent.context))
        }
    }

    override fun getItemCount() = stepItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (stepItems[position].type) {
            ChatViewType.BOT_TEXT.typeName -> (holder as BotTextViewHolder).bind(stepItems[position])
            ChatViewType.BOT_IMAGE.typeName -> (holder as BotImageViewHolder).bind(stepItems[position])
            ChatViewType.BOT_BUTTON.typeName -> (holder as BotButtonsViewHolder).bind(stepItems[position], botButtonClick)
            ChatViewType.USER_TEXT.typeName -> (holder as UserTextViewHolder).bind(stepItems[position])
            ChatViewType.BOT_WELCOME.typeName -> (holder as BotWelcomeViewHolder).bind()
            else -> EmptyViewHolder(View(holder.itemView.context))
        }
    }

    fun addChatData(newChatData: StepItem) {
        val newChatDataList = ArrayList(stepItems)
        newChatDataList.add(newChatData)

        val diffResult = DiffUtil.calculateDiff(ChatDataDiffCallback(stepItems, newChatDataList))
        stepItems.clear()
        stepItems.addAll(newChatDataList)

        diffResult.dispatchUpdatesTo(this)

        if (stepItems.size == 1) {
            val welcomeItem = StepItem(action = null, step = null, type = ChatViewType.BOT_WELCOME.typeName, content = null)
            stepItems.add(0,welcomeItem)
        }
    }

}


class ChatDataDiffCallback(
    private val oldList: List<StepItem>,
    private val newList: List<StepItem>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].step == newList[newItemPosition].step
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

class EmptyViewHolder(itemView: View) : ViewHolder(itemView)