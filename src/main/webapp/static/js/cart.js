export function cartInit() {
    function setLocalStorage(name, object) {
        localStorage.setItem(name, JSON.stringify(object));
        console.log(object);
    }

    function getLocalStorage(name) {
        return JSON.parse(localStorage.getItem(name));
    }

    function addItem() {
        let cart = getLocalStorage("cart");
        let name = this.id;
        cart.items[name].quantity++;
        postFetch("/api/cart", cart);
        setLocalStorage("cart", cart);
    }

    function sendInitialCart() {
        let cart = getLocalStorage("cart");
        postFetch("/api/cart", cart);
    }

    const buttons = document.querySelectorAll(".btn");
    let items = {};
    for (let button of buttons) {
        button.addEventListener("click", addItem);
        items[button.id] = {name : button.id, quantity : 0};
    }

    if (getLocalStorage("cart") == null) {
        setLocalStorage("cart", {items: items})
    }

    sendInitialCart();
}
    async function postFetch(url, data) {
        try {postData(url, data) // JSON-string from `response.json()` call
        } catch (error) {return console.error(error);}
        function postData(url = '', data = {}) {
            // Default options are marked with *
            const response = fetch(url, {
                method: 'POST', // *GET, POST, PUT, DELETE, etc.
                mode: 'cors', // no-cors, *cors, same-origin
                cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
                credentials: 'same-origin', // include, *same-origin, omit
                headers: {
                    'Content-Type': 'application/json'
                    // 'Content-Type': 'application/x-www-form-urlencoded',
                },
                redirect: 'follow', // manual, *follow, error
                referrer: 'no-referrer', // no-referrer, *client
                body: JSON.stringify(data) // body data type must match "Content-Type" header
            });
             // parses JSON response into native JavaScript objects
        }}
