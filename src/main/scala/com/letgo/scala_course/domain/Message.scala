package com.letgo.scala_course.domain

case class Message(text: MessageText, actions: Option[Seq[MessageAction]] = None)
