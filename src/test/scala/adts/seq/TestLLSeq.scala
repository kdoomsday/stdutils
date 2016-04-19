package adts.seq

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalacheck.{ Gen, Arbitrary }


object TestLLSeq extends Properties("LL") {
  import ebarrientos.stdutils.adts.seq.LL

  /** Makes an LL from a standard scala List */
  private[this] def fromList[A](l: List[A]): LL[A] = {
    def fromList(l: List[A], acc: LL[A]): LL[A] = l match {
      case Nil => acc
      case x::xs => fromList(xs, acc.prepend(x))
    }
    fromList(l, LL())
  }

  // LL generators for tests
  val lls: Gen[LL[Int]] = for { l <- Gen.listOf(Arbitrary.arbitrary[Int]) } yield fromList(l)
  implicit val genLLs = Arbitrary(lls)


  private[this] def l2llEquality[A](l: List[A], ll: LL[A]): Boolean = {
    import ebarrientos.stdutils.adts.seq.{Node, Empty}

    (l, ll) match {
      case (Nil, Empty()) => true
      case (x::xs, Node(h, t)) => x == h && l2llEquality(xs, t)
      case _ => false
    }
  }


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

  property("Append to empty behaves the same as prepend to empty") = {
    forAll { i: Int =>
      LL[Int]().prepend(i) === LL[Int]().append(i)
    }
  }

  property("Nonempty list is not empty") = {
    forAll { i: Int =>
      !LL(i).empty
    }
  }

  property("Size of empty list is 0") = {
    LL[String]().size == 0
  }

  property("Size gives correct answer") = {
    forAll { l: List[Int] =>
      fromList(l).size == l.size
    }
  }

  property("Size behaves normally on prepend") = {
    forAll { (l1: LL[Int], i: Int) =>
      val l2 = l1.prepend(i)
      l2.size == l1.size + 1
    }
  }


  property("List equals itself") = {
    forAll { l: List[Int] =>
      val ll = fromList(l)
      ll === ll
    }
  }

  property("Different lists don't equal themselves") = {
    forAll { l: List[Int] =>
      val l1 = fromList(l)
      val l2 = l1.prepend(42)
      !(l1 === l2)
    }
  }

  property("Map on empty produces empty") = {
    LL[Int]().map(_ + 1) === LL[Int]()
  }

  property("Map on actual lists produces correct result") = {
    forAll { l: List[Int] =>
      val ll = fromList(l.reverse)  // From llist makes the LL backwards
      val f = (x: Int) => 2*x
      l2llEquality(l.map(f), ll.map(f))
    }
  }

  property("Fold left on empty gives back the initial value") = {
    forAll { i: Int =>
      LL[Int]().foldLeft(i)(_ + _) == i
    }
  }

  property("Fold right on empty gives back the initial value") = {
    forAll { i: Int =>
      LL[Int]().foldRight(i)(_ + _) == i
    }
  }

  property("Fold sum on list of ints is the same as regular list") = {
    forAll { l: List[Int] =>
      val ll = fromList(l)

      ll.foldLeft(0)(_ + _) == l.foldLeft(0)(_ + _)
    }
  }

  property("FoldLeft works for sting concat as expected") = {
    val ll = fromList((1 to 9).toList.reverse)  // Note reverse, because fromList reverses
    ll.foldLeft("")(_ + _) == "123456789"
  }

  property("FoldRight works for string concat reversed") = {
    val ll = fromList((1 to 9).toList)
    ll.foldRight("")(_ + _) == "123456789"
  }
}
