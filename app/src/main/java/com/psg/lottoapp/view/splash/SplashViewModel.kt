package com.psg.lottoapp.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psg.lottoapp.data.model.LottoEntity
import com.psg.lottoapp.data.model.LottoNum
import com.psg.lottoapp.data.repository.AppRepository
import com.psg.lottoapp.view.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(private val repository: AppRepository): BaseViewModel() {
    val lottoEntity: LiveData<LottoEntity> get() = repository.getLottoNum()

    fun updateLotto(lottoEntity: LottoEntity) = CoroutineScope(Dispatchers.IO).launch { repository.updateLotto(lottoEntity) }
    fun insertLotto(lottoEntity: LottoEntity) = CoroutineScope(Dispatchers.IO).launch { repository.insertLotto(lottoEntity) }
    fun deleteLotto() = CoroutineScope(Dispatchers.IO).launch { repository.deleteLotto() }

    fun searchLotto(drwNo:Int): LottoNum {
        var res = LottoNum("","",0,0,0,0,0,0,0,0,0,0,0,0)
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                val body = repository.searchLotto(drwNo).body()
                val code = repository.searchLotto(drwNo).code()

                if (code == 200 && body != null){
                    println("Splash바디는?$body")
                    res = body
                }
            }

        }
        return res
    }

}