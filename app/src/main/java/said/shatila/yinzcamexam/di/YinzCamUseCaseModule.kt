package said.shatila.yinzcamexam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import said.shatila.yinzcamexam.domain.IODispatcher
import said.shatila.yinzcamexam.domain.MainDispatcher
import said.shatila.yinzcamexam.domain.repository.YinzCamRepository
import said.shatila.yinzcamexam.domain.usecase.FetchYinzCamUseCase
import said.shatila.yinzcamexam.domain.usecase.YinzCamUiBuild
import said.shatila.yinzcamexam.domain.usecase.YinzCamUseCases
import said.shatila.yinzcamexam.data.extension.toSectionUIModels

@Module
@InstallIn(ViewModelComponent::class)
object YinzCamUseCaseModule {
    @Provides
    fun provideYinzCamUseCase(
        yinzCamRepository: YinzCamRepository,
        @IODispatcher ioDispatcher: CoroutineDispatcher,
        @MainDispatcher mainDispatcher: CoroutineDispatcher
    ): YinzCamUseCases {
        return YinzCamUseCases(
            fetchYinzCamUseCase = FetchYinzCamUseCase(
                yinzCamRepository, ioDispatcher
            ),
            yinzCamUiBuild = YinzCamUiBuild { scheduleDTO ->
                with(mainDispatcher) {
                    scheduleDTO.toSectionUIModels()
                }
            }
        )
    }
}