
window.onload = () =>{
    observeSkillSection()
};
const navBar = document.getElementById("navBar");
const navLL = document.getElementById("navLinkList");
const menuIcon = document.getElementById("menuIcon");
const downloadIcon = document.getElementById("downloadIcon");
const resume = document.getElementById("resumeButton");
const blogButton = document.getElementById("blogButton");






//change color of download icon when the mouse enters and leaves the button area
// resume.style.borderRadius = "15px";
// resume.addEventListener("mouseover", ()=>{
//     downloadIcon.style.color="#fff";
//     resume.style.background = "linear-gradient(to bottom, #fff 0%, #000 100%)"});
// resume.addEventListener("mouseout",()=>{
//     downloadIcon.style.color = "#000";
//     resume.style.background = "#fff";
// });
// resume.addEventListener("mousedown",()=>{
//    resume.style.background = "linear-gradient(to top, #fff 0%, #000 100%)";    
// });
// resume.addEventListener("mouseup", ()=>{
//     resume.style.background = "linear-gradient(to bottom, #fff 0%, #000 100%)"; 
// })








//show navigation links when menu button is selected
navLL.style.display = "none";

function expandNavLinks(){
    menuIcon.addEventListener("click",()=>{
        if(navLL.style.display != "block"){
            navLL.style.display = "block";
            navBar.style.height = "fit-content";
            menuIcon.style.color= "black";
            menuIcon.style.backgroundColor = "white";
            menuIcon.style.boxShadow = "rgba(255, 255, 255, 0.3) 2px 2px 5px";
            menuIcon.style.border = "1px solid rgba(0, 0, 0, 1)";
            
        }
        collapseNavLinks();
    });

    
}
function collapseNavLinks(){
    menuIcon.addEventListener("click",()=>{
        if(navLL.style.display != "none"){
            navLL.style.display = "none";
            navBar.style.height = "fit-content";
            navLL.style.transition = "all 1s";
            navBar.style.transition = "all 1s";
            menuIcon.style.backgroundColor = "#000";
            menuIcon.style.boxShadow = "rgba(255, 255, 255, 0.3) 2px 2px 5px";
            menuIcon.style.border = "1px solid rgba(0, 0, 0, 1)";
            
        }
        expandNavLinks();
    });
    
}
function collapseOnScroll(){
    if(navLL.style.display != "none"){
        navLL.style.display = "none";
        navBar.style.height = "fit-content";
        menuIcon.style.backgroundColor = "#000";
            menuIcon.style.boxShadow = "rgba(255, 255, 255, 0.3) 2px 2px 5px";
            menuIcon.style.border = "1px solid rgba(0, 0, 0, 1)";
    }
    expandNavLinks();
}

function blogClick(target) {
    location.href='BlogPage\\blog.html';
};

function snakeClick(target){
    location.href="Snake\\snake.html";
}
expandNavLinks();




//scroll functions
function scrollToTop(){
    window.scrollTo({top:0, behavior:"smooth"});
    collapseOnScroll();


}
function scrollAbout(){
    document.querySelector('#about').scrollIntoView({behavior:"smooth"});
    collapseOnScroll();

}
function scrollTemplate(){
    document.querySelector('#template').scrollIntoView({behavior:"smooth"});
    collapseOnScroll();

}
function scrollSkills(){
    document.querySelector('#skillSection').scrollIntoView({behavior:"smooth"});
    collapseOnScroll();


}



/*
    Experience bar functions for programming languages
*/

const innerLevelBars = document.getElementsByClassName("langBarInner");
const javaInnerBar = document.getElementById("javaInnerBar");
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
        setTimeout(()=> (javaInnerBar.style.width = "98%",200));
        setTimeout(()=> (cSharpInnerBar.style.width = "92%",200));
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
    return true;
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



// div level fade in for the rest of the skills section

const languagesClass = document.querySelectorAll(".languages");
const techSection = document.getElementById("techSection")

languagesClass.forEach(languagesClass =>{
    languagesClass.style.opacity = "0";
});




function techSkillFI(){
    
    var observer = new IntersectionObserver(function(entries) {
        if(entries[0].isIntersecting === true){
            setTimeout(()=>{
                languagesClass[0].style.transition = "4s";
                languagesClass[0].style.opacity ++;
                languagesClass[0].style.transform = 'translateX(5vw)';

            },200);
        }
          
    }, { threshold: [0] });
    observer.observe(languagesClass[0]);
    return true;
}

function skillFI(){
    var observer = new IntersectionObserver(function(entries) {
        if(entries[0].isIntersecting === true){
            setTimeout(()=>{
                languagesClass[1].style.transition = "4s";
                languagesClass[1].style.opacity ++;
                languagesClass[1].style.transform = 'translateX(5vw)';

            },600)
        }
          
    }, { threshold: [0] });
    observer.observe(languagesClass[1]);
    return true;
}

techSkillFI();
skillFI();



