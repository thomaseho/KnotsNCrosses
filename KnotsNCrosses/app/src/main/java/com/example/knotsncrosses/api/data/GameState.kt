package com.example.knotsncrosses.api.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

typealias GameState = MutableList<List<Int>>

@Parcelize
data class Game(var players:MutableList<String>, val gameId:String, var state:GameState):Parcelable