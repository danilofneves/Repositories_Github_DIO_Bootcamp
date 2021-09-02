package com.dio.repositoriesgithub.core.di


import android.app.Application
import com.dio.repositoriesgithub.core.App
import com.dio.repositoriesgithub.data.di.DataModule
import com.dio.repositoriesgithub.domain.di.DomainModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule


import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        MainActivityModuleBuilder::class,
        ContextModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}