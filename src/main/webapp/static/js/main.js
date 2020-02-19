import { cartInit } from "./cart.js";
import { sortCategories } from "./category.js";
import { userManager } from "./user_manager.js"

window.onload = function () {
    cartInit();
    sortCategories();
    userManager.main();
};