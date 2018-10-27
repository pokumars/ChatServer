import java.net.ServerSocket
//Oheneba Poku-Marboah 1706214

//This acts as the server
fun main(args: Array<String>) {
    val serverXSocket = ServerSocket(30001)

    println("We are in port " + serverXSocket.localPort)

    while (true) {
        println("new connection" + serverXSocket.inetAddress.hostAddress + " " + serverXSocket.localPort)
        val s = serverXSocket.accept()
        val t = Thread(CommandInterpreter(s.getInputStream(),s.getOutputStream()))
                t.start()
    }
}
