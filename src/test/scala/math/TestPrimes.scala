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

  property("Set sieve contains the right numbers") = {
    val set = setSieve(100)
    ( set contains 2 ) &&
      ( set contains 17 ) &&
      ( set contains 23 ) &&
      ( set contains 97 )
  }

  property("Sieve has the right number of elements") = {
    val list = primeSieve(100)
    list.size == 25
  }
}
