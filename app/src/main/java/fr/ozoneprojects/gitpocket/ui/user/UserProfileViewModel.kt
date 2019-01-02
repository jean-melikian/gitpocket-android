package fr.ozoneprojects.gitpocket.ui.user

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import fr.ozoneprojects.gitpocket.R
import fr.ozoneprojects.gitpocket.base.BaseViewModel
import fr.ozoneprojects.gitpocket.model.Repository
import fr.ozoneprojects.gitpocket.model.User
import fr.ozoneprojects.gitpocket.network.UsersApi
import fr.ozoneprojects.gitpocket.ui.repository.RepositoryListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserProfileViewModel : BaseViewModel() {
    @Inject
    lateinit var usersApi: UsersApi

    private lateinit var userSubscription: Disposable
    private lateinit var repoListSubscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadUser(userNickname) }

    val repositoryListAdapter: RepositoryListAdapter = RepositoryListAdapter()

    val userNickname: String = "ozonePowered"

    private val user = MutableLiveData<User>()
    private val userLogin = MutableLiveData<String>()
    private val userPicture = MutableLiveData<String>()

    init {
        loadUser(userNickname)
    }

    private fun loadUser(nickname: String) {
        userSubscription = usersApi.getUserByName(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveUserStart() }
            .doOnTerminate { onRetrieveUserFinish() }
            .subscribe(
                { result -> onRetrieveUserSuccess(result, nickname) },
                { t -> onRetrieveUserError(t) }
            )
    }

    private fun loadRepositories(nickname: String) {
        repoListSubscription = usersApi.getUserRepositories(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveRepoListStart() }
            .doOnTerminate { onRetrieveRepoListFinish() }
            .subscribe(
                { result: List<Repository> -> onRetrieveRepoListSuccess(result) },
                { t -> onRetrieveRepoListError(t) }
            )
    }

    fun getUser(): MutableLiveData<User> {
        return user
    }

    fun getUserName(): MutableLiveData<String> {
        return userLogin
    }

    private fun onRetrieveUserStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveUserFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveUserSuccess(result: User, nickname: String) {
        this.user.value = result
        bind(this.user.value)
        Log.d("Fetched", "$result")
        loadRepositories(nickname)
    }

    private fun bind(value: User?) {
        userLogin.value = value?.login
        userPicture.value = value?.login
    }

    private fun onRetrieveUserError(t: Throwable) {
        t.printStackTrace()
        errorMessage.value = R.string.fetching_user_error
    }

    private fun onRetrieveRepoListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveRepoListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveRepoListSuccess(result: List<Repository>) {
        this.repositoryListAdapter.updateRepositoryList(result)
        Log.d("Fetched", "$result")
    }

    private fun onRetrieveRepoListError(t: Throwable) {
        t.printStackTrace()
        errorMessage.value = R.string.fetching_repos_error
    }

    override fun onCleared() {
        super.onCleared()
        userSubscription.dispose()
        repoListSubscription.dispose()
    }
}