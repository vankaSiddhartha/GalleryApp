package com.techvanka.galleryapp

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    var imageList = arrayListOf<String>()
    var videoList = arrayListOf<String>()
    var list = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrieveImages()
        retrieveVideos(this)
        var rv = findViewById<RecyclerView>(R.id.rv)

        if (imageList.size<videoList.size){
            for (i in 0..videoList.size){
                if (i<imageList.size){
                    list.add(imageList[i])
                    list.add(videoList[i])
                }else{
                    list.add(videoList[i])
                }
            }
        }else{
            for (j in 0..imageList.size){
                if (j<videoList.size){
                    list.add(imageList[j])
                    list.add(videoList[j])
                }else{
                   // list.add(imageList[j])
                }
            }
        }
        rv.layoutManager = GridLayoutManager(this,3)
        rv.adapter = Adapter(this, list)

    }

    private fun retrieveImages() {

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA
        )

        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val cursor = contentResolver.query(uri, projection, null, null, null)

        if (cursor != null && cursor.moveToFirst()) {
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
            val pathColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA)

            do {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val path = cursor.getString(pathColumn)
                val imageItem = DataClass(id, name, path)
                imageList.add(imageItem.path)
               // list.add(imageItem.path)
             //   Toast.makeText(this, "$imageList", Toast.LENGTH_SHORT).show()

            } while (cursor.moveToNext())
         //   Toast.makeText(this, "$imageList", Toast.LENGTH_SHORT).show()

        }
        //Toast.makeText(this, "$imageList", Toast.LENGTH_SHORT).show()

      //  Toast.makeText(this, "$imageList", Toast.LENGTH_SHORT).show()

    }
    private fun retrieveVideos(context: Context) {


        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATA
        )

        val selection = "${MediaStore.Video.Media.DATA} like?"
        val selectionArgs = arrayOf("%${Environment.DIRECTORY_DCIM}%")

        val sortOrder = "${MediaStore.Video.Media.DATE_ADDED} DESC"

        val query = context.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )

        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val pathColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val path = cursor.getString(pathColumn)

                val videoItem = DataClass(id, name, path)
                videoList.add(videoItem.path)
               // list.add(videoItem.path)
            }




            cursor.close()

        }


    }

}

