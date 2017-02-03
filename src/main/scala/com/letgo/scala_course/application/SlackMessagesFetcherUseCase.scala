package com.letgo.scala_course.application

import scala.concurrent.{ExecutionContext, Future}

import com.letgo.scala_course.domain.{ChannelId, Message, SlackClient}

class SlackMessagesFetcherUseCase(slackClient: SlackClient)(implicit ec: ExecutionContext) {
  var numberOfApiCalls: Int = 0

  var cache: Option[Future[Seq[Message]]] = None

  def fetch(channelName: ChannelId): Future[Seq[Message]] = {
    numberOfApiCalls += 1

    slackClient.fetchChannelMessages(channelName)
  }

  def fetchWithCache(channelName: ChannelId): Future[Seq[Message]] = {
    cache match {
      case Some(cachedMessages) => cachedMessages
      case None =>
        val messages = slackClient.fetchChannelMessages(channelName)

        numberOfApiCalls += 1

        cache = Some(messages)

        messages
    }
    /*
    Alternative solution:

    cache.getOrElse(
      val result = fetch(channelName)
      cache = Some(result)

      result
    )
     */
  }
}
