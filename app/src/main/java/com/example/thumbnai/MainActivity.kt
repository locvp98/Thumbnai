package com.example.thumbnai

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val link: String =
            "https://video.xx.fbcdn.net/v/t42.9040-2/10000000_276425469834291_2031109164062736384_n.mp4?_nc_cat=1&efg=eyJ2ZW5jb2RlX3RhZyI6InN2ZV9zZCJ9&_nc_oc=AQmYUIH0dpa7oIxqoDJnEUvncgsWajopzb2ZLErnOQJ7lUgWA8atLc8cIkhaPLDHFtY&_nc_ht=video-sea1-1.xx&oh=fb581525a3db32526e371e3302b174c4&oe=5D2DFCD9"
       val bitmap= loadBitmapFromVideo(link)
        imgBitmap.setImageBitmap(bitmap)

    }

    @Throws(Throwable::class)
    fun loadBitmapFromVideo(videoPath: String): Bitmap? {
        var bitmap: Bitmap? = null
        var mediaMetadataRetriever: MediaMetadataRetriever? = null
        try {
            mediaMetadataRetriever = MediaMetadataRetriever()
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, HashMap())
            else
                mediaMetadataRetriever.setDataSource(videoPath)
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.frameAtTime
        } catch (e: Exception) {
            e.printStackTrace()
            throw Throwable("Exception in loadBitmapFromVideo(String videoPath)" + e.message)

        } finally {
            mediaMetadataRetriever?.release()
        }
        return bitmap
    }
}
