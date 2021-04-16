package exercises

object MyListGenerics extends App {

  abstract class MyList[+A] extends {
    /*
      1. head. First element of the list
      2. tail. remainder of the list
      3. isEmpty. is this list is empty
      4. add(int). return a new list with this element added
      5. toString. a string representation of the list
     */
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyList[B]
    def printElements: String
    //polymorphic call
    override def toString: String = s"[$printElements]"
  }

  object Empty extends MyList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException
    override def tail: MyList[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
    override def printElements: String = ""
  }

  class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    override def head: A = h
    override def tail: MyList[A] = t
    override def isEmpty: Boolean = false
    override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
    override def printElements: String = {
      println(s"Printing tail => ${t}")
      if(t.isEmpty) h + ""
      else {
        s"$h ${t.printElements}"
      }
    }
  }

  //Testing generic classes
  var listOfIntegers: MyList[Int] = Empty
  var listOfStrings: MyList[String] = Empty

  println(listOfIntegers)
  println(listOfStrings)

  listOfIntegers = listOfIntegers.add(10)
  listOfStrings = listOfStrings.add("Werison")

  println(listOfIntegers)
  println(listOfStrings)
}
