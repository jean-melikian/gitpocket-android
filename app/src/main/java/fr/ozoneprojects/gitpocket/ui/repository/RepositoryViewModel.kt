package fr.ozoneprojects.gitpocket.ui.repository

import android.arch.lifecycle.MutableLiveData
import fr.ozoneprojects.gitpocket.base.BaseViewModel
import fr.ozoneprojects.gitpocket.model.Repository

class RepositoryViewModel : BaseViewModel() {
    private val name = MutableLiveData<String>()
    private val mainLanguage = MutableLiveData<String>()

    fun bind(repository: Repository) {
        name.value = repository.name
        mainLanguage.value = repository.language
    }

    fun getName(): MutableLiveData<String> {
        return name
    }

    fun getMainLanguage(): MutableLiveData<String> {
        return mainLanguage
    }
}