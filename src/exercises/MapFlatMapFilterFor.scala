package exercises

import exercises.HFOsAndCurries.{Cons, Empty}

object MapFlatMapFilterFor extends App {
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

    //Higher order functions
    def map[B](transformer: A => B): MyList[B]
    def flatMap[B](transformer: A => MyList[B]): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]
    //Concatenation
    def ++[B >: A](list: MyList[B]): MyList[B]
    //HFOs
    def foreach(f: A => Unit): Unit
    def sort(compare: (A, A) => Int): MyList[A]
    def zipWith[B,C](list: MyList[B], zip:(A, B) => C): MyList[C]
    def fold[B](start: B)(operator:(B, A) => B): B
  }

  object Empty extends MyList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException
    override def tail: MyList[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
    override def printElements: String = ""

    //
    def map[B](transformer: Nothing => B): MyList[B] = Empty
    def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
    def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
    //Concatenation
    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
    //HFOs
    override def foreach(f: Nothing => Unit): Unit = ()
    override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
    override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
      if(!list.isEmpty) throw new RuntimeException("Lists do not have the same length.")
      else Empty
    }
    override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
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
    /*
      [1,2,3].map(n*2)
        =new Cons(2, [2,3].map(n*2))
        =new Cons(2, new Cons(4, [3].map(n*2)))
        =new Cons(2, new Cons(4, new Cons(6, Empty.map(n*2))))
        =new Cons(2, new Cons(4, new Cons(6, Empty)))
     */
    def map[B](transformer: A => B): MyList[B] = {
      new Cons(transformer(h), t.map(transformer))
    }
    /*
      [1,2].flatMap(n => (n, n+1))
      = [1,2] ++ [2].flatMap(n => (n, n+1))
      = [1,2] ++ [2,3] ++Empty.flatMap(n => (n, n+1))
      = [1,2] ++ [2,3] ++ Empty)
      = [1,2, 2,3]
     */
    def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
      transformer(head) ++ t.flatMap(transformer)
    }
    /*
      [1,2,3].filter(n%2==0)
      [2,3].filter(n%2==0)
      = new Cons(2, [3].filter(n%2==0))
      = new Cons(2, Empty.filter(n%2==0))
      = new Cons(2, Empty)
     */
    def filter(predicate: A => Boolean): MyList[A] = {
      if(predicate(head)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)
    }
    /*
      Concatenation
      [1,2] ++ [3,4,5]
      = new Cons(1, [2] ++ [3,4,5])
      = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
      = new Cons(1, new Cons(2, [3,4,5]))
     */
    def ++[B >: A](list: MyList[B]): MyList[B] = {
      new Cons(h, t ++ list)
    }
    //HFOs
    override def foreach(f: A => Unit): Unit = {
      f(h)
      t.foreach(f)
    }
    override def sort(compare: (A, A) => Int): MyList[A] = {
      def insert(x: A, sortedList: MyList[A]): MyList[A] = {
        if(sortedList.isEmpty) new Cons(h, Empty)
        else if(compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
        else new Cons(sortedList.head, insert(x, sortedList.tail))
      }
      val sortedTail = t.sort(compare)
      insert(h, sortedTail)
    }

    override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
      if(list.isEmpty) throw new RuntimeException("Lists do not have the same length.")
      else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
    }
    /*
      [1,2,3].fold(0)(+)=
      [2,3].fold(1)(+)=
      [3].fold(3)(+)=
      [].fold(6)(+)=
      =6
     */
    override def fold[B](start: B)(operator: (B, A) => B): B = {
      //val newStart = operator(start,h)
      //t.fold(newStart)(operator)
      t.fold(operator(start,h))(operator)
    }
  }
  /*
    1. Expand MyList
      - foreach method A => Unit
        [1,2,3].foreach(x => println(x))
      - sort function ((A, A) => Int) => MyList
        [1,2,3].sort((x, y) => y-x) => [3,2,1]
      - zipWith (list, (A, A) => B) => MyList(B)
        [1,2,3].zipWith([4,5,6] => x * y) => [1*4, 2*5, 3*6] = [4,10,18]
      - fold(start)(function) => a value
        [1,2,3].fold(0)(x+y) = 6

     2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
        fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

     3. compose(f,g) => x => f(g(x))
        andThen(f,g) => x => g(f(x))
   */

  /*
  1. MyList supports for-comprehensions
    - map(f: A => B): MyList[B]
    - filter(predicate: A => Boolean): MyList[A]
    - map(f: A => MyList[B]): MyList[B]
  2. A small collection of ONE element - Maybe[+T]
    - map, flatMap, filter
 */

  //TEST
  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty)))))
  val listOfStrings =  new Cons("a", new Cons("b", Empty))

  val forCombinations = for {
    n <- listOfIntegers
    s <- listOfStrings
  } yield n + "_" + s
  println(forCombinations)
}
