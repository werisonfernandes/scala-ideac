package lectures.functional.programming

import scala.util.{Failure, Random, Success, Try}

object HandingFailure extends App {
  //Create a success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super Failure!!!"))
  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("No string for you buster")
  //Try objects via the apply method
  val potentialFailure = Try(unsafeMethod)
  println(potentialFailure)

  //Syntax sugar
  val anotherPotentialFailure = Try {
    //Code the might be throw
  }
  //Utilities
  println(potentialFailure.isSuccess)

  //orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod))
  println(fallbackTry)

  //If you design the API
  def betterUnsafeMethod: Try[String] = Failure(new RuntimeException)
  def betterBackupMethod: Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod orElse betterBackupMethod
  println(betterFallback)

  //map, filter, flatMap
  println(aSuccess.map(_*2))
  println(aSuccess.flatMap(x => Success(x*10)))
  println(aSuccess.filter(_ > 10))
  //for-comprehension
  /*
    Exercise.
   */
  val hostname= "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }
    def getSafe(url: String): Try[String] = {
      Try(get(url))
    }
  }

  object HttpService {
    val random = new Random(System.nanoTime())
    def getConnection(host: String, port: String): Connection = {
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }
    def getSafeConnection(host: String, port: String): Try[Connection] = {
      Try(getConnection(host, port))
    }
  }
  //If you get the html page from the connection, print it in the console i.e call renderHTML
  val possibleConnection = HttpService.getSafeConnection(hostname, port)
  val possibleHTML = possibleConnection.flatMap(conn => conn.getSafe("/home"))
  possibleHTML.foreach(renderHTML)

  //shorthand version
  HttpService.getSafeConnection(hostname, port)
    .flatMap(conn => conn.getSafe("/home"))
    .foreach(renderHTML)
  //for comprehension version
  for {
    connection <- HttpService.getSafeConnection(hostname, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)

  //imperative lang
  /*try {
    val connection = HttpService.getConnection(hostname, port)
    try {
      val page = connection.get("/home")
      renderHTML(page)
    } catch (Exception e) {

    }
  } catch (Exception e) {

  }*/
}

