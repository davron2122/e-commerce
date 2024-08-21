package com.example.e_commerce.util

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {
    companion object {
        private const val TAG = "SingleEvent"
    }

    val mPending: AtomicBoolean = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (!hasActiveObservers()) {

            //Observe Internal Mutable Live Data
            super.observe(owner) {
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(it)
                }
            }
        }

    }

    @MainThread
    override fun setValue(value: T?) {
        mPending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        value = null

    }


}
