/**
  * Created by goutham on 11/05/17.
  */

import java.io._
import java.net.{DatagramPacket, DatagramSocket}


abstract sealed class MsgType{
  def main(args: Array[String]): Unit = {
    try {
      //1. creating a server socket, parameter is local port number
      val sock = new DatagramSocket(7777)
      //buffer to receive incoming data
      val buffer = new Array[Byte](65536)
      val incoming = new DatagramPacket(buffer, buffer.length)
      //2. Wait for an incoming data
      echo("Server socket created. Waiting for incoming data...")
      //communication loop
      while({true}){
        sock.receive(incoming)
        val data = incoming.getData
        var Msg = new String(data, 0, incoming.getLength)
        //echo the details of incoming data - client ip : client port - client message
        echo(incoming.getAddress.getHostAddress + " : " + incoming.getPort + " - " + Msg)
        Msg = (Msg + ":echoed")
        val dp = new DatagramPacket(Msg.getBytes, Msg.getBytes.length, incoming.getAddress, incoming.getPort)
        sock.send(dp)
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