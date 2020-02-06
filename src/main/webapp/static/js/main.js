import { cartInit } from "./cart.js";
import { sortCategories } from "./category.js";

window.onload = function () {
    cartInit();
    sortCategories();
};