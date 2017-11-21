package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product : Product) : Set<Customer> = customers.filter {
  it.orderedProducts.contains(product)
}.toSet()

fun Customer.getMostExpensiveDeliveredProduct() : Product? = orders.filter { it.isDelivered }.flatMap { it.products }.maxBy { it.price }

fun Shop.getNumberOfTimesProductWasOrdered(product : Product) : Int = customers.sumBy {
  it.orders.sumBy { it.products.count { it == product } }
}
