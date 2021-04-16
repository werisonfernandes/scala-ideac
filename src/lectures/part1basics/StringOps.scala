package lectures.part1basics

import scala.:+

object StringOps extends App {

  val str: String = "Hello, i am learning scala!!!"
  println(str.charAt(1))
  println(str.substring(0, 5))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.length)

  val aNumberString: String = "34"
  println(aNumberString)
  val aNumber: Int = aNumberString.toInt;
  println(aNumber)

  //+: prepend
  //:+ append
  println('a' +: aNumberString :+ 'z')

  println(str.reverse)
  println(str.take(2))

  //Scala specific: Interpolators.
  val name= "Werison"
  val age = 38
  val greeting = s"Hello, my name is $name and i am $age years old."
  println(greeting)

  val anotherGreeting = s"Hello, my name is $name and i am ${age+1} years old."
  println(anotherGreeting)

  val speed = 19090.259f
  val myth = f"$name%s can eat $speed%2.2f hamburgers per minute."
  println(myth)

  //raw interpolator: ignores escaped character
  println(s"This is a \n new line")
  println(raw"This is a \n new line")
  val escaped = "This is a \n new line"
  println(raw"$escaped")
}
