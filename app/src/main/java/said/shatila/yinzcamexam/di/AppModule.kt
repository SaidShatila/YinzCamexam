package said.shatila.yinzcamexam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import said.shatila.yinzcamexam.utils.DefaultDispatcher
import said.shatila.yinzcamexam.utils.IODispatcher
import said.shatila.yinzcamexam.utils.MainDispatcher


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

}