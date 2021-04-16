package lectures.functional.programming

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(12)
  val noOption: Option[Int] = None
  println(myFirstOption)
  println(noOption)

  //WORK with Unsafe APIs
  def unsafeMethod(): String = null
  //val result = Some(unsafeMethod()) //Wrong
  //val result2 = Some(null) //Wrong -> never do it
  val result = Option(unsafeMethod()) //Some or None
  println(result)

  //Chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)

  //DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterChainedResult)

  //Functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) //UNSAFE - Do not use this

  //map, flatMap and filter
  println(myFirstOption.map(_*2))
  println(myFirstOption.filter(_>3))
  println(myFirstOption.flatMap(x => Option(x * 2)))

  //for-comprehensions
  /*
    Exercises.
   */
  val config: Map[String, String] = Map(
    //fetched from elsewhere
    "host" -> "192.168.0.1",
    "port" -> "8090"
  )
  class Connection {
    def connect = "Connected" //Connected to some server
  }
  object Connection {
    val random =  new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] = {
      if(random.nextBoolean()) Some(new Connection)
      else None
    }
  }
  //Try to establish a connection, if so, print the connect method
  val host = config.get("host")
  val port = config.get("port")
  /*
    if(p != null)
      if(h != null)
        return Connection.apply(h,p)
    else return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  /*
    if(c != null) return c.connect
    else return null
   */
  val connectionStatus = connection.map(c => c.connect)
  /*
    if(connectionStatus == null) println(None) else println(Some(connectionStatus.get))
   */
  println(connectionStatus)
  /*
    if(status != null) println(status)
   */
  connectionStatus.foreach(println)

  //Chaining calls
  println("*********************************")
  config.get("host")
    .flatMap(h => config.get("port")
      .flatMap(p => Connection(p, h))
        .map(connection => connection.connect))
    .foreach(println)

  //for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}
