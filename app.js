// triangle shape for profile header section
const myShape = document.getElementById("colorBack");
const ctx = myShape.getContext("2d");


ctx.fillStyle= 'darkblue';
ctx.moveTo(0,10);
ctx.lineTo(1920,10);
ctx.lineTo(450,900);
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