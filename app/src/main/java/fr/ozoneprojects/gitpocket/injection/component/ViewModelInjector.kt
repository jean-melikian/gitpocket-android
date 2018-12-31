package fr.ozoneprojects.gitpocket.injection.component

import dagger.Component
import fr.ozoneprojects.gitpocket.injection.module.NetworkModule
import fr.ozoneprojects.gitpocket.ui.user.UserProfileViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(userProfileViewModel: UserProfileViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}