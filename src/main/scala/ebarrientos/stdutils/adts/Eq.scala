package ebarrientos.stdutils.adts

import simulacrum._

import scala.language.{ higherKinds, implicitConversions }

@typeclass trait Eq[A] {
  @op("===") def same(a1: A, a2: A): Boolean
}

object Eq {
  implicit object eqInt extends Eq[Int] {
    def same(a1: Int, a2: Int) = a1 == a2
  }

  implicit object eqLong extends Eq[Long] {
    def same(a1: Long, a2: Long) = a1 == a2
  }

  implicit object eqFloat extends Eq[Float] {
    def same(a1: Float, a2: Float) = a1 == a2
  }

  implicit object eqDouble extends Eq[Double] {
    def same(a1: Double, a2: Double) = a1 == a2
  }

  implicit object eqBoolean extends Eq[Boolean] {
    def same(a1: Boolean, a2: Boolean) = a1 == a2
  }

  implicit object eqString extends Eq[String] {
    def same(a1: String, a2: String) = a1 == a2
  }
}
