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
            toAppend += `
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${item}</h5>
                        <span class="minus"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Minus_font_awesome.svg/512px-Minus_font_awesome.svg.png" width="25" height="25"></span>
                        <span class="plus"><img src="https://www.ectopay.com/ctopayb2b/gbweb_new/assets/110912-font-awesome/png/add-square-button.png" width="25" height="25"></span>
                        <p class="quantity">Quantity: <span class="quantity-number">${pageContent[item]}</span></p>
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

function refreshPrices() {
    const basePrices =
        {"Raspberry Pi 3B": 48.85,
        "Raspberry Pi 3B+": 55.96,
        "Raspberry Pi 4B [4GB]": 76.99,
        "Arduino Uno R3": 18.0,
        "Raspberry Pi 3A+": 24.9,
        "Lenovo IdeaPad S145": 466.9,
        "Lenovo Legion Y540": 998.99,
        "ASUS ROG Strix G531GT": 999.9,
        "Apple MacBook Pro 16": 2999.9,
        "Gigabyte GeForce GTX 1660 Ti OC 6GB": 319.9,
        "GIGABYTE GeForce GTX 1050 Ti OC 4GB": 165.0,
        "GIGABYTE GeForce RTX 2060 SUPER AORUS 8GB": 519.9,
        "AMD Ryzen 5 3600 Hexa-Core 3.6GHz AM4 Processor": 209.9,
        "AMD Ryzen 7 3700x Octa-Core 3.6GHz AM4": 399.9
        };

    let cards = document.querySelectorAll(".card-body");

    for (let card of cards) {
        let cardTitle = card.childNodes[0].nextSibling;
        for (let item in basePrices) {
            try {
                if (item === cardTitle.innerText) {
                    let quantityText = cardTitle.parentNode.querySelectorAll(".quantity")[0].innerText;
                    let quantity = quantityText.split(" ")[1];
                    cardTitle.parentNode.querySelectorAll(".price")[0].innerText = `Price: ${Math.round((basePrices[item] * quantity) *100 ) /100}`;

                }
            } catch (e) {
                console.log(e);
            }
        }
    }
}

function minusButton() {
    let buttons = document.querySelectorAll(".minus");
    for (let button of buttons) {
        button.addEventListener("click", function () {
            let quantityText = button.parentNode.querySelectorAll(".quantity")[0].innerText;
            let quantityNum = quantityText.split(" ")[1];
            quantityNum--;
            if (quantityNum >= 0) {
                button.parentNode.querySelectorAll(".quantity")[0].innerText = `Quantity: ${quantityNum}`;
                refreshPrices();
                //TODO: implement delete
            }
        })
    }
}




function main() {
    let price = collectPrices();
    let pageContent = collectQuantities();
    clearPage();
    buildPage(pageContent);
    refreshPrices();
    printOutPrices(price);
    minusButton();
}
main();