import {post_fetch} from "./post_fetch.js";

export let orderHistory = {
    orderHistory : function () {
        const modalHistory = document.getElementById("history");
        const orderHistoryBtn = document.querySelector(".order-history");

        orderHistoryBtn.addEventListener("click", showHistory);

        function showHistory() {
            $('.modal').modal('show');
            modalHistory.setAttribute("class", "");
        }

        function hide() {
            $('.modal').modal('hide');
            modalHistory.setAttribute("class", "hide");
        }
    }
};