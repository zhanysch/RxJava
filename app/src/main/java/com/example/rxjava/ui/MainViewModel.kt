package com.example.rxjava.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjava.data.RetrofitInterface
import com.example.rxjava.data.model.SearchModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class MainViewModel(private val network : RetrofitInterface) : ViewModel() {

    private val progress = MutableLiveData<Boolean>()

    private var disposable : Disposable? = null  // disposable хранилище в котором хранит один поток 1 вариант
    private var compositDisposable = CompositeDisposable()    // для больших потоков 2 вариант

    private val filmBehaviorSujeckt = BehaviorSubject.create<String>()   // для получения самых последних данных с интернета
    val data = MutableLiveData<SearchModel>()

    init {
        compositDisposable.add(  // метод асинхронн запроса
            filmBehaviorSujeckt
                .debounce(400,TimeUnit.MILLISECONDS)   // для задержки  поиска
                .subscribe({
                    searchFilm(it)
                },{
                    Log.d("gsdgdsgs","gsgdsgsdg")  // при ошибке
                })
        )
    }

    fun search(text : String){
         filmBehaviorSujeckt.onNext(text)
    }

    private fun searchFilm(text : String){
//      disposable =
        compositDisposable.add(network.searchFilm("90b2f7c2",text)
           .subscribeOn(Schedulers.io())  // поток выполнен запроса
           .observeOn(AndroidSchedulers.mainThread())  // отдает данные в главном потоке
           .doOnSubscribe{
               progress.postValue(true)
           }
           .doFinally{    //момент когда запрос закончил свою работу
               progress.postValue(false)
           }
           .subscribe({
               data.postValue(it)
              Log.d("___afafafs","ssdgsdsdgsd")                // если все произошло успешно
           },{
               Log.d("___afafafs",it.localizedMessage)                // если все отвалилось
           })
        )
    }

    override fun onCleared() {  // метод при уничтожении ViewModel все уничтажалось
        super.onCleared()
        disposable?.dispose()   // чтоб запросы очищались, и не было утечки памяти 1 вариант для мелких потоков
        compositDisposable.dispose()  // 2 вариант для больших потокв
    }
}