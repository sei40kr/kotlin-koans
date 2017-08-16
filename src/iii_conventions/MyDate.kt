package iii_conventions

data class MyDate(val year : Int, val month : Int, val dayOfMonth : Int) : Comparable<MyDate> {
  override fun compareTo(other : MyDate) : Int = (year * 365 + month * 31 + dayOfMonth).compareTo(
      other.year * 365 + other.month * 31 + other.dayOfMonth)

  operator fun plus(timeInterval : TimeInterval) : MyDate = addTimeIntervals(timeInterval, 1)

  operator fun plus(repeatedTimeInterval : RepeatedTimeInterval) : MyDate = addTimeIntervals(
      repeatedTimeInterval.timeInterval, repeatedTimeInterval.number)
}

operator fun MyDate.rangeTo(other : MyDate) : DateRange = DateRange(this, other)

enum class TimeInterval {
  DAY, WEEK, YEAR;

  operator fun times(number : Int) : RepeatedTimeInterval = RepeatedTimeInterval(this, number)
}

class RepeatedTimeInterval(val timeInterval : TimeInterval, val number : Int)

class DateRange(override val start : MyDate,
    override val endInclusive : MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
  override fun iterator() : Iterator<MyDate> {
    return object : Iterator<MyDate> {
      private var current : MyDate? = null

      override fun hasNext() : Boolean = if (current == null) start <= endInclusive else current!! < endInclusive
      override fun next() : MyDate {
        current = current?.nextDay() ?: start
        return current!!
      }
    }
  }
}
