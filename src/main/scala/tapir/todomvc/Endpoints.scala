package tapir.todomvc
import java.nio.charset.Charset
import java.util.UUID

import tapir._
import tapir.json.circe._
import io.circe.generic.auto._
import tapir.GeneralCodec.PlainCodec
import cats.implicits._
import tapir.DecodeResult.{Missing, Value}
import tapir.MediaType.TextPlain
import tapir.Schema.SString
class Endpoints(charset: Charset) {

  implicit private val uuidCodec: PlainCodec[UUID] = new PlainCodec[UUID] {
    override def encode(t: UUID): String = t.toString
    override def decode(s: String): DecodeResult[UUID] =
      Either.catchNonFatal(UUID.fromString(s)).fold(_ => Missing, Value(_))
    override val rawValueType: RawValueType[String] = StringValueType(charset)
    override def schema: Schema                     = SString
    override def mediaType: MediaType.TextPlain     = TextPlain(charset)
  }

  val postEndpoint: Endpoint[Todo, String, Todo] =
    endpoint.post
      .in(jsonBody[Todo])
      .out(jsonBody[Todo])
      .errorOut(stringBody)

  val deleteEndpoint: Endpoint[Unit, String, List[Todo]] =
    endpoint.delete
      .out(jsonBody[List[Todo]])
      .errorOut(stringBody)

  val getEndpoint: Endpoint[Unit, String, List[Todo]] =
    endpoint.get
      .out(jsonBody[List[Todo]])
      .errorOut(stringBody)

  val getTodoEndpoint: Endpoint[UUID, String, Todo] =
    endpoint.get
      .in(path[UUID]("uuid"))
      .out(jsonBody[Todo])
      .errorOut(stringBody)

  val patchByIdEndpoint: Endpoint[(UUID, Todo), String, Todo] =
    endpoint.patch
      .in(path[UUID]("uuid")(uuidCodec))
      .in(jsonBody[Todo])
      .out(jsonBody[Todo])
      .errorOut(stringBody)

}
