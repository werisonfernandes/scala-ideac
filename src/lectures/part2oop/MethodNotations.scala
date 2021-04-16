package lectures.part2oop

object MethodNotations extends App {

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  //Infix notation
  println(mary likes "Inception") //Equivalent => Infix notation => Operator notation (syntactic sugar)

  //Prefix notation
  val x = -1 //Equivalent with 1.unary_-
  val y = 1.unary_-
  //unary_- prefix only work with + - ~ !
  println(!mary)
  println(mary.unary_!)

  //postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  //apply
  println(mary.apply())
  println(mary()) //Equivalent

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"${this.name} hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)
    def unary_! : String = s"$name What the heck?!"
    def unary_+ : Person = new Person(name, favoriteMovie, age+1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and i like $favoriteMovie"
    def apply(n: Int) = s"${this.name} watched $favoriteMovie $n times"
    def learns(thing: String) = s"$name is learning $thing"
    def learnScala = this learns "Scala"
  }

  //"Operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary + tom)
  println(mary.+(tom))

  //All Operators are methods
  println(2 + 3)
  println(1.+(4))
  //Akka actors have operators like !(bang), ?(question mark)

  /*
    1. Overload the + operator
      mary + "rockstar" => new Person "Mary (the rockstar)"
    2. Add an age to the Person class
      Add a unary + operator => new person with the age + 1
      +mary => mary with the age incrementer
    3. Add a "learns" method in the Person class => "Mary learns scala."
      Add a learnScala method, calls learns method with "scala"
      Use it in postfix notation
    4. Overload the apply method
      mary.apply(2) => "Mary watched Inception 2 times"
   */
  println((mary + "the rockstar")())
  println((+mary).age)
  println(mary learnScala)
  println(mary(3))
}
