import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalacheck.{ Gen, Arbitrary }


object LLSeqTests extends Properties("LL") {
  import ebarrientos.stdutils.adts.seq.LL

  property("Empty LL contains nothing") = {
    val seq = LL[Int]()
    forAll { i: Int =>
      !seq.contains(i)
    }
  }

  property("Prepend to collection contains the element") = {
    forAll { (i: Int, j: Int, k: Int) =>
      val seq = LL(i).prepend(j).prepend(k)
      seq.contains(i) && seq.contains(j) && seq.contains(k)
    }
  }

  property("Append to collection contains the element") = {
    forAll { (i: Int, j: Int, k: Int) =>
      val seq = LL(i).append(j).append(k)
      seq.contains(i) && seq.contains(j) && seq.contains(k)
    }
  }

  property("Nonempty list is not empty") = {
    forAll { i: Int =>
      LL(i).empty == false
    }
  }

  property("Size of empty list is 0") = {
    LL[String]().size == 0
  }

  property("Size gives correct answer") = {
    def fromList[A](l: List[A], acc: LL[A]): LL[A] = l match {
      case Nil => acc
      case x::xs => fromList(xs, acc.prepend(x))
    }

    forAll { l: List[Int] =>
      fromList(l, LL[Int]()).size == l.size
    }
  }
}
