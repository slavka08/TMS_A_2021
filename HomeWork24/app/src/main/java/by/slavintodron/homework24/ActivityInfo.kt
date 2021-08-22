package by.slavintodron.homework24

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import by.slavintodron.homework24.databinding.ActivityInfoBinding
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout

class ActivityInfo : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    private val viewModel: ItemsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getIntExtra(posterExtraName, -1)
        if (id >= 0) {
           val data = viewModel.getItem(id)
        }

    }


    companion object {
        const val posterExtraName = "posterExtraId"
        fun startActivity(
            context: Context,
            transformationLayout: TransformationLayout,
            id: Int
        ) {
            val intent = Intent(context, ActivityInfo::class.java)
            intent.putExtra(posterExtraName, id)
            TransformationCompat.startActivity(transformationLayout, intent)
        }
    }
}