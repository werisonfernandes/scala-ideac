package lectures.part2oop

object AnonymousClasses extends App2 {

  abstract class Animal {
    def eat: Unit
  }

  //Anonymous class
  val funnyAnimal: Animal= new Animal {
    override def eat: Unit = println("Ha hah hah!!!")
  }
  println(funnyAnimal.getClass)
  /*Equivalent with
    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("Ha hah hah!!!")
    }
    val funnyAnimal: Animal= AnonymousClasses$$anon$1()
   */
  funnyAnimal.eat

  class Person(val name: String) {
    def sayHi(): Unit = println(s"Hi, my name is $name, how can i help you?")
  }

  val person = new Person("Jim") {
    override def sayHi(): Unit = println(s"Hi, my name is ${this.name}, how can i help you?")
  }
  person.sayHi()

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
}
