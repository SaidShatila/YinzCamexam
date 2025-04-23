package said.shatila.yinzcamexam.domain

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IODispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher