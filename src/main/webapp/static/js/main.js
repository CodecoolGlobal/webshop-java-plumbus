import { cartInit } from "./cart.js";
import { sortCategories } from "./category.js";
import { userManager } from "./user_manager.js";
import { orderHistory } from "./order_history.js";

window.onload = function () {
    cartInit();
    sortCategories();
    userManager.main();
    orderHistory.orderHistory();
};