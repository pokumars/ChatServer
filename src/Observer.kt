//Oheneba Poku-Marboah 1706214

//Command interpreter inherits from this so it can observe  the chat history
interface ChatHistoryObserver {
    fun newMessage(message: ChatMessage)
}