package by.slavintodron.homework21.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import by.slavintodron.homework21.databinding.ActivityMainBinding
import by.slavintodron.homework21.services.ACTION_PLAY
import by.slavintodron.homework21.services.PlayerService
import java.io.File


const val OPEN_MP3_DIALOG = 127
const val FILE_URI = "audioFileUri"
const val MUSIC_IS_PLAYING = "playingmusic"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonOpenFile.setOnClickListener {
            openFileDialog()
        }
        binding.buttonraw.setOnClickListener {

            File.createTempFile("Over_the_Horizon.mp3", null, cacheDir)
            val cacheFile = File(cacheDir, "Over_the_Horizon.mp3")
            application.assets.open("Over_the_Horizon.mp3").use {
                cacheFile.writeBytes(it.readBytes())
            }
            cacheFile.toUri()
            val intent = Intent(applicationContext, PlayerService::class.java)
            intent.putExtra(FILE_URI,  cacheFile.toUri())
            intent.putExtra(MUSIC_IS_PLAYING,true)
            intent.action = ACTION_PLAY
            ContextCompat.startForegroundService(applicationContext, intent)
        }
    }

    private fun openFileDialog() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "audio/*"
        }
        startActivityForResult(intent, OPEN_MP3_DIALOG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                OPEN_MP3_DIALOG -> data?.data?.let {
                    val intent = Intent(applicationContext, PlayerService::class.java)
                    intent.putExtra(FILE_URI, it)
                    intent.putExtra(MUSIC_IS_PLAYING,true)
                    intent.action = ACTION_PLAY
                    ContextCompat.startForegroundService(applicationContext, intent)
                }
            }
        }
    }

}