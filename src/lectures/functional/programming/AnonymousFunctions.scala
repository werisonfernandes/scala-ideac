package lectures.functional.programming

import lectures.part2oop.App2

object AnonymousFunctions extends App2{

  //Anonymous functions => LAMBDA
  //val doubler: Int => Int = (x: Int) => x * 2
  //val doubler: Int => Int = x => x * 2
  val doubler = (x: Int) => x * 2

  //Multiple parameters in a Lambda
  //val adder: (Int, Int) => Int = (x: Int, y: Int) => x + y
  //val adder: (Int, Int) => Int = (x, y) => x + y
  val adder = (x: Int, y: Int) => x + y

  //No params
  //val justDoSomething = () => 3
  val justDoSomething: () => Int = () => 3
  //Careful
  println(justDoSomething) //function its self
  println(justDoSomething.apply()) //call
  println(justDoSomething()) //call

  //Curly braces with lambdas
  val stringToInt = {(str: String) =>
    str.toInt
  }
  println(stringToInt("12"))

  //Moar syntax sugar
  //val niceIncrementer: Int => Int = (x: Int) => x + 1
  //val niceIncrementer2: Int => Int = x => x + 1
  val niceIncrementer2: Int => Int = _ + 1 //equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = (a: Int, b: Int) => a + b
  val niceAdder2: (Int, Int) => Int = _ + _ //equivalent to (a, b) => a + b

  /*
    1. MyList: Replace All FunctionX calls with Lambdas
    2. Rewrite the "special" adder as an anonymous function
   */
  val superAdd= (x: Int) => (y: Int) => x + y
  println(superAdd(3)(5))
}
