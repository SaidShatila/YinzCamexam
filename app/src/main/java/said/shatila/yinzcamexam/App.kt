package said.shatila.yinzcamexam

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import coil3.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), SingletonImageLoader.Factory {
    override fun newImageLoader(context: PlatformContext): ImageLoader {
        val imageRequest =
            ImageRequest.Builder(context).placeholder(R.drawable.baseline_sports_football_24)
                .build()
                .placeholder()
        return ImageLoader(this).newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder().maxSizePercent(context, 0.1).build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache")).maxSizePercent(0.1).build()
            }
            .placeholder(imageRequest)
            .error(imageRequest)
            .logger(DebugLogger())
            .crossfade(true)
            .build()
    }

}
