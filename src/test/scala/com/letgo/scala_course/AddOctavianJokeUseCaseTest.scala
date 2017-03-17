package com.letgo.scala_course

import akka.actor.ActorSystem
import org.scalatest.Matchers.contain
import org.scalatest.{GivenWhenThen, WordSpec}
import org.scalatest.concurrent.{Eventually, ScalaFutures}
import org.scalatest.time.{Millis, Seconds, Span}

import com.letgo.scala_course.application.{AddOctavianJokeUseCase, SlackMessageAdderUseCase, SlackMessagesFetcherUseCase}
import com.letgo.scala_course.domain.OctavianJokeInMemoryRepository
import com.letgo.scala_course.infrastructure.GilbertSlackClient
import com.letgo.scala_course.infrastructure.stub.{ChannelIdStub, MessageActionStub, MessageStub}

class AddOctavianJokeUseCaseTest extends WordSpec with GivenWhenThen with ScalaFutures with Eventually {
  implicit private val actorSystem      = ActorSystem("test-actor-system")
  implicit private val executionContext = scala.concurrent.ExecutionContext.global

  implicit override val patienceConfig = PatienceConfig(
    timeout = scaled(Span(3, Seconds)),
    interval = scaled(Span(100, Millis))
  )

  private val client = new GilbertSlackClient
  private val octavianJokeRepository = new OctavianJokeInMemoryRepository

  private def slackMessagesFetcherUseCase = new SlackMessagesFetcherUseCase(client)

  private def addOctavianJokeUseCase = new AddOctavianJokeUseCase(client, octavianJokeRepository)

  "AddOctavianJokeUseCase" should {
    "publish a message with a joke by Octavian to a channel" in {
      val scalaCourseChannelId = ChannelIdStub.scalaCourse

      addOctavianJokeUseCase.add(scalaCourseChannelId).futureValue

      eventually {
        val authoredMessages = slackMessagesFetcherUseCase.fetch(scalaCourseChannelId).futureValue

        val messages = authoredMessages.map(_.message)

        // messages should contain(message)
      }
    }
  }
}
