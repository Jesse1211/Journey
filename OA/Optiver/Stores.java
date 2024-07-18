package OA.Optiver;

// As part of your system you want to track the following:
//      number_of_stores: The number of stores is the amount of scouts with stores open, where an open store is any store who currently has a non-zero order size
//      number_of_orders: The total number of orders for all scouts, noting that an order with 100 quantity is still 1 order
//      number_of_different_beverages: The number of different beverages that you need to make
//      number_of_beverages: The total number of beverages that you need to make, seen as the total of all the orders
//      number_of_each_beverage: The number of beverages per beverage type across all stores

// You will constantly need to maintain that your orders abide to the limits being set, limits can be updated and are defined as:
//      numberOfStores: The total number of stores that can be open
//      perBeverageTotal: For each beverage, across all stores the total number of beverages that are required

// If an order would exceed the limit (when compared with a >) then the order should be ignored and you should print reject_order: <uniqueId>.
// If updating the limit would cause your limits to be exceeded, then you should close all stores, resetting all your orders.

// Note If no limits have been supplied then the limits should all be treated as 0. 
// Scouts will regularly come and update you with what beverages they need,
// each time they do this it will replace the previous order for the same type of beverage for that scout:
//      uniqueId: the unique identifier for this order
//      storeId: the unique identifier for the scouts corner stand
//      beverageName: the name of the beverage they're updating the order of
//      quantity: the amount of this beverage that they still require

// In addition to this, if a scout's store goes out of business they will tell you their storeId and that all their orders should be canceled, 
// alternatively, if the total number of beverages for a given scout goes to 0 then that store is also considered out of business.
// Note: If an order's size is zero then that order is no longer considered active and does not count towards number_of_orders.

// You will need to implement the following functions:

//      UpdateLimit(numberOfStores, perBeverageTotal)
//          Updates the limits of your business
//          If on doing so any of the limits are exceeded then you should close all stores
//      ProcessOrder(uniqueId, storeId, beverageName, quantity)
//          Updates the given stores order for a given beverage name
//          Prints reject_order: <uniqueId> in the case that it exceeds any of the limits
//      CloseStore(storeId)
//          Closes the given store removing any orders
//      PrintState()
//          Prints the current state in the format 
//              number_of_stores: <number_of_stores>, 
//              number_of_orders: <number_of_orders>, 
//              number_of_different_beverages: <number_of_different_beverages>, 
//              number_of_beverages: <number_of_beverages>
class Stores {

}
