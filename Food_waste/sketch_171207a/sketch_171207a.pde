    import processing.sound.*;
    import ddf.minim.*;
    
    SoundFile file;
    //put your audio file name here
    String audioName = "ss1.wav";
    String path;
    //.........trigger sound
    boolean flushNow;
    AudioSnippet flush;
    Minim minim;
    

    //runs once when the app first starts
    void setup() {
      // for more info about sketchPath, go to https://processing.org/discourse/beta/num_1229443269.html
      path = sketchPath(audioName);
      file = new SoundFile(this, path);
      //file.loop();
    }

    //runs all the time, this is the main app loop
    minim = new Minim(this);
    flush = minim.loadsnippet("ss.wav");
    
    void draw() {
       flush(flushNow);
    }
    
         void keyPressed()
{
  if (key == CODED) {
    if (keyCode == LEFT) {
      //file.play();
      flushNow = true;
      flush.rewind();
      flush.play();
     
    }
    else{ 
    //file.stop();
  }

    
  }
}