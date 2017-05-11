/**
  * Created by goutham on 11/05/17.
  */

import java.io._
import java.net._


object udp_client {
  def main(args: Array[String]): Unit = {
    val port = 7777
    val cin = new BufferedReader(new InputStreamReader(System.in))
    try {
      var sock = new DatagramSocket
      val host = InetAddress.getByName("localhost")
      while({true})
      { //take input and send the packet
        echo("Enter message to send : ")
        var Msg = cin.readLine.asInstanceOf[String]
        val b = Msg.getBytes
        val dp = new DatagramPacket(b, b.length, host, port)
        sock.send(dp)
        //now receive reply
        //buffer to receive incoming data
        val buffer = new Array[Byte](65536)
        val reply = new DatagramPacket(buffer, buffer.length)
        sock.receive(reply)
        val data = reply.getData
        Msg = new String(data, 0, reply.getLength)
        echo(Msg)
      }
    } catch {
      case e: IOException =>
        System.err.println("IOException " + e)
    }
  }

  //simple function to echo data to terminal
  def echo(msg: String): Unit = {
    System.out.println(msg)
  }
}
