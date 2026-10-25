package template.spark

import org.apache.spark.sql.functions._

object Main extends InitSpark {
  def main(args: Array[String]) = {



    // 1. Load the Parquet file into a DataFrame using your file path
    val df = spark.read
      .parquet("/Users/yashmundra/Desktop/part-r-00000-1a9822ba-b8fb-4d8e-844a-ea30d0801b9e.gz.parquet")
//        .parquet("/data/desktop/part-r-00000-1a9822ba-b8fb-4d8e-844a-ea30d0801b9e.gz.parquet")
      .repartition(10) // Triggers a shuffle to create 10 partitions


    println(s"Number of partitions: ${df.rdd.partitions.length}")

    // 2. Print the schema to see the column structure
    df.printSchema()

    // 3. Display the contents of the DataFrame
    df.show()

    val filteredDf = df.filter(col("count") > 1)

    filteredDf.show()


    val usFlightCounter = sc.longAccumulator("US-Flight-Counter")
    val otherFlightCounter = sc.longAccumulator("Other-Flight-Counter")

    // 3. Use foreach to iterate through rows and update accumulators during an action
    // Note: foreach is an action, which forces Spark to execute the job!
    df.foreach { row =>
      val dest = row.getAs[String]("DEST_COUNTRY_NAME")
      if (dest == "United States") {
        usFlightCounter.add(1L)
      } else {
        otherFlightCounter.add(1L)
      }
    }

    println("----------------------------------------")
    println(s"Total US Flights counted via Accumulator: ${usFlightCounter.value}")
    println(s"Total Other Flights counted: ${otherFlightCounter.value}")
    println("----------------------------------------")

close

  }
}



