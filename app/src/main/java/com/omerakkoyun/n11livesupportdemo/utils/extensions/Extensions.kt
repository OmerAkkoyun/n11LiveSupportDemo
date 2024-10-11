package com.omerakkoyun.n11livesupportdemo.utils.extensions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.omerakkoyun.n11livesupportdemo.data.local.room.StepEntity
import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import com.omerakkoyun.n11livesupportdemo.utils.helpers.JsonHelper

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */


fun View.makeItVisible(){
    this.visibility = View.VISIBLE
}

fun View.makeItGone(){
    this.visibility = View.GONE
}

fun StepItem.toStepEntity(): StepEntity? {
    return this.content?.let { StepEntity( step = this.step!!, stepContentJson = Gson().toJson(this)) }
}

fun StepEntity.toStepItem(): StepItem {
    return JsonHelper.loadStepItemFromJson(this.stepContentJson)
}

fun RecyclerView.scrollToBottom(itemSize: Int){
    this.scrollToPosition(itemSize - 1)
}