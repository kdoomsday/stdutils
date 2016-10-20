package ebarrientos.stdutils.math

object Primes {
  import scala.math.sqrt

  def isPrime(i: Long): Boolean = {
    if (i < 2) false
    else if (i == 2 || i == 3) true
    else {
      val top = sqrt(i.toDouble).toInt
      (2 to top).forall(i % _ != 0)
    }
  }

  def primeSieve(top: Long): Seq[Long] = ???
}
