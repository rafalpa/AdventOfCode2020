package day5

sealed trait BinaryEncoding {
  def zeroChar: Char

  def oneCHar: Char
}

class BinaryRow extends BinaryEncoding {
  override def zeroChar: Char = 'F'

  override def oneCHar: Char = 'B'
}

class BinaryColumn extends BinaryEncoding {
  override def zeroChar: Char = 'L'

  override def oneCHar: Char = 'R'
}
