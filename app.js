// triangle shape for profile header section
const myShape = document.getElementById("colorBack");
const ctx = myShape.getContext("2d");


ctx.fillStyle= '#6a859b';
ctx.moveTo(5,10);
ctx.lineTo(1800,10);
ctx.lineTo(1350, 400);
ctx.lineTo(1800,900);
ctx.fill();



//Hide navbar function
var hideScroll = window.pageYOffset;
window.onscroll = function(){
    let scrollPos = window.pageYOffset;
    if(hideScroll > scrollPos){
        document.getElementById("navBar").style.top = "0";
    }else{
        document.getElementById("navBar").style.top = "-100px";
    }
    hideScroll = scrollPos;
}

//scroll functions
function scrollToTop(){
    window.scrollTo({top:0, behavior:"smooth"})

}

function scrollAbout(){
    document.querySelector('#about').scrollIntoView({behavior:"smooth"})
}

function scrollTemplate(){
    document.querySelector('#template').scrollIntoView({behavior:"smooth"})
}