package com.example.knotsncrosses.dialogs

interface GameDialogListener {

    fun onDialogCreateGame(player:String)
    fun onDialogJoinGame(player:String, gameId:String)

}