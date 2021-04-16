package lectures.part1basics

object ValuesVariablesTypes extends App{
  //VALUES
  val x: Int = 56;
  println(x)
  //Val are immutable
  //x = 2

  //Compile can infer types
  val y = 20
  println(y)

  val aString: String = "Hello"
  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val aShort: Short = 123
  val aLong: Long = 122434343555L
  val aFloat: Float = 2.0f
  val aDouble: Double = 2.0

  //VARIABLES
  var aVariable: Int = 45
  aVariable = 7 //side effects
  println(aVariable)
}
