package by.slavintodron.homework21.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.provider.OpenableColumns
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import by.slavintodron.homework21.R
import by.slavintodron.homework21.activity.FILE_URI
import by.slavintodron.homework21.activity.MUSIC_IS_PLAYING
import kotlinx.coroutines.*
import java.sql.Timestamp
import java.text.SimpleDateFormat

const val REQ_CODE_ACTION_RESUME = 33
const val REQ_CODE_ACTION_PAUSE = 44
const val REQ_CODE_ACTION_STOP = 55
const val ACTION_RESUME: String = "by.action.RESUME"
const val ACTION_PLAY: String = "by.action.PLAY"
const val ACTION_STOP: String = "by.action.STOP"
const val ACTION_PAUSE: String = "by.action.PAUSE"
const val NOTIFICATION_ID = 12345
const val NOTIFICATION_CHANNEL_ID = "37525"
const val NOTIFICATION_CHANNEL_NAME = "PlayerService"

const val CHANNEL_ID = "37525"

class PlayerService : Service(), MediaPlayer.OnPreparedListener {
    private var mediaPlayer: MediaPlayer? = null
    private var notificationManager: NotificationManager? = null
    private var job: Job? = null
    private var count = 0
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent != null) {
            val parcelableExtraURI = intent.getParcelableExtra<Uri>(FILE_URI)
            val musicPlaying = intent.getBooleanExtra(MUSIC_IS_PLAYING, false)


            when (intent.action) {
                ACTION_PLAY -> {
                    /*val notification =
                        createNotification(getFileName(parcelableExtraURI), musicPlaying)
                    startForeground(NOTIFICATION_ID, notification)*/


                    if (mediaPlayer == null) {
                        mediaPlayer = MediaPlayer()
                    }
                    mediaPlayer?.apply {
                        setAudioAttributes(
                            AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                        )
                        reset()
                        if (parcelableExtraURI != null) {
                            setDataSource(applicationContext, parcelableExtraURI)
                        }
                        setOnPreparedListener(this@PlayerService)
                        prepareAsync() // prepare async to not block main thread

                    }
                }
                ACTION_STOP -> {
                    mediaPlayer?.stop()
                    job?.cancel("")
                    stopSelf()
                }
                ACTION_PAUSE -> {
                    /*val notification =
                        createNotification(getFileName(parcelableExtraURI), musicPlaying)
                    startForeground(NOTIFICATION_ID, notification)*/
                    mediaPlayer?.pause()
                }
                ACTION_RESUME -> {
                    /* val notification =
                         createNotification(getFileName(parcelableExtraURI), musicPlaying)
                     startForeground(NOTIFICATION_ID, notification)*/
                    mediaPlayer?.start()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun getFileName(uri: Uri?): String {
        var str = ""
        if (uri != null) {
            contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                /*
                 * Get the column indexes of the data in the Cursor,
                 * move to the first row in the Cursor, get the data,
                 * and display it.
                 */
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                str = cursor.getString(nameIndex)
            }
        }
        return str
    }

    private fun createNotification(
        filename: String,
        timer: String,
        musicPlaying: Boolean
    ): Notification {
        val snoozeIntentStop = Intent(this, PlayerService::class.java).apply {
            action = ACTION_STOP
        }
        val snoozePendingIntentStop: PendingIntent =
            PendingIntent.getService(
                this,
                REQ_CODE_ACTION_STOP,
                snoozeIntentStop,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        val snoozeIntentPause = Intent(this, PlayerService::class.java).apply {
            putExtra(MUSIC_IS_PLAYING, false)
            action = ACTION_PAUSE
        }
        val snoozePendingIntentPause: PendingIntent =
            PendingIntent.getService(
                this,
                REQ_CODE_ACTION_PAUSE,
                snoozeIntentPause,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        val snoozeIntentResume = Intent(this, PlayerService::class.java).apply {
            putExtra(MUSIC_IS_PLAYING, true)
            action = ACTION_RESUME
        }
        val snoozePendingIntentResume: PendingIntent =
            PendingIntent.getService(
                this,
                REQ_CODE_ACTION_RESUME,
                snoozeIntentResume,
                PendingIntent.FLAG_UPDATE_CURRENT
            ).apply {

            }

        var channelId = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channelId = createNotificationChannel("my_service", "My Background Service")
        }

        return when (musicPlaying) {
            true -> {
                NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_music_note_black_24dp)
                    .setContentTitle("${getString(R.string.plays)} $filename")
                    .setContentText(timer)

                    .addAction(
                        R.drawable.ic_pause_black_24dp, getString(R.string.pause),
                        snoozePendingIntentPause
                    )
                    .addAction(
                        R.drawable.ic_play_arrow_black_24dp, getString(R.string.stop),
                        snoozePendingIntentStop
                    )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()
            }
            else -> {
                NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_music_note_black_24dp)
                    .setContentTitle("${getString(R.string.plays)} $filename")
                    .setContentText(timer)

                    .addAction(
                        R.drawable.ic_play_arrow_black_24dp, getString(R.string.play),
                        snoozePendingIntentResume
                    )
                    .addAction(
                        R.drawable.ic_play_arrow_black_24dp, getString(R.string.stop),
                        snoozePendingIntentStop
                    )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val channel = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_NONE
        )
        channel.lightColor = Color.BLUE
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
        return channelId
    }

    override fun onPrepared(mp: MediaPlayer?) {
        job = GlobalScope.launch(Dispatchers.Main) {

            while (true) {
                var isPlaying = false
                isPlaying = mp?.isPlaying == true
                val currentTime = mp?.currentPosition?.toLong()?.let { Timestamp(it) }
                val fileDuration = mp?.duration?.toLong()?.let { Timestamp(it) }
                val dateFormat = SimpleDateFormat("mm:ss")

                val time = "${dateFormat.format(currentTime)} / ${dateFormat.format(fileDuration)}"
                val notif = createNotification("file name", time, isPlaying)
                startForeground(NOTIFICATION_ID, notif)
                delay(1000L)
            }
        }
        mp?.start()
    }
}