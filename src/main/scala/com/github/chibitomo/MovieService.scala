package com.github.chibitomo

import akka.stream.javadsl.Source
import com.github.chibitomo.MovieService.{Principal, TvSeries}

trait MovieService {
  def principalsForMovieName(name: String): Source[Principal, _]
  def tvSeriesWithGreatestNumberOfEpisodes(): Source[TvSeries, _]
}

object MovieService {
  final case class Principal(name: String, birthYear: Int, deathYear: Option[Int], profession: List[String])
  final case class TvSeries(original: String, startYear: Int, endYear: Option[Int], genres: List[String])
}
