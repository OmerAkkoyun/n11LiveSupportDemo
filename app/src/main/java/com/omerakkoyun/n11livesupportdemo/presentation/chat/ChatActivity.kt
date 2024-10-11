package com.omerakkoyun.n11livesupportdemo.presentation.chat

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omerakkoyun.n11livesupportdemo.data.models.ContentResponse
import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import com.omerakkoyun.n11livesupportdemo.databinding.ActivityChatBinding
import com.omerakkoyun.n11livesupportdemo.presentation.chat.adapter.ChatBotAdapter
import com.omerakkoyun.n11livesupportdemo.presentation.viewmodels.ChatViewModel
import com.omerakkoyun.n11livesupportdemo.utils.Constant.FIRST_STEP
import com.omerakkoyun.n11livesupportdemo.utils.enums.ChatViewType
import com.omerakkoyun.n11livesupportdemo.utils.extensions.makeItGone
import com.omerakkoyun.n11livesupportdemo.utils.extensions.makeItVisible
import com.omerakkoyun.n11livesupportdemo.utils.extensions.scrollToBottom
import com.omerakkoyun.n11livesupportdemo.utils.helpers.JsonHelper
import dagger.hilt.android.AndroidEntryPoint
/**
 * Created by Omer AKKOYUN on 9.10.2024.
 */
@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels<ChatViewModel>()
    private lateinit var adapter: ChatBotAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        setupListeners()
        setupRecyclerView()

        /// for test websocket first step json
        viewModel.getNextStepFromWebSocket(FIRST_STEP)
    }

    private fun setupRecyclerView() {
        with(binding) {

            rvChat.layoutManager = LinearLayoutManager(this@ChatActivity, RecyclerView.VERTICAL, false)
            adapter = ChatBotAdapter(stepItems = ArrayList(), botButtonClick = ::handleAction)
            rvChat.adapter = adapter
        }

    }

    private fun setupListeners() {
        with(binding) {

            frmErrorContainer.buttonClose.setOnClickListener {
                binding.frmErrorContainer.root.makeItGone()
                closeConnection()
            }

            frmChatEndContainer.buttonContinueLiveSupport.setOnClickListener {
                binding.frmChatEndContainer.root.makeItGone()

            }

            frmChatEndContainer.buttonCloseLiveSupport.setOnClickListener {
                closeConnection()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.incomingMessagesLiveData.observe(this) { stepItem ->
            binding.progressView.root.makeItGone()
            stepItem?.let { updateUI(stepItem) }
        }

        viewModel.errorLiveData.observe(this) { errorMessage ->
            errorMessage?.let { showError(errorMessage) }
        }
    }

    private fun showError(errorMessage: String) {
        binding.frmErrorContainer.tvErrorValue.text = errorMessage
        binding.frmErrorContainer.root.makeItVisible()

    }

    private fun updateUI(stepItem: StepItem) {
        adapter.addChatData(stepItem)
        if (stepItem.action == ChatViewType.END_CONVERSATION.typeName) {
            adapter.addChatData(JsonHelper.loadStepListFromJsonRaw(this).last())
        }
        binding.rvChat.scrollToBottom(adapter.itemCount)
    }

    private fun handleAction(action: String, selectedButton: String) {
        if (action == ChatViewType.END_CONVERSATION.typeName) {
            showEndConversationDialog()
        } else {
            // set, selected button to chat
            adapter.addChatData(
                newChatData = StepItem(
                    action = null,
                    step = null,
                    type = ChatViewType.USER_TEXT.typeName,
                    content = ContentResponse.TextContent(text = selectedButton)
                )
            )
            viewModel.getNextStepFromWebSocket(action)// or ROOM
        }
    }

    private fun showEndConversationDialog() {
        binding.frmChatEndContainer.root.makeItVisible()
    }

    private fun closeConnection() {
        viewModel.closeConnection()
        finish()
    }

}