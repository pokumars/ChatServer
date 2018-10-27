import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.time.LocalDateTime
import java.util.*
//Oheneba Poku-Marboah 1706214

class CommandInterpreter (inputStream: InputStream, outputStream: OutputStream):Runnable,ChatHistoryObserver {
    var scanner =Scanner(inputStream)
    var printer =PrintStream(outputStream)
    var userInput: String= ""
    var username :String = ""

    init{
        ChatHistory.register(this)
    }
    override fun newMessage(message: ChatMessage) {
        printer.println(message.toString())
    }
    override fun run (){
        userInput= scanner.nextLine()

        //First thing, take username and add it to list of users
        takeUsername()

        //continuously runs so that it can wait for input
        while (userInput!=":quit"){
            userInput= scanner.nextLine()
            val currentTime=LocalDateTime.now()

            if (userInput.trim().startsWith(':')){
                if (userInput.contains(":userlist") ||userInput.contains(":messages")){
                    when(userInput.trim()){
                        ":userlist"->{
                            printer.println(User.allUsers)
                            println("wants user list")
                        }
                        ":messages"->{
                            printer.println(ChatHistory)
                            println("executing history")
                        }
                    }
                }else{
                    println("your command is unfamiliar. Try again")
                }
            }else if(!userInput.isBlank()&&!userInput.isEmpty()){
                //all positive conditions have been met. Save this message into messages and pass to others
                //save text and its attributes as myChatMessage, notify other observers and add message to list of messages
                var myChatMessage = ChatMessage(username,userInput,currentTime)
                ChatHistory.insert(myChatMessage)
                println(myChatMessage.toString())
            }
        }
        //do this if we leave the while loop
        printer.close()
        User.removeUser(username)
        ChatHistory.deregister(this)
    }
    //check if username is valid
    private fun usernameValidity (): Boolean{
        try{
            if(userInput.substring(0..4)!= ":user"){
                printer.println("Set a username. use command :user \"my username\" ")
                return false
            }else if (userInput.trim() == ":user"){
                printer.println("Set a username. use command :user \"my username\" ")
                return false
            }else if(userInput.substring(0..4)=="user" && userInput[5]!=' '){
                printer.println("There should be a space between the command and the username \"my username\" ")
                return false
            }else{
                if (!User.isUsernameUnique(username)){
                    username = userInput.substring(6)
                    if(User.addUser(username)){
                         printer.println("user name $username added. There are ${User.allUsers.size}")
                     }else{
                         printer.println("user name not unique and not added. There are still ${User.allUsers.size}")
                     }
                }else{

                    printer.println("User name is not unique. set a Unique username. Something other than these.")
                    printer.println(User.allUsers)
                    return false

                }
                return true
            }
        }catch (e: Exception){
            printer.println("Set a username. use command :user \"my username\" ")
            return false
        }
    }
    //Checks if username is valid and then in that case takes the username
    private fun takeUsername () {
        if (usernameValidity()){
            printer.println("Your username is ${username}")
        }else{
            userInput= scanner.nextLine()
            takeUsername()
        }
    }

}



