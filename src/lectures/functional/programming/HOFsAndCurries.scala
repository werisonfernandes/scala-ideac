package lectures.functional.programming

object HOFsAndCurries extends App {

  //Higher order function
  //map, flatMap, filter
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  //Function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(3))) = nTimes(f, 2, f(x))) = f(f(f(x)))
  // nTimes(f, n, x) = f(f(...f(x))) = nTimes(f, n-1, f(x)))
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if(n <= 0) {x}
    else {
      //val y = f(x)
      //nTimes(f, n - 1, y)
      nTimes(f, n - 1, f(x))
    }
  }
  println(nTimes(x => x + 1, 10, 0))

  //nbt(f, n) = f(f(f...f(x)))
  //increment10 = ntb(plusOne, 10) = x => plusOne(plusOne...(x))
  //val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): Int => Int = {
    if(n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))
  }
  println(nTimesBetter(x => x +1, 10)(1))

  //Curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3)
  println(add3(10))
  println(superAdder(3)(10))

  //functions with multiple parameters list
  def curriedFormater(c: String)(x: Double): String = c.format(x)
  val standardFormat: (Double => String) = curriedFormater("%4.2f")
  val preciseFormat: (Double => String) = curriedFormater("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

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
  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = {
    x => y => f(x, y)
  }
  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = {
    (x, y) => f(x)(y)
  }
  //FunctionX
  def compose(f: Int => Int, g: Int => Int): Int => Int = {
    x => f(g(x))
  }
  def compose2[A,B,T](f: A => B, g: T => A): T => B = {
    x => f(g(x))
  }
  def andThen(f: Int => Int, g: Int => Int): Int => Int = {
    x => g(f(x))
  }
  def andThen2[A,B,C](f: A => B, g: B => C): A => C = {
    x => g(f(x))
  }
  //TESTS
  def supperAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(20, 10))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)
  println(composed(4))
  println(ordered(4))

}
