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

function clearPage() {
    let cards = document.querySelectorAll(".iterator");
    for (let card of cards) {
        card.remove();
    }
}

function buildPage(pageContent) {
    let toAppend = "";
    for (let item in pageContent) {
        if (pageContent[item] !== 0) {
            console.log(item);
            toAppend += `
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${item}</h5>
                        <p class="card-text price">Price: 69.420</p>
                        <p class="card-text"><small class="text-muted">Electronics Inc</small></p>
                    </div>
                </div>
            `;
        }
    }
    let element = document.querySelector(".container");
    let node = document.createRange().createContextualFragment(toAppend);
    element.appendChild(node);
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
    let price = collectPrices();
    let pageContent = collectQuantities();
    buildPage(pageContent);
    printOutPrices(price);
    clearPage();
}
main();