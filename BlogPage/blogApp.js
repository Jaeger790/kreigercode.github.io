//blogs by content type
let techBlog = document.getElementsByClassName("tList");
let motoLog = document.getElementsByClassName("mList");
//arrays for blog elements
let techBlogElements = Array.from(techBlog);
let motoBlogElements = Array.from(motoLog);
//top nav-bar buttons
let showMotoB = document.getElementById("showMoto");
let showTechB = document.getElementById("showTech");
let showAllB = document.getElementById("showAll");
//hide moto blog items and show tech blog items
function showTechBlog() {
    fadeOutMoto();
    setTimeout(() => fadeInTech(), 400);
}
//show moto blog items and hide tech blog items
function showMoto() {
    fadeOutTech();
    setTimeout(() => fadeInMoto(), 400);
}
//show all blog items
function showAll() {
    fadeInTech();
    fadeInMoto();
}
/* iterate through an array of motoBlog div elements and add animation to hide them. */
function fadeOutMoto() {
    for (let i = 0; i < motoBlogElements.length; i++) {
        motoLog[i].style.transition = "all .2s";
        motoLog[i].style.transform = "scale(0)";
        setTimeout(() => (motoLog[i].style.display = "none"), 200);
    }
}
/* iterate through an array of motoBlog div elements and add animation to fade them into view. */
function fadeInMoto() {
    for (let i = 0; i < motoBlogElements.length; i++) {
        if ((motoLog[i].style.display = "none")) {
            motoLog[i].style.display = "flex";
            motoLog[i].style.transition = "all .2s";
            setTimeout(() => (motoLog[i].style.transform = "scale(1)"), 1);
        }
    }
}
/* iterate through an array of techBlog div elements and add animation to fade them out of view. */
function fadeOutTech() {
    for (let i = 0; i < techBlogElements.length; i++) {
        techBlog[i].style.transition = "all .2s";
        techBlog[i].style.transform = "scale(0)";
        setTimeout(() => (techBlog[i].style.display = "none"), 200);
    }
}
/* iterate through an array of techBlog div elements and add animation to fade them in view. */
function fadeInTech() {
    for (let i = 0; i < techBlogElements.length; i++) {
        if ((techBlog[i].style.display = "none")) {
            techBlog[i].style.display = "flex";

            techBlog[i].style.transition = "all .2s";
            setTimeout(() => (techBlog[i].style.transform = "scale(1)"), 1);
        }
    }
}
//add event listeners on the top nav buttons to call blog filtering methods
showMotoB.addEventListener("click", showMoto);
showTechB.addEventListener("click", showTechBlog);
showAllB.addEventListener("click", showAll);
