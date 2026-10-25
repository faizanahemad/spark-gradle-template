package template.spark
import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, LogManager, Logger}
//import org.apache.log4j.{Level, LogManager, Logger}
//import org.apache.spark.sql.SparkSession

//trait InitSpark {
//  val spark: SparkSession = SparkSession.builder()
//                            .appName("Spark example")
//                            .master("local[*]") //all cores of the server will be used
//                            .config("option", "some-value")
//                            .getOrCreate()
//
//  val sc = spark.sparkContext
//
//  def close = {
//    spark.close()
//  }
//}




trait InitSpark {
  val spark: SparkSession = SparkSession.builder()
    .appName("Spark Kubernetes Flight App")
    .master("k8s://https://127.0.0.1:55620") // Update port if it changes
    .config("spark.submit.deployMode", "client")
    .config("spark.kubernetes.container.image", "apache/spark:4.2.0")
    .config("spark.kubernetes.container.image.pullPolicy", "IfNotPresent")
    .config("spark.kubernetes.authenticate.driver.serviceAccountName", "spark")
    .config("spark.executor.instances", "2")
    .config("spark.driver.host", "10.110.143.52")
    .config("spark.driver.bindAddress", "0.0.0.0")
    .getOrCreate()

  val sc = spark.sparkContext

  def close(): Unit = {
    spark.close()
  }
}