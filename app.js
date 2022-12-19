const navBar = document.getElementById("navBar");
const downloadIcon = document.getElementById("downloadIcon");
const resume = document.getElementById("resumeLink");

resume.style.borderRadius = "15px";


//change color of download icon when the mouse enters and leaves the button area
resume.addEventListener("mouseover", ()=>{
    downloadIcon.style.color="#fff";
    resume.style.background = "linear-gradient(to bottom, #fff 0%, #000 100%)"});
resume.addEventListener("mouseout",()=>{
    downloadIcon.style.color = "#000";
    resume.style.background = "#fff";
});
resume.addEventListener("mousedown",()=>{
   resume.style.background = "linear-gradient(to top, #fff 0%, #000 100%)";    
});
resume.addEventListener("mouseup", ()=>{
    resume.style.background = "linear-gradient(to bottom, #fff 0%, #000 100%)"; 
})



//Hide navbar function
var hideScroll = window.pageYOffset;
window.onscroll = function(){
    let scrollPos = window.pageYOffset;
    if(hideScroll > scrollPos){
        navBar.style.transition = "1s";
        navBar.style.top = "0";

    }else{
        navBar.style.top = "-100px";
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

