package com.github.chibitomo

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.csv.scaladsl.CsvParsing
import akka.stream.alpakka.csv.scaladsl.CsvToMap
import akka.stream.scaladsl.Sink
import akka.stream.scaladsl.FileIO
import org.scalatest.concurrent.IntegrationPatience
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.io.File
import java.nio.file.Paths
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import org.scalatest.concurrent.PatienceConfiguration.Timeout

class TsvDataSourceSpec extends AnyFlatSpec with Matchers with ScalaFutures {

  implicit val actorSystem: ActorSystem = ActorSystem()

  "GzTsvDataSource" should "open file" in {
    // GIVEN
    val filename = getClass.getResource("/title.ratings.tsv.gz")
    val file     = new File(filename.getFile)

    // WHEN
    val datasource = TsvDataSource(file, "UTF-8")

    // THEN
    println(datasource.next())
  }

  it should "open file again" in {
    // GIVEN

    val filename = getClass.getResource("/title.ratings.tsv")
    val file     = new File(filename.getFile)
    val fileIO   = FileIO.fromPath(file.toPath)

    val result = for {
      value1 <- fileIO
                  .via(CsvParsing.lineScanner(delimiter = '\t'))
                  .via(CsvToMap.toMapAsStrings())
                  .runWith(Sink.seq)

      _ = value1.head.foreach(println)
    } yield value1

    whenReady[Seq[Map[String, String]], Unit](result, Timeout(60.second)) { x =>
      x should have size 1
    }
//
//    // WHEN
//    val datasource = TsvDataSource(file, "UTF-8")
//
//    // THEN
//    println(datasource.next())
  }

}
