package said.shatila.yinzcamexam.utils

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