//Oheneba Poku-Marboah 1706214

//keeps the log of all previously entered messages
//Gives the command interpreters the ability to view the messages by registering them to a list of observers and
// notifying those observers when there is a new message.

object ChatHistory: ChatHistoryObservable{
    val listOfObservers = mutableSetOf<ChatHistoryObserver>()
    val myOldMessages = mutableListOf<ChatMessage>()

    override fun register(observers:ChatHistoryObserver) {
        listOfObservers.add(observers)
        println("register")
    }

    override fun deregister(observers:ChatHistoryObserver) {
        listOfObservers.remove(observers)
    }

    override fun notifyObserver(message: ChatMessage) {
        for (i in listOfObservers){
            i.newMessage(message)
            //works
        }
    }
    override fun toString(): String {
        var text =""
        for (i in myOldMessages){
            text += "${i.toString()}\n"
        }
        return text
    }
    fun insert(message: ChatMessage){
        myOldMessages.add(message)
        notifyObserver(message)
    }

}
