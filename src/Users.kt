//Oheneba Poku-Marboah 1706214

//Singleton with methods for adding and removing users and checking if a username is unique or not
object User {
     val allUsers = hashSetOf<String>()
    fun addUser(theAddedUsersName:String): Boolean{
        if(!isUsernameUnique(theAddedUsersName)){
            allUsers.add(theAddedUsersName)
            println("user name $theAddedUsersName added. There are ${allUsers.size}")
            return true

        }else{
            println("user name not unique and not added. There are still ${allUsers.size}")
            println(User.allUsers)
            return false
        }
    }
    fun removeUser(theRemovedUsersName: String){
        allUsers.remove(theRemovedUsersName)
        println("user $theRemovedUsersName removed. There are ${allUsers.size}")
    }
    fun isUsernameUnique(theUsersName: String):Boolean{
        if (allUsers.contains(theUsersName)){
            println("contains name already")
            return true
        }else{
            return false
        }
    }
    override fun toString(): String {
        var text =""
        for (i in allUsers){
            text += "$i \n"
        }
        return text
    }
}
