package iv_properties

import util.TODO
import util.doc34
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class LazyPropertyUsingDelegates(initializer : () -> Int) {
  val lazyValue : Int by Delegate(initializer)
}

internal class Delegate<T>(private val initializer : () -> T) : ReadOnlyProperty<LazyPropertyUsingDelegates, T> {
  private var value : T? = null

  override fun getValue(thisRef : LazyPropertyUsingDelegates, property : KProperty<*>) : T {
    if (value == null) {
      value = initializer()
    }

    return value!!
  }
}

fun todoTask34() : Lazy<Int> = TODO("""
        Task 34.
        Read about delegated properties and make the property lazy by using delegates.
    """, documentation = doc34())
