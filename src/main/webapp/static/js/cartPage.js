function collectPrices() {
    let prices = document.querySelectorAll(".unit_price");
    let total = 0;
    for (let value of prices) {
        total+= parseInt(value.innerText);
    }
    return total;
}


function collectQuantities() {
    let items = {"Raspberry Pi 3B": 0,
                "Raspberry Pi 3B+": 0,
                "Raspberry Pi 4B [4GB]": 0,
                "Arduino Uno R3": 0,
                "Raspberry Pi 3A+": 0,
                "Lenovo IdeaPad S145": 0,
                "Lenovo Legion Y540": 0,
                "ASUS ROG Strix G531GT": 0,
                "Apple MacBook Pro 16": 0,
                "Gigabyte GeForce GTX 1660 Ti OC 6GB": 0,
                "GIGABYTE GeForce GTX 1050 Ti OC 4GB": 0,
                "GIGABYTE GeForce RTX 2060 SUPER AORUS 8GB": 0,
                "AMD Ryzen 5 3600 Hexa-Core 3.6GHz AM4 Processor": 0,
                "AMD Ryzen 7 3700x Octa-Core 3.6GHz AM4": 0};
    /*
    for (let item in items) {
        console.log(item + items[item]);
    }
     */

    let titles = document.querySelectorAll(".card-title");
    for (let title of titles) {
        for (let item in items) {
            if (item === title.innerText) {
                items[item] += 1;
            }
        }
    }
    return items;
}



function printOutPrices(price) {
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
    let pageContent = collectQuantities();
    let price = collectPrices();
    printOutPrices(price);
}
main();