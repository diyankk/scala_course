package com.letgo.scala_course.infrastructure.stub

import com.letgo.scala_course.domain._

object MessageStub {
  def create(
    text: MessageText = MessageTextStub.random,
    actions: Option[Seq[MessageAction]] = MessageActionStub.randomSeq()
  ): Message = Message(text, actions)

  def random: Message = create()
}
