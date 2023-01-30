
window.onload = () =>{
    observeSkillSection()
};
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




/*
    Experience bar functions for programming languages
*/

const innerLevelBars = document.getElementsByClassName("langBarInner");
const javaInnerBar = document.getElementById("javaInnerBar");
let javaLevelText = document.getElementById("javaLevelValue");

const cSharpInnerBar = document.getElementById("cSharpInnerBar");
const cPlusInnerBar = document.getElementById("cPlusInnerBar");
const jsInnerBar = document.getElementById("jsInnerBar");
const pythonInnerBar = document.getElementById("pythonInnerBar");
const skillSection = document.getElementById("skillSection");
const langLevelContainer = document.getElementsByClassName("langLevelContainer");
const langContainerSelector = document.querySelector('#langLevel')
const skillS = document.querySelector("#skillSection");
const langHead = document.getElementById("langHead");
let position = 0;

javaInnerBar.style.left = "0";
langHead.style.opacity = "0";


function animateFadeIn(){
    langHead.style.transition = " 2s";
    setTimeout(()=>(langHead.style.opacity ++),300);
    langHead.style.transform = 'translateX(5vw)';

    for (let i = 0; i < langLevelContainer.length; i++) {
        langLevelContainer[i].style.transition = " 2s";
        setTimeout(()=>(langLevelContainer[i].style.opacity ++),600);
        langLevelContainer[i].style.transform = "translateX(10vw)";    
    }
    
    for(i=0;i<innerLevelBars.length;i++){
        innerLevelBars[i].style.transition = "all 3s";
        setTimeout(()=> (javaInnerBar.style.width = "95%",200));
        setTimeout(()=> (cSharpInnerBar.style.width = "80%",200));
        setTimeout(()=> (cPlusInnerBar.style.width = "70%",200));
        setTimeout(()=> (jsInnerBar.style.width = "77%",200));
        setTimeout(()=> (pythonInnerBar.style.width = "84%",200));
    }

    

}



function animateFadeOut(){
    for (let i = 0; i < langLevelContainer.length; i++) {
        langLevelContainer[i].style.transition = "all 2s";
        langLevelContainer[i].style.opacity --;
        langLevelContainer[i].style.transform = "translateX(-150px)";
    }
    langHead.style.transition = " 2s";
    setTimeout(()=>(langHead.style.opacity --));
    langHead.style.transform = 'translateX(-10vw)';
}

function fadeInSkillSection(){
    var observer = new IntersectionObserver(function(entries) {
        if(entries[0].isIntersecting === true){
            animateFadeIn();
        }
          
    }, { threshold: [0] });
    observer.observe(langHead);
    
}

function fadeOutSkillSection(){
    var observer = new IntersectionObserver(function(entries) {
        if(entries[0].isIntersecting === false){
            animateFadeOut();
        }
          
    }, { threshold: [0] });
    observer.observe(langHead);
    return true;
    
}

fadeInSkillSection();

    




