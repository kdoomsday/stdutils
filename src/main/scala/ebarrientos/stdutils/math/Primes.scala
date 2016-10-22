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

  /** Al primes less than or equal to top */
  def primeSieve(top: Int): Seq[Int] = {
    val nums = Array.fill(top+1)(true)

    for (i <- 4 to top by 2) nums(i) = false

    var next = 3
    while (next < top) {
      for (pos <- next*next to top by next)
        nums(pos) = false
      
      next += 2
      while (next <= top && !nums(next)) {
        next += 2
      }
    }

    Seq(2) ++ ( for(i <- 3 to top by 2 if nums(i)) yield i )
  }
}
