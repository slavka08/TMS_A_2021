package by.tms.homework25room.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import by.tms.homework25room.R

class FilterFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings_sort)
    }

}