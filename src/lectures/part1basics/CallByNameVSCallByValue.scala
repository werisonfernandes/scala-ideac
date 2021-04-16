package lectures.part1basics

object CallByNameVSCallByValue extends App {

  def calledByValue(n: Long): Unit = {
    println("Called by value => " + n)
    println("Called by value => " + n)
  }

  def calledByName(n: => Long): Unit = {
    println("Called by name => " + n)
    println("Called by name => " + n)
  }
  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(first: Int, second: => Int): Unit = println(first)

  println(infinite())
  printFirst(1, 2)
  //printFirst(infinite(), 2)
  printFirst(2, infinite())
}
