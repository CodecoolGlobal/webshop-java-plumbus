function collectPrices() {
    let prices = document.querySelectorAll(".unit_price");
    let total = 0;
    for (let value of prices) {
        total+= parseInt(value.innerText);
    }

    return total;
}
function getCartContent() {
    fetch('/api/get_cart').then(response => response.json())
        .then(response => console.log(response));
}

function buildPage(cartContent) {
    const toAppend = document.querySelector(".cart_header");
    console.log(cartContent[0].name);
    let items = "";
    for (let item of cartContent) {
        items += `<div class="card">
            <div class="card-body">
                <h5 class="card-title">${item.name}</h5>
                <p class="card-text price">Price: <span class="unit_price" ${item.price}></span></p>
                <p class="card-text"><small class="text-muted"><span ${item.quantity}></span></small></p>
            </div>
        </div>`;
    }
    let nodeItems = document.createRange().createContextualFragment(items);
    toAppend.appendChild(nodeItems);
}

function showTotal(price) {
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
    getCartContent();
    let price = collectPrices();
    showTotal(price);
}

main();