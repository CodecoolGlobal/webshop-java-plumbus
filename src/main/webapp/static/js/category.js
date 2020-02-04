function sortCategories() {
    const categories = document.querySelectorAll(".category");
    for(let category of categories) {
        category.addEventListener('click', function () {
            fetch_it("/category", category.innerText);
        })
    }
}
sortCategories();
