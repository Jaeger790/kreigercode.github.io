let techBlog = document.getElementById('techBlog');
let motoLog = document.getElementById("motoLog");



function showTechBlog(){
    fadeOutMoto();
    setTimeout(()=>fadeIn(), 400);
    
}

function showMotoLog(){
    fadeOut();
    setTimeout(()=>fadeInMoto(), 400);
    
}
function showAll(){
    fadeIn();
    fadeInMoto();
}

function fadeOut(){
    techBlog.style.transition = "all .2s";
    
    techBlog.style.transform = "scale(0)";
    setTimeout(()=>techBlog.style.display = 'none', 200);
}
function fadeIn(){
    
    if(techBlog.style.display = "none"){
        techBlog.style.display = 'flex';
        
        techBlog.style.transition = "all .2s";
        setTimeout(()=>techBlog.style.transform = "scale(1)", 1);
        
        
    }

    
    
    
}

function fadeInMoto(){

    if(motoLog.style.display = "none"){
        motoLog.style.display = 'flex';
        motoLog.style.transition = "all .2s";
        setTimeout(()=>motoLog.style.transform = "scale(1)", 1);
    }
        
    
}

function fadeOutMoto(){
    motoLog.style.transition = "all .2s";
    motoLog.style.transform = "scale(0)";
    setTimeout(()=> motoLog.style.display = 'none', 200);
}



