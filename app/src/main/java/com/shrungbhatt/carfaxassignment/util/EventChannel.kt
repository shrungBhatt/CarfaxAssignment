package com.shrungbhatt.carfaxassignment.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class EventChannel: IEventChannel {

    private val channel = Channel<Event>()

    override val eventFlow: Flow<Event> = channel.receiveAsFlow()

    override fun emitEvent(type: EventType, message: String) {
        CoroutineScope(Default).launch {
            channel.send(Event(type, message))
        }
    }
}

interface IEventChannel{
    val eventFlow: Flow<Event>

    fun emitEvent(type: EventType, message: String)
}

data class Event(
    val type: EventType,
    val message: String
)

enum class EventType{
    ERROR,
    INVALID_DATA
}