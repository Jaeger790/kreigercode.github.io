function showTechBlog(){
    fadeOutMoto();
    fadeIn();
    //document.getElementById('motoLog').style.display = 'none';
}

function showMotoLog(){
    fadeOut();
    fadeInMoto();
    //document.getElementById('techBlog').style.display= 'none';
}
function showAll(){
    fadeIn();
    fadeInMoto();
}

function fadeOut(){
    document.getElementById('techBlog').style.transition = "all .5s";
    document.getElementById('techBlog').style.transform = "scale(0)";
    document.getElementById('techBlog').style.display = 'none';
}
function fadeIn(){
    
    document.getElementById('techBlog').style.transition = "all .5s";
    document.getElementById('techBlog').style.transform = "scale(1)";
   document.getElementById('techBlog').style.display = 'flex';
    
}

function fadeInMoto(){
    document.getElementById('motoLog').style.transition = "all .5s";
    document.getElementById('motoLog').style.transform = "scale(1)";
   document.getElementById('motoLog').style.display = 'flex';
}

function fadeOutMoto(){
    document.getElementById('motoLog').style.transition = "all .5s";
    document.getElementById('motoLog').style.transform = "scale(0)";
    document.getElementById('motoLog').style.display = 'none';
}

