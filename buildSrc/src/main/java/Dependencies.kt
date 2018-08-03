import Versions.lifecycle_version
import Versions.room_version

object Versions {
    const val support_lib = "27.1.1"
    const val retrofit = "2.3.0"
    const val rxjava = "2.1.9"
    const val room_version = "1.1.1"
    const val lifecycle_version = "1.1.1"

}

object Libs {
    val support_annotations = "com.android.support:support-annotations:${Versions.support_lib}"
    val support_appcompat_v7 = "com.android.support:appcompat-v7:${Versions.support_lib}"
    val recyclerview = "com.android.support:recyclerview-v7:${Versions.support_lib}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"

    val design_support = "com.android.support:design:${Versions.support_lib}"
    val room_imp = "android.arch.persistence.room:runtime:$room_version"
    val room_annotation_processor = "android.arch.persistence.room:compiler:$room_version"

    val livedata = "android.arch.lifecycle:livedata:${lifecycle_version}"


}