import org.scalacheck.Properties
import org.scalacheck.Prop.forAll


object LLSeqTests extends Properties("LL") {
  import ebarrientos.stdutils.adts.Coll._
  import ebarrientos.stdutils.adts.seq.TCSeq._
  import ebarrientos.stdutils.adts.seq.LL

  property("Empty LL contains nothing") = {
    val seq = LL[Int]()
    forAll { i: Int =>
      !seq.contains(i)
    }
  }

  property("Prepend to collection contains the element") = {
    forAll {(i: Int, j: Int, k: Int) =>
      val seq = LL(i).prepend(j).prepend(k)
      seq.contains(i) && seq.contains(j) && seq.contains(k)
    }
  }

  property("Append to collection contains the element") = {
    forAll {(i: Int, j: Int, k: Int) =>
      val seq = LL(i).append(j).append(k)
      seq.contains(i) && seq.contains(j) && seq.contains(k)
    }
  }

  property("Nonempty list is not empty") = {
    forAll { i: Int =>
      LL(i).empty == false
    }
  }
}
