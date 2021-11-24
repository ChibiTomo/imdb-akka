package com.github.chibitomo

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.zip.GZIPInputStream

class TsvDataSource(reader: BufferedReader) extends DataSource with Iterator[String] {
  override def hasNext(): Boolean = reader.ready
  override def next(): String     = reader.readLine()
}

object TsvDataSource {
  def apply(file: File, encoding: String): TsvDataSource =
    new TsvDataSource(
      new BufferedReader(
        new InputStreamReader(
          new GZIPInputStream(
            new FileInputStream(file)
          ),
          encoding
        )
      )
    )
}
