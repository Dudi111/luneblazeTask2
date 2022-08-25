package com.example.luneblaze.viewmodel

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.luneblaze.datamodel.Data
import com.example.luneblaze.repo.mainrepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class mainViewModel(val repo: mainrepo): ViewModel() {


    private var mutableLiveData= MutableLiveData<Data>()
    val liveData: LiveData<Data> = mutableLiveData

    fun getapidata(){
        CoroutineScope(Dispatchers.IO).launch {
            val maindata= repo.getapiresp().data
            mutableLiveData.postValue(maindata)

        }
    }

}