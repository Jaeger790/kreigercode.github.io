


let phoneValidateMessage = document.getElementById("phone")
phoneValidateMessage.oninvalid = function(event){
    event.target.setCustomValidity("Phone number should match format: 123-456-789")
};

/* 
    ! Animation funtions
*/

function hoverSectionOut(obj){
    obj.style.color = "black";
    obj.style.border = ""
}

function hoverSection(obj){
   
    obj.style.color = "green";
    obj.style.border = "5px solid blue"
}

function phoneFormat(obj){
    obj.getElementById("")
}


/* 
    ! Navbar funtions
*/

function coll(){
  document.getElementById("navBar").style.display = "none";
  document.getElementById("showNav").style.display = "block";
 
  //console.log("hide")
}

function show(){
    document.getElementById("navBar").style.display = "block";
    document.getElementById("showNav").style.display = "none";
    document.getElementById("collapseNavButton").style.float = "right";
   //console.log("show")
}


/* 
    ! Scroll funtions
*/

function scrollToTop(){
    //document.querySelector("#top").scrollIntoView({behavior:"smooth"});
    window.scrollTo({top:0, behavior: "smooth"});
}

function scrollBottom(){
    document.querySelector("#contact").scrollIntoView({behavior:"smooth"});
    
}

function scrollAbout(){
    document.querySelector("#about").scrollIntoView({behavior:"smooth"});
}


