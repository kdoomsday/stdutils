package ebarrientos.stdutils.adts

import simulacrum._

import scala.language.{ higherKinds, implicitConversions }

/**
  * User: Eduardo Barrientos
  * Date: 17/04/16
  * Time: 09:12 PM
  */
@typeclass trait Foldable[Repr[_]] {
  def foldLeft[A, B](r: Repr[A], init: B)(f: (B, A) => B): B
}
