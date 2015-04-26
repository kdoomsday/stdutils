package ebarrientos.stdutils.math

class Rational(initialNumer: BigInt = 1, initialDenom: BigInt = 1) extends Ordered[Rational] {
  def this(n: Rational, d: Rational) = this(n.numer * d.denom, n.denom * d.numer)
  
  require(initialDenom != 0, "denominator must be nonzero")
  private val gcd = {
    def gcdRec(x: BigInt, y: BigInt): BigInt = {
      if(y == 0) x else gcdRec(y, x % y)
      }
    gcdRec(initialNumer, initialDenom).abs
  }
  val numer = (if(initialDenom < 0) -(initialNumer.abs) else initialNumer).abs / gcd
  val denom = initialDenom.abs / gcd
  def unary_- = new Rational(-numer, denom)
  def +(that: Rational) =
  new Rational(that.numer * denom + numer * that.denom,
    denom * that.denom)
  def -(that: Rational) = this + -that
  def *(that: Rational) = new Rational(numer * that.numer, denom * that.denom)
  def /(that: Rational) = new Rational(numer * that.denom, denom * that.numer)
  def max(that: Rational) = if (this < that) that else this
  
  def ==(that: Rational) = numer == that.numer && denom == that.denom
  override def equals(that: Any): Boolean = that match {
    case thatRat: Rational => this == thatRat
    case _ => false
  }
  override def hashCode = (numer / denom).hashCode
  
  override def compare(that: Rational) = {
    val res = numer*that.denom - that.numer*denom
    if (res < 0) -1
    else if (res == 0) 0
    else 1
  }
  
  override def toString = numer + "/" + denom
}

object Rational {
  import scala.language.implicitConversions
  
  def apply(initialNumer: BigInt = 1, initialDenom: BigInt = 1) = new Rational(initialNumer, initialDenom)
  def apply(initialNumer: Rational, initialDenom: Rational) = new Rational(initialNumer, initialDenom)
  
  implicit def int2Rat(i: Int) = Rational(i)
  implicit def long2Rat(l: Long) = Rational(l)
  implicit def bi2Rat(bi: BigInt) = Rational(bi)
}