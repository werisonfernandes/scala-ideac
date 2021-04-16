package lectures.part2oop

object Exceptions extends App2 {

  //1. Throwing exceptions
  val x: String = null
  //println(x.length)
  //This will**crash with a NPE

  //Throwing and Catching exceptions
  //throw new NullPointerException
  //val aWeirdValue: String = throw new NullPointerException
  //println(aWeirdValue)
  //throwable classes extend the Throwable class
  //Exception and the Error are the major Throwable subtypes

  //2. How to catch exceptions
  def getInt(withExcepions: Boolean): Int = {
    if(withExcepions) throw new RuntimeException("No int for yow!!!")
    42
  }
  try {
    //Code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught a RuntimeException")
  } finally {
    //Code that will get executed no Matter What
    println("Finally")
  }

  val potentialFail = try {
    //Code that might throw
    getInt(true)
  } catch {
    case e: NullPointerException => println("Caught a NullPointerException")
    case e: RuntimeException => println("Caught a RuntimeException")
  } finally {
    //Code that will get executed no Matter What
    //Option
    //Does not influence the return type of this expression
    //Use finally only for side effects
    println("Finally")

    //3. How to define your own exceptions
    class MyException extends Exception
    val exception = new MyException
    //throw exception
    /*
      1. Crash your program with an OutOfMemoryError
      2. Crash with SOError
      3. PocketCalculator
        - add(x,y)
        - subtract(x,y)
        - multiply(x,y)
        - divide(x,y)

        Throw
        - OverflowException if add(x,y) exceeds Int.MAX_VALUE
        - UnderflowException if add(x,y) exceeds Int.MIN_VALUE
        - MathCalculationException for divide by 0
     */
  }
  println(potentialFail)
}
