int points =0; //points int
int speed = 15; //speed of the character
int value = 1;
int stop = 120;
int index;


//movement for character
int moveX = 250;
int moveY= 440;
int moveW= 50;
int moveH= 50;
PShape character;  // The PShape object

//image initiliasation ............
PImage[] food = new PImage[8];
PImage[] bfood = new PImage[7];
PImage bgfood;
PImage photo;
PImage back;

// intialisation for falling objects
Falling[] b = new Falling[15]; 
Badfood[] bf = new Badfood[15];


//int for bounicng balls in background
int numBalls = 60;
float spring = 0.05;
float gravity = 0.03;
float friction = -0.9;
Ball[] balls = new Ball[numBalls];

//timer
int t;


//audio intialisation .................////////......
import processing.sound.*;
    
    SoundFile file;
    //put your audio file name here
    String audioName = "audio/ss1.wav";
    String path;

    //runs once when the app first starts

void setup(){
  size (500,500);
  character = createShape(ELLIPSE,0,0,moveW,moveH);

  //code for background 
  for (int i = 0; i < numBalls; i++) {
    balls[i] = new Ball(random(width), random(height), random(30, 70), i, balls);
  }
 
  
  for (int i=0; i< b.length; i++) {
     b[i] = new Falling();}// code for falling objects
       for (int i=0; i< bf.length; i++) {
     bf[i] = new Badfood();}// code for falling objects
     
     //code for picture
          for ( int i = 0; i< food.length; i++ ) {
       food[i] = loadImage("images/fw"+i+".png" );   // make sure images "0.jpg" to "11.jpg" exist
}
for ( int i = 0; i< bfood.length; i++ ) {
       bfood[i] = loadImage("images/bfw"+i+".png" );   // make sure images "0.jpg" to "11.jpg" exist
}
photo = loadImage("images/cart.png");
bgfood = loadImage("images/bg.png");
back = loadImage("images/background.png");


  noStroke();
  fill(255, 204);
  
  //sound ...................................
   path = sketchPath(audioName);
      file = new SoundFile(this, path);
      file.play();
}

void draw(){
  background(200);
  image(back, 0, 0,500,500);
   /*for (Ball ball : balls) {
    ball.collide();
    ball.move();
    ball.display();  
  } */
      
  
  
  //timer counts down from 120seconds ...................
  fill(0);
  t=millis()/1000;
  

  text(t,400,40);
 
  

  //...........................
  
        textSize(32);
text(points,10,40);
 
  for (int i=0; i< b.length; i++) {
b[i].display();
//b[i].ascend();
//b[i].side();
b[i].fall();
} // for the falling objects 

  for (int i=0; i< bf.length; i++) {
bf[i].display();
//b[i].ascend();
//b[i].side();
bf[i].fall();
} // for the falling objects

//character display................................................,.,,.
 image(photo, moveX, moveY,moveW,moveH);
//image(food[0],moveX,moveY,100,100);
//image(bfood[0],moveX,moveY,100,100);
//shape(character,moveX,moveY);


  if (moveX <= 0){
    moveX= moveX+speed;
  }
  if (moveX >= width) {
    moveX= moveX-speed;
  }
  if (moveY <= 0) {
    moveY= moveY+speed;
  }
  if (moveY >= height) {
    moveY= moveY-speed;
  }
  
  //rest the game timer ......
    if (t>=stop ){
rect(0,0,500,5000);
    fill(255);
  text("GAME OVER", 255, 200 );
   fill(0);
text("you got"+points+"food items",150,250);


 }
 else{
  
 
 }
//................
}

void keyPressed()
{
  if (key == CODED) {
    if (keyCode == LEFT) {
      moveX= moveX-speed;
    }

    if (keyCode == RIGHT) {
      moveX= moveX+speed;
    }
  /*  if (keyCode == UP) {
      moveY= moveY-speed;
  }
  if (keyCode == DOWN) {
      moveY= moveY+speed; 
    } */
  }
}

//to rest the game...........
void mouseClicked(){
setup();
  t = millis() - t;
text(t,250,200);
points=0;

 exit();
}