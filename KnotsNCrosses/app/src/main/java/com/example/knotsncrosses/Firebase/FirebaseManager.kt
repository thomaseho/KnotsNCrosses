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

        val userRef = Firebase.storage.reference.child("users/${uniqueId}.json")
        val ONE_MEGABYTE: Long = 1024 * 1024

        userRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {

            val user = String(it)

            val result = Klaxon().parse<KNCUser>(user)

            if (result != null){

                GameManager.currentGames = mutableListOf()
                GameManager.player = result.userName

            } else {

                GameManager.loadGames()

            }

        }.addOnFailureListener{

            GameManager.loadGames()

        }

    }


    fun saveUserData() {

        val path  = filePath
        val fileName = "${uniqueId}.json"
        val file = File(path, fileName)

        if (file.exists() && file.isFile){

            file.delete()

        }

        file.createNewFile()

        val content: String = "{\n" +
                "\"userName\": \"${GameManager.player}\"\n" +
                "}"


        FileOutputStream(file, true).bufferedWriter().use { writer ->

            writer.write(content)

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