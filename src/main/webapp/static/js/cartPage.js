function collectPrices() {
    let prices = document.querySelectorAll(".unit_price");
    let total = 0;
    for (let value of prices) {
        total+= parseInt(value.innerText);
    }
    return total;
}

function buildPage(price) {
    let totalCard = `
            <div class="card js_total">
                <div class="card-body">
                    <p class="card-text price">Total Price: ${price} USD</p>            
                </div>
            </div>
    `;
    let appendTo = document.querySelector('#last_element');
    let nodeCard = document.createRange().createContextualFragment(totalCard);
    appendTo.appendChild(nodeCard);
}



function main() {
    let price = collectPrices();
    buildPage(price);
}
main();