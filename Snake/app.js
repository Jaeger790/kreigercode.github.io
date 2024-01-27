//Define HTML elements
const board = document.getElementById('gameBoard');
const instructionText = document.getElementById('instructionText');
const logo = document.getElementById('logo');
const score = document.getElementById('score');
const highScoreText = document.getElementById('highScore');


//Define game variables
const gameBoardSize = 20;
let snake = [{x:10,y:10}];
let food = createFoodObject();
let highScore = 0;
let direction = 'right';
let gameInterval;
let gameSpeedDelay = 200;
let gameStarted = false;


//Draw map, snake, and food
function draw(){
    board.innerHTML = '';
    visualizeSnake();
    visualizeFood();
    updateScore();

}

//draw snake
function visualizeSnake(){
    snake.forEach((segment)=>{
        const snakeObject = createGameObject('div','snake');
        setPosition(snakeObject, segment);
        board.appendChild(snakeObject);
        //upgrade snake color when a new high-score is met
        
        if (highScore >= 10 && highScore <20) {
            snakeObject.style.backgroundColor = "red";
            snakeObject.style.borderColor = "orange";
        }else if (highScore >= 20){
            snakeObject.style.backgroundColor = "black";                snakeObject.style.borderColor = "brown";
        }
        
        
    });
}

//Create snake and snake food
function createGameObject(tag, className){
    const element = document.createElement(tag);
    element.className = className;
    return element;
}

//Set the position of the snake and food
function setPosition(element, position){
    element.style.gridColumn = position.x;
    element.style.gridRow = position.y;
}

//Test draw function
//draw();

//Create food on gameBoard
function visualizeFood(){
    if(gameStarted) {
        const foodObject = createGameObject('div','food');
        setPosition(foodObject, food);
        board.appendChild(foodObject);

    }
}

//Create food Object
function createFoodObject(){
    const x = Math.floor(Math.random() * gameBoardSize) + +1;
    const y = Math.floor(Math.random() * gameBoardSize) + +1;
    return {x, y};
}

//Create game controls
function controls(){
    const head = {...snake[0]};
    switch (direction){
        case 'up':
            head.y--;
            break;
        case 'down':
            head.y++;
            break;
        case 'left':
            head.x--;
            break;
        case 'right':
            head.x++;
            break;
    }
    snake.unshift(head);

    if(head.x === food.x && head.y === food.y){
        food = createFoodObject();
        increaseSpeed();
        clearInterval(gameInterval);
        gameInterval = setInterval(() =>{
            controls();
            checkCollision();
            draw();

        }, gameSpeedDelay);
    } else {
        snake.pop();
    }
}

//Start the game.
function startGame(){
    gameStarted = true;
    instructionText.style.display = 'none';
    logo.style.display = 'none';
    gameInterval = setInterval(()=> {
        controls();
        checkCollision();
        draw();
    }, gameSpeedDelay);
}

//Handle input
function handleInput(event){
    if(
        (!gameStarted && event.code === 'Space') ||
        (!gameStarted && event.key === ' ')
    ){
        startGame();
    }else{
        switch(event.key){
            case 'ArrowUp':
                direction = 'up';
                break;
            case 'ArrowDown':
                direction = 'down';
                break;
            case 'ArrowLeft':
                direction = 'left';
                break;
            case 'ArrowRight':
                direction = 'right';
                break;
        }
    }
}
//Add event listener to detect when key is pressed. Then call the handleInput function to process the input
document.addEventListener('keydown',handleInput);

//decrease the game speed delay by varying amounts depending on the current speed of the game. speed will increase more slowly the faster the speed is.
function increaseSpeed(){
    if(gameSpeedDelay > 150){
        gameSpeedDelay -=5;
    }else if (gameSpeedDelay > 100){
        gameSpeedDelay -= 3;
    }else if (gameSpeedDelay > 50){
        gameSpeedDelay -= 1;
    }
}

//check collision
function checkCollision(){
    const head = snake[0];
    if(head.x < 1 || head.x > gameBoardSize || head.y < 1 || head.y > gameBoardSize){
        resetGame();
    }

    for(let i =1; i < snake.length; i++){
        if(head.x === snake[i].x && head.y === snake[i].y){
            resetGame();
        }
    }
}

//reset the game
function resetGame(){
    updateHighScore();
    stopGame();
    snake = [{x:10, y:10}];
    food = createFoodObject();
    direction = 'left';
    gameSpeedDelay = 200;
    updateScore();
}

//update the score for every piece of food the snake eats
function updateScore(){
    const currentScore = snake.length - 1;
    score.textContent = currentScore.toString().padStart(3,'0');
}

//stop the game
function stopGame(){
    clearInterval(gameInterval);
    gameStarted = false;
    instructionText.style.display = 'block';
    logo.style.display = 'block';
}

//update the high-score 
function updateHighScore(){
    const currentScore = snake.length -1 ;
    if(currentScore > highScore){
        highScore = currentScore;
        highScoreText.textContent = highScore.toString().padStart(3,'0');
    }


}






