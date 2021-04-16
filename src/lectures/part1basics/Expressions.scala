package lectures.part1basics

object Expressions extends App {

  val x = 1 + 4 //Expression
  println(x)

  // + - * / & | ^ << >> >>> (right shift with zero extension)
  println(3 + 4 * 2)

  //== != > < >= <=
  println(1 == x)

  //! && ||
  println(!(1 == x))

  var aVariable = 2
  aVariable += 3
  println(aVariable) //also works with *= -= /= ... side effects

  //Instructions vs Expressions
  //IF Expression
  val aCondition = false
  val aConditionValue = if(aCondition) 3 else 6 //IF Expression
  println(aConditionValue)

  println(if(aCondition) 3 else 6)

  var i = 0
  val aWhile = while(i < 10) {
    println(i)
    i+=1
  } //Never right this again

  //Everything in Scala is an Expression

  val aWeirdValue = (aVariable + 5) //Unit === void
  println(aWeirdValue)

  //Side effects. Expressions return void, println(), while, reassigning

  //Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if(z > 2) "Hello" else "Goodbye"
  }
  println(aCodeBlock)

  //Exercises
  //1. What's the difference between "hello world" vs println("hello world")? (String value, Unit expression)
  //2.
  val someValue = {
    2 > 3
  }
  println(someValue) //false

  val someOherValue = {
    if(someValue) 239 else 986
    42
  }
  println(someOherValue) //42
}
