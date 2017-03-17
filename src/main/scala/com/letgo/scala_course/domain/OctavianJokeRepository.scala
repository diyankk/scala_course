package com.letgo.scala_course.domain

trait OctavianJokeRepository {
  def searchAll(): List[OctavianJoke]
}

class OctavianJokeInMemoryRepository extends OctavianJokeRepository {
  override def searchAll(): List[OctavianJoke] = {
    List(
      OctavianJoke(
        Message(
          MessageText(
            "(Viendo a alguien comer un aguacate en la cocina): " +
            "Octavian: 'Heheheheeeeeeeeiiiiii Estas catando agua?'"
          )
        )
      ),
      OctavianJoke(Message(MessageText("Octavian hablando a un brick de salmorejo: Sal, Morejo!! Sal!"))),
      OctavianJoke(Message(MessageText("Octavian: Elastic de plastic")))
    )
  }
}
