package com.app.empdatabase.core.utils

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

internal interface FlowCollector<T> {
    /**
     * Will help to finish the Collector
     *
     * @return [FlowCollector]
     */
    fun finish(): FlowCollector<T>

    /**
     * Help to assert the collected values.
     *
     * @param values - collection of values that need to be assrted
     *
     * @return [FlowCollector]
     */
    fun assertValues(vararg values: T): FlowCollector<T>

    fun assertSize(size: Int): FlowCollector<T>
}

private class FlowCollectorImpl<T>(
    scope: CoroutineScope,
    flow: Flow<T>,
) : FlowCollector<T> {
    private val listOfCollectedItems = ArrayList<T>()
    private val job =
        scope.launch {
            flow.collect {
                listOfCollectedItems.add(it)
            }
        }

    override fun finish(): FlowCollector<T> {
        job.cancel()

        return this
    }

    override fun assertValues(vararg values: T): FlowCollector<T> {
        assertThat(listOfCollectedItems).containsExactly(values)
        return this
    }

    override fun assertSize(size: Int): FlowCollector<T> {
        assertThat(listOfCollectedItems.size).isEqualTo(size)
        return this
    }
}

internal fun <T> Flow<T>.test(scope: CoroutineScope): FlowCollector<T> {
    return FlowCollectorImpl(
        scope,
        this,
    )
}
