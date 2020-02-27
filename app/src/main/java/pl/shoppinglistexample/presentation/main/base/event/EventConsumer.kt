package pl.shoppinglistexample.presentation.main.base.event

import androidx.lifecycle.Observer

open class EventConsumer<T> (private val onConsumeEvent: (T) -> Unit) : Observer<ConsumableEvent<T>> {
    override fun onChanged(t: ConsumableEvent<T>?) {
        t?.consume()?.run(onConsumeEvent)
    }

}