
const bookButton = document.querySelector("#book");
const homeButton = document.querySelector("#homeButton");
const aboutButton = document.querySelector("#aboutButton");
const servicesButton = document.querySelector("#servicesButton");
const contactButton = document.querySelector("#contactButton");
const aboutSection = document.querySelector("#about");
const serviceSection = document.querySelector("#services");
const contactSection = document.querySelector("#footer");

//Hide navbar function
var hideScroll = window.pageYOffset;
window.onscroll = function(){
    let scrollPos = window.pageYOffset;
    if(hideScroll > scrollPos){
        navBar.style.transition = ".5s";
        navBar.style.top = "0";

    }else{
        navBar.style.top = "-100px";
    }
    hideScroll = scrollPos;
}

//scroll functions

bookButton.addEventListener("click",()=>{
    contactSection.scrollIntoView({behavior: "smooth"})
});
homeButton.addEventListener("click",()=>{
    window.scrollTo({top:0, behavior:"smooth"})
});
aboutButton.addEventListener("click",()=>{
    aboutSection.scrollIntoView({behavior:"smooth"})
});
servicesButton.addEventListener("click",()=>{
    serviceSection.scrollIntoView({behavior:"smooth"})
});
contactButton.addEventListener("click",()=>{
    contactSection.scrollIntoView({behavior: "smooth"})
});
