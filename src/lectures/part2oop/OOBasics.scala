package lectures.part2oop

import scala.annotation.tailrec

object OOBasics extends App {
  val person = new Person("John", 23)
  println(person.x)
  person.greeting("Werison")
  person.greeting()

  //Tests
  val writer = new Writer("Charles", "Dickens", 1812)
  val impostor = new Writer("Charles", "Dickens", 1812)
  val novel: Novel = new Novel("Great Expectations", 1861, writer)
  println(novel.authorAge())
  println(novel.isWrittenBy(writer))
  println(novel.isWrittenBy(impostor))
  println(novel.copy(1869))

  //Counter
  val counter = new Counter
  counter.inc.print
  counter.inc.inc.print
  counter.inc(3).print
}

//Constructor
class Person(name: String, val age: Int) {
  //multiple constructors
  def this(name: String)= this(name, 0)
  def this() = this("John Doe")

  //Body
  //class params are not fields
  val x = 2
  println(1+3)

  //method
  def greeting(): Unit = println(s"Hi, i am $name")
  def greeting(name: String): Unit = println(s"${this.name} says: Hi, $name")
}

/*
  Novel and a Writer class
  Writer: firstName, surname, year
  -Method: fullname()

  Novel: age, yearOfRelease, author
  -Methods: authorAge(), isWrittenBy(author), copy(new year of release) = instance of Novel
 */
class Writer(firstName: String, surname: String, val year: Int) {
  def fullName: String = s"$firstName $surname"
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge(): Int= year-author.year
  def isWrittenBy(author: Writer): Boolean= author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

/*
  Counter class
  -receives an int value
  method current value
  method to increment/decrement => new Counter
  overload inc/dec to receive a new amount => new Counter
 */
class Counter(val count: Int = 0) {
  /*def inc = new Counter(count + 1) //Immutability
  def dec = new Counter(count - 1)
  def inc(n: Int) = new Counter(count + n)
  def dec(n: Int) =  new Counter(count - n)*/

  //Immutability
  def inc = {
    println("Increment")
    new Counter(count + 1)
  }
  def dec = {
    println("Decrement")
    new Counter(count - 1)
  }
  def inc(n: Int): Counter = {
    if(n <= 0) this
    else inc.inc(n-1)
  }
  def dec(n: Int): Counter =  {
    if(n <= 0) this
    else dec.dec(n-1)
  }

  def print = println(count)
}