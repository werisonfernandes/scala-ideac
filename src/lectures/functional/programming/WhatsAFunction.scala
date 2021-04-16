package lectures.functional.programming

object WhatsAFunction extends App {

  //DREAM: Use functions as first class elements
  //PROBLEM: OOP = POO

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(4))

  //function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  println(stringToIntConverter("34") + 1)

  //function types = Function1[A, B]
  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  //Function types Function2[A, B, R] === (A, B) => R
  //ALL Scala functions are objects
  /*
    1. A function which takes two strings and concatenates them
    2. Transform MYPredicate and MyTransform into function types
    3. Define a function which takes and Int and returns another function which takes an Int and return
       and Int
       - What's the type of this function
       - How to do it
   */
  //def concatenator: (String, String) => String = (v1: String, v2: String) => v1 + v2
  def concatenator: (String, String) => String = new Function2[String, String, String]{
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println(concatenator("Werison", "Fernandes"))

  trait MyPredicate[-T] { //T => Boolean
    def test(element: T): Boolean
  }

  trait MyTransformer[-A, B] { //A => B
    def transforme(element: A): B
  }

  //Higher order functions
  //Function1[Int, Function1[Int, Int]]
  val supperAdder = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Int => Int = new Function[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }
  val adder3 = supperAdder(10)
  println(adder3(23))
  println(supperAdder(11)(45)) //Curried function
}

trait MyFunction[A, B] {
  def apply(element: A): B
}

trait Action[A, B] {
  def execute(element: A): B
}
