package exercises

import lectures.part2oop.App2

abstract class MyList extends {
  /*
    1. head. First element of the list
    2. tail. remainder of the list
    3. isEmpty. is this list is empty
    4. add(int). return a new list with this element added
    5. toString. a string representation of the list
   */
  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList
  def printElements: String
  //polymorphic call
  override def toString: String = s"[$printElements]"
}

//Linked list
object EmptyList extends MyList {
  override def head: Int = throw new NoSuchElementException
  override def tail: MyList = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add(element: Int): MyList = new Cons(element, EmptyList)
  override def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  override def head: Int = h
  override def tail: MyList = t
  override def isEmpty: Boolean = false
  override def add(element: Int): MyList = new Cons(element, this)
  override def printElements: String = {
    println(s"Printing tail => ${t}")
    if(t.isEmpty) h + ""
    else {
      s"$h ${t.printElements}"
    }
  }
}

object ListTest extends App2 {
  //val list = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, EmptyList)))))
  val list = new Cons(1, new Cons(2,  new Cons(3, new Cons(4, EmptyList))))
  //println(list.head)
  //println(list.tail.head)
  //println(list.tail.tail.tail.tail.head)
  //println(list.add(6).head)
  //polymorphic call
  println(list.toString)
}
