function setSessionStorage(name, object) {
    console.log(object);
    sessionStorage.setItem(name, JSON.stringify(object));
}

function getSessionStorage(name) {
    return JSON.parse(sessionStorage.getItem(name));
}

function sortCategories() {
    const selectedCategories = new Set();
    const categories = document.querySelectorAll(".category");
    for (let category of categories) {
        category.addEventListener('click', function () {
            selectedCategories.add(category.firstElementChild.innerHTML);
            console.log(selectedCategories);
            const cards = document.querySelectorAll("[data-label='category']");
            for (let card of cards) {
                if (!selectedCategories.has(card.firstElementChild.innerHTML)) {
                    card.setAttribute("class", "hidden-card")
                } else {
                    card.setAttribute("class", "card");
                }
            }
            if (selectedCategories.has("All")) {
                showAllCards(selectedCategories);
                selectedCategories.clear();
            }
        })
    }
}


function showAllCards(cardsSet) {
    const hiddenCards = document.querySelectorAll(".hidden-card");
    for (let hiddenCard of hiddenCards) {
        hiddenCard.setAttribute("class", "card")
    }
    cardsSet.clear();
}


sortCategories();