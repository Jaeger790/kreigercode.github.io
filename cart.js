let label = document.getElementById("label");
let shopptingCart = document.getElementById("shopping-cart");

let cart = JSON.parse(localStorage.getItem("data")) || [];

let totalCartAmount = ()=>{
    let cartIcon = document.getElementById("cartAmount");
    cartIcon.innerHTML = cart.map((i) => i.item).reduce((x, y) => x + y, 0);

    console.log(cart.map((i) => i.item).reduce((x, y) => x + y, 0));

};

totalCartAmount();

let createShopItems = ()=> {
    if( cart.length !== 0){
        return (shopptingCart.innerHTML = cart.map((i)=> {
            let{id, item} = i;
            let search = shopItemData.find((x)=> x.id === id) || [];
            let {img, name, price} = search
            return `
            <div class="cart-item">
                <img width="150" height="150px" src=${img} alt="" />
                
                <div class="details">

                    <div class="titlePriceContainer">
                        <h4 class="titlePrice">
                            <p>${name}</p>
                            <p class="cartItemPrice">$ ${price}</p>
                        </h4>
                        <button class = "bi-file-x" onclick="removeItem(${id})"></button>
                    </div>
                    
                    <div class="cartButtons">
                        <i onclick="decrease(${id})" class="bi-dash-square"></i>
                        <i onclick="increase(${id})"class="bi-plus-square"></i>
                        <div id=${id} class="quantity">${item}</div>
                    </div>
                    
                    <h3>$ ${item * price}</h3>
                </div>
            </div>
            `;
        }).join(""));
    }
    else{
        shopptingCart.innerHTML = ``;
        label.innerHTML = `<h2>Cart is empty </h2>
        <a href="index.html">
            <button class="homeButton">Back to home</button>
        </a>`;
    };
};

createShopItems();

let increase = (id) =>{
    let currentItem = id;
    let search = cart.find((i)=> i.id === currentItem.id);

    if(search === undefined){
        cart.push({
        id: currentItem.id,
        item: 1,
    });
    }
    else{
        search.item += 1;
    }
    
    update(currentItem.id);
    createShopItems();
    localStorage.setItem("data", JSON.stringify(cart));
    
};

let decrease = (id) =>{
    let currentItem = id;
    let search = cart.find((i)=> i.id === currentItem.id);

    if(search === undefined) return;  
    else if(search.item === 0) return;
    else{
        search.item -= 1;
    }

    update(currentItem.id);
    cart = cart.filter((i)=> i.item !== 0);
    createShopItems();
    localStorage.setItem("data", JSON.stringify(cart));
};

let update = (id) =>{
    let search = cart.find((i)=> i.id === id);
    document.getElementById(id).innerHTML = search.item;
    totalCartAmount();
    totalCost();


};

let removeItem = (id)=> {
    let currentItem = id;
    cart = cart.filter((i)=> i.id !== currentItem.id);
    localStorage.setItem("data", JSON.stringify(cart));
    createShopItems();
    totalCartAmount();
}

let clearCart = ()=>{
    cart = [];
    createShopItems();
    totalCartAmount();
    localStorage.setItem("data", JSON.stringify(cart));
};

let totalCost = ()=>{
    if (cart.length !== 0){
        let total = cart.map((i)=>{
            let{item, id} = i;
            let search = shopItemData.find((x)=> x.id === id) || [];
            return item * search.price;
        }).reduce((x, y) => x + y, 0);
        
        label.innerHTML = `
        <h2>Total: $ ${total}</h2>
        <button class="checkout">Checkout</button>
        <button onclick="clearCart()" class="removeAll">Clear Cart</button>`;

    }else return;
};

totalCost();