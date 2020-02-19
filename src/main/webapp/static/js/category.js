function setSessionStorage(name, object) {
    console.log(object);
    sessionStorage.setItem(name, JSON.stringify(object));
}

function getSessionStorage(name) {
    return JSON.parse(sessionStorage.getItem(name));
}

export function sortCategories() {
    const selectedCategories = new Set();
    const categories = document.querySelectorAll(".category-name");
    for (let category of categories) {
        category.addEventListener('click', function () {
        const cards = document.querySelectorAll("[data-label='card']");
            if (!selectedCategories.has(category.innerHTML)) {
                selectedCategories.add(category.innerHTML);
                if (category.innerHTML !== "All") category.style.backgroundColor = "green";
                setAttributes(cards, selectedCategories);
            } else {
                selectedCategories.delete(category.innerHTML);
                category.style.backgroundColor = null;
                setAttributes(cards, selectedCategories)
            }
            if (selectedCategories.has("All")) {
                categories.forEach(element => element.style.backgroundColor = null);
                showAllCards();
                selectedCategories.clear();
            }
            if (selectedCategories.size === 0) showAllCards();
        })
    }
}

function setAttributes(elements, storage) {
    for (let element of elements) {
        if (!storage.has(element.firstElementChild.innerHTML)) {
            element.setAttribute("class", "hidden-card")
        } else {
            element.setAttribute("class", "card");
        }
    }
}

function showAllCards() {
    const hiddenCards = document.querySelectorAll(".hidden-card");
    for (let hiddenCard of hiddenCards) {
        hiddenCard.setAttribute("class", "card")
    }
}
