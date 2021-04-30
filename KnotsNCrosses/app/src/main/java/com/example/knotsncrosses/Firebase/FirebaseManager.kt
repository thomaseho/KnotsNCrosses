package com.example.knotsncrosses.Firebase

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.beust.klaxon.Klaxon
import com.example.knotsncrosses.GameManager
import com.example.knotsncrosses.api.data.Game
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.google.gson.Gson
import java.io.File
import java.io.FileOutputStream

class FirebaseManager {

    private lateinit var uniqueId: String

    val TAG: String = "KnotsNCrosses: FirebaseManager"

    fun loadFirebase() {

        val userGamesRef = Firebase.storage.reference.child("users/${uniqueId}-Games.json")
        val ONE_MEGABYTE: Long = 1024 * 1024

        userGamesRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {

            val userGames = String(it)
            val result = Klaxon().parseArray<Game>(userGames)

            GameManager.currentGames = mutableListOf()

            if (result != null){



                result.forEach{

                    GameManager.currentGames.add(it)
                    GameManager.updateCurrentGames()

                }

            } else {

                GameManager.currentGames = mutableListOf()

            }

        }.addOnFailureListener {

            GameManager.currentGames = mutableListOf()

        }

    }

    fun saveUserData(filePath: File?) {

        val path  = filePath
        val fileName = "${uniqueId}-Games.json"
        val file = File(path, fileName)

        if (file.exists() && file.isFile){

            file.delete()

        }

        file.createNewFile()

        if (path != null){

            GameManager.player = GameManager.currentGames[0].players[0]

            var content: String = "[\n"

            GameManager.currentGames.forEach { Game ->
                content += "{"
                content += "\"players\": [ \n"

                Game.players.forEach {

                   content += "\"${it}\", \n"

                }

                content += "], \n\"gameId\": \"${Game.gameId}\", \n" +
                        "\"state\": [\n"

                Game.state.forEach{

                    content += "[ \n"

                    it.forEach {
                        content += "${it}, \n"
                    }

                    content += "], \n"

                    }
                content +=  " ], \n }, \n"


            }
            content += "]\n"

            FileOutputStream(file, true).bufferedWriter().use { writer ->

                writer.write(content)

            }

            upload(file.toUri())

        }


    }

    fun upload(file: Uri) {

        Log.d(TAG, "Upload file $file")

        val ref = FirebaseStorage.getInstance().reference.child("users/${file.lastPathSegment}")
        val uploadTask = ref.putFile(file)

        uploadTask.addOnSuccessListener {

            Log.d(TAG, "Saved changes ${it.toString()}")

        }.addOnFailureListener{

            Log.e(TAG, "Error saving changes to Firebase", it)

        }

    }


    fun setUniqueId(deviceID: String){

        uniqueId = deviceID

    }

    companion object {

        val instance = FirebaseManager()

    }

}