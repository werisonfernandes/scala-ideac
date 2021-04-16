package exercises

object CaseClasses extends App {

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
    //
    def map[B](transformer: MyTransformer[A, B]): MyList[B]
    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
    def filter(predicate: MyPredicate[A]): MyList[A]
    //Concatenation
    def ++[B >: A](list: MyList[B]): MyList[B]
  }

  case object Empty extends MyList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException
    override def tail: MyList[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
    override def printElements: String = ""

    //
    def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
    def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
    def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
    //Concatenation
    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  }

  case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
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
    def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
      new Cons(transformer.transforme(h), t.map(transformer))
    }
    /*
      [1,2].flatMap(n => (n, n+1))
      = [1,2] ++ [2].flatMap(n => (n, n+1))
      = [1,2] ++ [2,3] ++Empty.flatMap(n => (n, n+1))
      = [1,2] ++ [2,3] ++ Empty)
      = [1,2, 2,3]
     */
    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
      transformer.transforme(head) ++ t.flatMap(transformer)
    }
    /*
      [1,2,3].filter(n%2==0)
      [2,3].filter(n%2==0)
      = new Cons(2, [3].filter(n%2==0))
      = new Cons(2, Empty.filter(n%2==0))
      = new Cons(2, Empty)
     */
    def filter(predicate: MyPredicate[A]): MyList[A] = {
      if(predicate.test(head)) new Cons(h, t.filter(predicate))
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
  }

  /*
   1. Generic trait MyPredicate[-T]] with a little method test(t): Boolean
   2. Generic trait MyTransformer[-A, B] with a method transform A => B
   3. MyList
     - map(transformer): MyList
     - filter(predicate): MyList
     - flatMap(transformer from A to MyList Of B): MyList[B]

     class EvenPredicate extends MyPredicate[Int]
     class StringToIntTransformer extends MyTransformer[String, Int]
     [1,2,3].map(n*2) = [2,4,6]
     [1,2,3,4].filter(n%2) = [2,4]
     [1,2,3].flatMap(n=>[n,n+1]) = [1,2,2,3,3,4]
  */
  trait MyPredicate[-T] {
    def test(element: T): Boolean
  }

  trait MyTransformer[-A, B] {
    def transforme(element: A): B
  }

  //Test
  var listOfIntegers: MyList[Int] = Empty
  var anotherListOfIntegers: MyList[Int] = Empty
  var listOfStrings: MyList[String] = Empty

  println(listOfIntegers)
  println(listOfStrings)

  listOfIntegers = listOfIntegers.add(1).add(2).add(3)
  anotherListOfIntegers = listOfIntegers.add(4).add(5).add(6)
  listOfStrings = listOfStrings.add("Werison")
  //
  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transforme(element: Int): Int = element * 2
  }))
  //
  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element%2==0
  }))
  //
  println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transforme(element: Int): MyList[Int] = new Cons(element, new Cons(element+1, Empty))
  }))

  println(listOfIntegers ++ anotherListOfIntegers)

  val clone1 = new Cons(1, Empty)
  val clone2 = new Cons(1, Empty)
  println(clone2 == clone1)
}
