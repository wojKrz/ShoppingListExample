package pl.shoppinglistexample.presentation.main.base.event

open class ConsumableEvent<out T>(private val element: T) {

    var consumed = false
        private set

    internal fun consume(): T? =
        if (consumed) {
            null
        } else {
            consumed = true
            element
        }

}