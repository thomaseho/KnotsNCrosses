package com.example.knotsncrosses.Firebase

import android.net.Uri
import android.util.JsonReader
import android.util.Log
import androidx.core.net.toUri
import com.beust.klaxon.Klaxon
import com.example.knotsncrosses.GameManager
import com.example.knotsncrosses.api.data.Game
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader

class FirebaseManager {

    private lateinit var uniqueId: String
    private lateinit var filePath: File

    val TAG: String = "KnotsNCrosses: FirebaseManager"

    fun loadFirebase() {

        val file = File(filePath, uniqueId.plus("-Games.json")).toUri()

        val ref = FirebaseStorage.getInstance().reference.child("users/${file.lastPathSegment}")
        val downloadUserGames = ref.getFile(file)

        downloadUserGames.addOnSuccessListener {

            if(it != null){

                val jsonData = Gson().fromJson(it.toString(), KNCUser::class.java)

                if (jsonData != null){

                    GameManager.currentGames = jsonData.currentGames
                    GameManager.player = jsonData.userName

                } else {

                    GameManager.loadGames()

                }

                GameManager.updateCurrentGames()

            }

        }

    }

    fun saveUserData() {

        val path  = filePath
        val fileName = "${uniqueId}-Games.json"
        val file = File(path, fileName)

        if (file.exists() && file.isFile){

            file.delete()

        }

        file.createNewFile()


            GameManager.player = GameManager.currentGames[0].players[0]

            var content: String = "{\n"

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
            content += "}\n"

            val jsonContent = Gson().toJson(content)

            FileOutputStream(file, true).bufferedWriter().use { writer ->

                writer.write(jsonContent)

            }

            upload(file.toUri())


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


    fun setUniqueId(deviceID: String, path: File?){

        uniqueId = deviceID

        if (path != null) {
            filePath = path
        }

    }

    companion object {

        val instance = FirebaseManager()

    }

}