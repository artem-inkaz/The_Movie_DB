package ru.androidschool.intensiv.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import ru.androidschool.intensiv.presentation.feed.viewmodel.FeedViewModel
import ru.androidschool.intensiv.presentation.profile.viewmodel.ProfileViewModel
import kotlin.reflect.KClass

//
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

//@Singleton
//class ViewModelFactory @Inject constructor(
//    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
//    ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T =
//        viewModels[modelClass]?.get() as T
//}

@Module
abstract class ViewModelModule {

//    @Binds
//    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun feedViewModel(viewModel: FeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun profileViewModel(viewModel: ProfileViewModel): ViewModel
}
