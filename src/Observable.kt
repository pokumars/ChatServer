//Oheneba Poku-Marboah 1706214

//ChatHistory inherits from this so it can be observed by command interpreters
interface ChatHistoryObservable {
    fun register (observer: ChatHistoryObserver)
    fun deregister(observer: ChatHistoryObserver)
    fun notifyObserver(message: ChatMessage)
}