package fr.ozoneprojects.gitpocket.base

import android.arch.lifecycle.ViewModel
import fr.ozoneprojects.gitpocket.injection.component.DaggerViewModelInjector
import fr.ozoneprojects.gitpocket.injection.component.ViewModelInjector
import fr.ozoneprojects.gitpocket.injection.module.NetworkModule
import fr.ozoneprojects.gitpocket.ui.user.UserProfileViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is UserProfileViewModel -> injector.inject(this)
        }
    }
}