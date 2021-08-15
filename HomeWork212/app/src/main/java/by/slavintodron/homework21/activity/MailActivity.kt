package by.slavintodron.homework21.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.slavintodron.homework21.mail.MailEntity
import by.slavintodron.homework21.fragments.interfaces.MailFragmentsListener
import by.slavintodron.homework21.R
import by.slavintodron.homework21.fragments.MailFragment
import by.slavintodron.homework21.fragments.MailListFragment

class MailActivity : AppCompatActivity(), MailFragmentsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentMailList()
        setFragmentMailRead(null)
        setContentView(R.layout.activity_mail)
    }

    private fun setFragmentMailList(){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_top,MailListFragment())
            commit()
        }
    }

    private fun setFragmentMailRead(id:Int?){
        supportFragmentManager.beginTransaction().apply {
            val fragment = MailFragment.newInstance(id)
            replace(R.id.fragment_bottom,fragment)
            commit()
        }
    }
    override fun openMail(mailID: Int) {
        setFragmentMailRead(mailID)
    }
}