import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Food_waste extends PApplet {

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
float spring = 0.05f;
float gravity = 0.03f;
float friction = -0.9f;
Ball[] balls = new Ball[numBalls];

//timer
int t;


//audio intialisation .................////////......

    
    SoundFile file;
    //put your audio file name here
    String audioName = "audio/ss1.wav";
    String path;

    //runs once when the app first starts

public void setup(){
  
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

public void draw(){
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

public void keyPressed()
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
public void mouseClicked(){
setup();
  t = millis() - t;
text(t,250,200);
points=0;

 exit();
}
class Ball {
  
  float x, y;
  float diameter;
  float vx = 0;
  float vy = 0;
  int id;
  Ball[] others;
 PShape ob;
 
  Ball(float xin, float yin, float din, int idin, Ball[] oin) {
    x = xin;
    y = yin;
    diameter = din;
    id = idin;
    others = oin;
 
   
  } 
  
  public void collide() {
    for (int i = id + 1; i < numBalls; i++) {
      float dx = others[i].x - x;
      float dy = others[i].y - y;
      float distance = sqrt(dx*dx + dy*dy);
      float minDist = others[i].diameter/2 + diameter/2;
      if (distance < minDist) { 
        float angle = atan2(dy, dx);
        float targetX = x + cos(angle) * minDist;
        float targetY = y + sin(angle) * minDist;
        float ax = (targetX - others[i].x) * spring;
        float ay = (targetY - others[i].y) * spring;
        vx -= ax;
        vy -= ay;
        others[i].vx += ax;
        others[i].vy += ay;
      }
    }
   //trying to make the falling objects disappear on interraction 
   /*
   if (x+y >= moveX+moveY){
     fill(0,0,255);
    shape(character,moveX,moveY);
  numBalls = numBalls-1;
   } else {
   noFill();
    shape(character,moveX,moveY);
   // numBalls = numBalls+0;
   }
    */
  }
  
  public void move() {
    vy += gravity;
    x += vx;
    y += vy;
    if (x + diameter/2 > width) {
      x = width - diameter/2;
      vx *= friction; 
    }
    else if (x - diameter/2 < 0) {
      x = diameter/2;
      vx *= friction;
    }
    if (y + diameter/2 > height) {
      y = height - diameter/2;
      vy *= friction; 
    } 
    else if (y - diameter/2 < 0) {
      y = diameter/2;
      vy *= friction;
    }
  }
  
  public void display() {
     fill(255);
      image(bgfood, x, y,diameter,diameter);
    //ellipse(x, y, diameter, diameter);
  
    
  }
}
class Badfood{

float x;
float y;
float speed;
float ballsize;


Badfood() {
  x =random(width);
  y = 0;
  speed = random(0.5f,2);
  ballsize = random(40);

}

public void display() {

 stroke(255);
 //ellipse(x,y,ballsize,ballsize);
   image(bfood[3],x,y,ballsize,ballsize);
 //image(flowers[0],x,y,20,20);
 //rect(x,y,ballsize,ballsize);

}

public void ascend(){
 y = y-speed;
 x = x- random(-2,2);
 if (y >= 0){
 points=points+1;
 }
}

public void fall(){
   if (y+speed >= height){
if (y < height) { 
    y = 0; 
  } 
 }
 y = y+speed;
 
 //colision code
   if(dist(x,y,moveX,moveY)<=20 ){
     if (y < height) { 
       points=points-2;
    y = 0; 
  } 
      points=points+value;
      //if (points >= points){points= points*points; }
//background(0,0,255);
}
// x = x+ random(-2,2);
//if (y >= flowers[0].height){
//background(0,255,0);
//}

  if (t>=stop){
y = -1;

 }
}

public void side(){
x= x + speed;

if (x > width || x < 0) {
  //turn around
  speed = speed * -1;
}

if (x <0) {
  speed = 10;
}
}
  


}
class Falling{

float x;
float y;
float speed;
float ballsize;



Falling() {
  x =random(width);
  y = 0;
  speed = random(0.5f,2);
  ballsize = random(40);

}

public void display() {
 fill(100,100,255);
 stroke(255);
 image(food[index],x,y,ballsize,ballsize);
 //ellipse(x,y,ballsize,ballsize);
 //image(flowers[0],x,y,20,20);
 //rect(x,y,ballsize,ballsize);

}

public void ascend(){
 y = y-speed;
 x = x- random(-2,2);
 if (y >= 0){
 points=points+1;
 }
}

public void fall(){
   if (y+speed >= height){
if (y < height) { 
    y = 0; 
  } 
 }
 y = y+speed;
 
 //colision code
   if(dist(x,y,moveX,moveY)<=20 ){
     if (y < height) { 
       points=points++;
       index = PApplet.parseInt(random(0,food.length));
    y = 0; 
  } 
      points=points+value;
      //if (points >= points){points= points*points; }
//background(0,0,255);
}
// x = x+ random(-2,2);
//if (y >= flowers[0].height){
//background(0,255,0);
//}
  if (t>=stop){
y = -1;}
}

public void side(){
x= x + speed;

if (x > width || x < 0) {
  //turn around
  speed = speed * -1;
}

if (x <0) {
  speed = 10;
}
}
  


}
  public void settings() {  size (500,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Food_waste" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
