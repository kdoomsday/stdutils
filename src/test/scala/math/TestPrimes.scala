package math

import org.scalacheck.Properties


class TestPrimes extends Properties("Primes") {
  import ebarrientos.stdutils.math.Primes._

  property("Primes are primes") = {
    isPrime(2) && isPrime(3) && isPrime(101) && isPrime(3331)
  }

  property("Non primes aren't primes") = {
    !(isPrime(9) || isPrime(21) || isPrime(3333))
  }

  property("Sieve has the right number of elements") = {
    val list = primeSieve(100)
    list.size == 25
  }
}
