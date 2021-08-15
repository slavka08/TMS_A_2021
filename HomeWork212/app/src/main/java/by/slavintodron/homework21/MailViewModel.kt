package by.slavintodron.homework21

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.slavintodron.homework21.mail.MailBuilder
import by.slavintodron.homework21.mail.MailEntity

class MailViewModel(): ViewModel() {
 var mails: MutableLiveData<Array<MailEntity>> =  MutableLiveData<Array<MailEntity>>()

    init {
        createRandomMails()
    }
    private fun createRandomMails(){
        mails.postValue(MailBuilder().make())
    }
    fun getMail(id:Int): MailEntity? {
      return mails.value?.get(id)
    }

}

