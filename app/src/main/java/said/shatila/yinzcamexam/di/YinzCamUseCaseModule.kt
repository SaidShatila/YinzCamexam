package said.shatila.yinzcamexam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import said.shatila.yinzcamexam.domain.IODispatcher
import said.shatila.yinzcamexam.domain.repository.YinzCamRepository
import said.shatila.yinzcamexam.domain.usecase.FetchYinzCamUseCase
import said.shatila.yinzcamexam.domain.usecase.YinzCamUiBuild
import said.shatila.yinzcamexam.domain.usecase.YinzCamUseCases

@Module
@InstallIn(ViewModelComponent::class)
object YinzCamUseCaseModule {
    @Provides
    fun provideYinzCamUseCase(
        yinzCamRepository: YinzCamRepository,
        @IODispatcher ioDispatcher: CoroutineDispatcher
    ): YinzCamUseCases {
        return YinzCamUseCases(
            fetchYinzCamUseCase = FetchYinzCamUseCase(
                yinzCamRepository, ioDispatcher
            ),
            yinzCamUiBuild = YinzCamUiBuild()
        )
    }
}