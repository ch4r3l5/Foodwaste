class Badfood{

float x;
float y;
float speed;
float ballsize;


Badfood() {
  x =random(width);
  y = 0;
  speed = random(0.5,2);
  ballsize = random(40);

}

void display() {

 stroke(255);
 //ellipse(x,y,ballsize,ballsize);
   image(bfood[3],x,y,ballsize,ballsize);
 //image(flowers[0],x,y,20,20);
 //rect(x,y,ballsize,ballsize);

}

void ascend(){
 y = y-speed;
 x = x- random(-2,2);
 if (y >= 0){
 points=points+1;
 }
}

void fall(){
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

void side(){
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