package template.spark

import org.apache.spark.sql.functions._

final case class Person(firstName: String, lastName: String, country: String, age: Int)

object Main extends InitSpark {
  def main(args: Array[String]) = {
    import spark.implicits._

    val version = spark.version
    println("VERSION_STRING = " + version)

    val sumHundred = spark.range(1, 101).reduce(_ + _)
    println(f"sum 1 to 100 = $sumHundred")

    val persons = reader.csv("people-example.csv").as[Person]
    persons.show(2)
    val averageAge = persons.agg(avg("age")).first.get(0).asInstanceOf[Double]
    println(f"Average Age: $averageAge%.2f")

    close
  }
}
