package com.omerakkoyun.n11livesupportdemo.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */

const val STEPS_TABLE = "step_table"

@Entity(STEPS_TABLE)
data class StepEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,

    @ColumnInfo(name = "step") val step: String,
    @ColumnInfo(name = "step_content_json") val stepContentJson: String,


){
    constructor() : this(0,"","")
}


