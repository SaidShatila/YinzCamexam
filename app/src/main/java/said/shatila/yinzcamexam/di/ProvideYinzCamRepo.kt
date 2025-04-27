package said.shatila.yinzcamexam.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import said.shatila.yinzcamexam.data.repository.YinzCamRepoImpl
import said.shatila.yinzcamexam.data.repository.YinzCamRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProvideYinzCamRepo {

    @Binds
    abstract fun provideYinzCamRepository(
        yinzCamRepoImpl: YinzCamRepoImpl
    ): YinzCamRepository
}