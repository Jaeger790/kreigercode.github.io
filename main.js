let shop = document.getElementById("shop");



let cart = JSON.parse(localStorage.getItem("data")) || [];

let createShop = ()=>{
    return (shop.innerHTML = shopItemData.map((i)=>{
        
        let {id, name, price, description, img} = i;
        let search = cart.find ((x)=>x.id === id) || [];
        return `<div id=product-id-${id} class="item">
            <img width="295" src=${img} alt="">
            <div class="details">
                <h3>${name}</h3>
                <p>${description}</p>
                <div class="priceQuantity">
                <h2>$ ${price}</h2>
                <div class="buttons">
                    <i onclick="decrease(${id})" class="bi bi-dash-square"></i>
                    <i onclick="increase(${id})"class="bi bi-plus-square"></i>
                    <div id=${id} class="quantity">${search.item === undefined? 0: search.item}</div>
                    
                </div>
            </div>
        </div>
    </div>`
    }).join(""));
};

createShop();

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
    
    localStorage.setItem("data", JSON.stringify(cart));
};

let update = (id) =>{
    let search = cart.find((i)=> i.id === id);
    document.getElementById(id).innerHTML = search.item;
    totalCartAmount();


};

let totalCartAmount = ()=>{
    let cartIcon = document.getElementById("cartAmount");
    cartIcon.innerHTML = cart.map((i) => i.item).reduce((x, y) => x + y, 0);

    console.log(cart.map((i) => i.item).reduce((x, y) => x + y, 0));

};

totalCartAmount();