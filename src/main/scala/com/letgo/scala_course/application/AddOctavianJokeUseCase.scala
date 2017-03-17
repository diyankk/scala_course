package com.letgo.scala_course.application

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

import com.letgo.scala_course.domain.{ChannelId, OctavianJokeRepository, SlackClient}

class AddOctavianJokeUseCase(
  slackClient: SlackClient, octavianJokeRepository: OctavianJokeRepository
)(implicit ec: ExecutionContext) {
  def add(channel: ChannelId): Future[Unit] = slackClient.addMessage(
    channel,
    Random.shuffle(octavianJokeRepository.searchAll()).head.message
  )
}
