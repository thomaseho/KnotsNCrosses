package com.example.knotsncrosses.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.knotsncrosses.GameManager
import com.example.knotsncrosses.MainActivity
import com.example.knotsncrosses.databinding.DialogCreateGameBinding
import java.lang.ClassCastException
import java.lang.IllegalStateException

class CreateGameDialog(): DialogFragment() {

    internal lateinit var listener:GameDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder: AlertDialog.Builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val binding = DialogCreateGameBinding.inflate(inflater)

            builder.apply {
                setTitle("What is your name gamer?")
                setPositiveButton("Create") { dialog, which ->
                    if (binding.username.text.toString() != ""){
                        GameManager.player = binding.username.text.toString()
                    }
                }

                setNegativeButton("Cancel") {dialog, which ->
                    dialog.cancel()
                }

                setView(binding.root)
            }

            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as GameDialogListener
        } catch (e: ClassCastException){

            throw ClassCastException(("$context must implement GameDialogListener"))

        }
    }

}