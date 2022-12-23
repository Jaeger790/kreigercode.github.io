const descriptionBubble1 = document.querySelector("#desc1");
const descriptionBubble2 = document.querySelector("#desc2");
const bubbleArrow = document.querySelector(".bubbleArrow");
const bubbleArrow2 = document.querySelector(".bubbleArrow2");

let bubbleHeight1 = descriptionBubble1.style.height = "10vh";
let bubbleHeight2 = descriptionBubble2.style.height = "10vh";




//expand collapse description bubble 1
descriptionBubble1.addEventListener("click",()=>{
    console.log("Click");
    
    let bubbleShow1 = descriptionBubble1.style.height = "10vh";

    if(bubbleHeight1 == bubbleShow1){
        bubbleArrow.style.transition = ".3s";
        bubbleArrow.style.transform = "rotate(180deg)";
        bubbleArrow.style.float = "right";
        
        let bubbleHide1 = descriptionBubble1.style.height = "auto";
        bubbleHeight1 = bubbleHide1;
        descriptionBubble1.scrollIntoView({behavior: "smooth"});

    }else{
        bubbleHeight1 = bubbleShow1;
        bubbleArrow.style.transform = "rotate(0deg)";
    }
});

// expand collapse description bubble 2
descriptionBubble2.addEventListener("click",()=>{

    let bubbleShow2 = descriptionBubble2.style.height = "10vh";
    bubbleArrow2.style.transition = ".3s";
    bubbleArrow2.style.transform = "rotate(180deg)";
    bubbleArrow2.style.float = "right";
    if(bubbleHeight2 == bubbleShow2){
        
        let bubbleHide2 = descriptionBubble2.style.height = "auto";
        bubbleHeight2 = bubbleHide2;
        descriptionBubble2.scrollIntoView({behavior: "smooth"});
    }else{
       bubbleHeight2 = bubbleShow2;
       bubbleArrow2.style.transform = "rotate(0deg)";
       
       
     }
 });

 //change background color of description bubble 1 when hovered over
 descriptionBubble1.addEventListener("mouseenter",()=>{
    descriptionBubble1.style.transition = "1s";
    descriptionBubble1.style.background = "rgba(51, 61, 41,.4)";
    descriptionBubble1.style.boxShadow = "10px 20px 30px rgb(64, 67, 40)";
    
    
    
 });
//change background color back to original color when mouse leaves div
 descriptionBubble1.addEventListener("mouseleave",()=>{
    descriptionBubble1.style.transition = "1s";
    descriptionBubble1.style.background = "rgba(194, 197, 170,0)";
    descriptionBubble1.style.boxShadow = "none";
    
    
 });

 //change background color of description bubble 2 when hovered over
 descriptionBubble2.addEventListener("mouseenter",()=>{
    descriptionBubble2.style.transition = "1s";
    descriptionBubble2.style.background = "rgba(51, 61, 41,.4)";
    descriptionBubble1.style.boxShadow = "10px 20px 30px rgb(64, 67, 40)";
    
    
    
 });
 //change background color back to original color when mouse leaves div
 descriptionBubble2.addEventListener("mouseleave",()=>{
    descriptionBubble2.style.transition = "1s";
    descriptionBubble2.style.background = "rgba(194, 197, 170,0)";
    descriptionBubble1.style.boxShadow = "none";
    
 });