package com.example.knotsncrosses.Firebase

import android.os.Parcelable
import com.example.knotsncrosses.api.data.Game
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KNCUser(var userName: String, var currentGames: MutableList<Game>):Parcelable