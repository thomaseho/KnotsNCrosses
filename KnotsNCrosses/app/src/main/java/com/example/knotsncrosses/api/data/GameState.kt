package com.example.knotsncrosses.api.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

typealias GameState = MutableList<MutableList<Int>>

@Parcelize
data class Game(var players:MutableList<String>, val gameId:String, var state: MutableList<MutableList<Int>>):Parcelable