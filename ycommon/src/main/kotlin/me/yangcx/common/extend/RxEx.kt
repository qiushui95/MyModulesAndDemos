package me.yangcx.common.extend

import androidx.annotation.CheckResult
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun getIoScheduler() = Schedulers.io()
fun getUiScheduler() = AndroidSchedulers.mainThread()

@CheckResult
inline fun <reified T> Observable<T>.subscribeOnIo(): Observable<T> {
    return subscribeOn(getIoScheduler())
}

@CheckResult
inline fun <reified T> Flowable<T>.subscribeOnIo(): Flowable<T> {
    return subscribeOn(getIoScheduler())
}

@CheckResult
inline fun <reified T> Maybe<T>.subscribeOnIo(): Maybe<T> {
    return subscribeOn(getIoScheduler())
}

@CheckResult
inline fun <reified T> Single<T>.subscribeOnIo(): Single<T> {
    return subscribeOn(getIoScheduler())
}

@CheckResult
fun Completable.subscribeOnIo(): Completable {
    return subscribeOn(Schedulers.io())
}

@CheckResult
inline fun <reified T> Observable<T>.subscribeOnUi(): Observable<T> {
    return subscribeOn(getUiScheduler())
}

@CheckResult
inline fun <reified T> Flowable<T>.subscribeOnUi(): Flowable<T> {
    return subscribeOn(getUiScheduler())
}

@CheckResult
inline fun <reified T> Maybe<T>.subscribeOnUi(): Maybe<T> {
    return subscribeOn(getUiScheduler())
}

@CheckResult
inline fun <reified T> Single<T>.subscribeOnUi(): Single<T> {
    return subscribeOn(getUiScheduler())
}

@CheckResult
fun Completable.subscribeOnUi(): Completable {
    return subscribeOn(getUiScheduler())
}

@CheckResult
inline fun <reified T> Observable<T>.observeOnIo(): Observable<T> {
    return observeOn(getIoScheduler())
}

@CheckResult
inline fun <reified T> Flowable<T>.observeOnIo(): Flowable<T> {
    return observeOn(getIoScheduler())
}

@CheckResult
inline fun <reified T> Maybe<T>.observeOnIo(): Maybe<T> {
    return observeOn(getIoScheduler())
}

@CheckResult
inline fun <reified T> Single<T>.observeOnIo(): Single<T> {
    return observeOn(getIoScheduler())
}

@CheckResult
fun Completable.observeOnIo(): Completable {
    return observeOn(getIoScheduler())
}

@CheckResult
inline fun <reified T> Observable<T>.observeOnUi(): Observable<T> {
    return observeOn(getUiScheduler())
}

@CheckResult
inline fun <reified T> Flowable<T>.observeOnUi(): Flowable<T> {
    return observeOn(getUiScheduler())
}

@CheckResult
inline fun <reified T> Maybe<T>.observeOnUi(): Maybe<T> {
    return observeOn(getUiScheduler())
}

@CheckResult
inline fun <reified T> Single<T>.observeOnUi(): Single<T> {
    return observeOn(getUiScheduler())
}

@CheckResult
fun Completable.observeOnUi(): Completable {
    return observeOn(getUiScheduler())
}

@CheckResult
inline fun <reified T> Observable<T>.subscribeOnIoObserveOnUi(): Observable<T> {
    return subscribeOnIo().observeOnUi()
}

@CheckResult
inline fun <reified T> Flowable<T>.subscribeOnIoObserveOnUi(): Flowable<T> {
    return subscribeOnIo().observeOnUi()
}

@CheckResult
inline fun <reified T> Maybe<T>.subscribeOnIoObserveOnUi(): Maybe<T> {
    return subscribeOnIo().observeOnUi()
}

@CheckResult
inline fun <reified T> Single<T>.subscribeOnIoObserveOnUi(): Single<T> {
    return subscribeOnIo().observeOnUi()
}

@CheckResult
inline fun <reified T> Completable.subscribeOnIoObserveOnUi(): Completable {
    return subscribeOnIo().observeOnUi()
}