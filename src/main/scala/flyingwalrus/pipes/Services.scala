package flyingwalrus
package pipes

import cats._, implicits._
import com.twitter.finagle._
import com.twitter.finagle.http.{ Request, Response }
import com.twitter.util._
import io.catbird.util._
import io.iteratee._
import io.iteratee.twitter._

final case class Data(id: String,test: String,num: Int)
final case class Person(id: String,name: String,age: Short,score: Byte)

object services {

  /**
    * my own monitor that does exactly the same thing as the default
    * monitor. at some point I assume i'd want to do something
    * slightly more intellegent that printStackTrace ...
    */
  object monitor extends Monitor {
    def handle(t: Throwable): Boolean =  {
      t.printStackTrace()
      true
    }
  }

  val google: Service[Request,Response] = Http.client.
    withLabel("google").
    withMonitor(monitor).
    newService("www.google.com")

}

object pipe {
  def fromFuture[A](fa: Future[A]): Rerunnable[A] = Rerunnable.fromFuture(fa)
  def liftE[A](f: () => Future[List[A]]): Enumerator[Rerunnable,List[A]] = liftToEnumerator(Rerunnable.fromFuture(f()))
  def fl[A,B](fa: List[A])(f: A => List[B]): List[B] = fa.flatMap(f)
  def litoli(i: Int): List[Int] = List(i)

  def scratch: Unit = {
    val rli: Enumerator[Rerunnable,Int] = enumList(List(1,2,3))
    val ie: Enumerator[Rerunnable,Int] = rli.flatMap { i: Int => enumOne(i) }
    def flatten[A](fa: Enumerator[Rerunnable,List[A]]): Enumerator[Rerunnable,A] = fa.flatMap { l => enumList(l) }
  }
}
