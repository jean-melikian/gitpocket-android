package fr.ozoneprojects.gitpocket.ui.user

import android.arch.lifecycle.MutableLiveData
import android.view.View
import fr.ozoneprojects.gitpocket.base.BaseViewModel
import fr.ozoneprojects.gitpocket.network.UserApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserProfileViewModel : BaseViewModel() {
    @Inject
    lateinit var userApi: UserApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        loadUser()
    }

    private fun loadUser() {
        subscription = userApi.getUserByName("ozonePowered")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveUserStart() }
            .doOnTerminate { onRetrieveUserFinish() }
            .subscribe(
                { onRetrieveUserSuccess() },
                { onRetrieveUserError() }
            )
    }

    private fun onRetrieveUserStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveUserFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveUserSuccess() {

    }

    private fun onRetrieveUserError() {

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}