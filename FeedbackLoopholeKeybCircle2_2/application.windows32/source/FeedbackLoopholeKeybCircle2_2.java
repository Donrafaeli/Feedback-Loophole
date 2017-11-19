import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.pdf.*; 
import ddf.minim.*; 
import ddf.minim.analysis.*; 
import ddf.minim.ugens.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class FeedbackLoopholeKeybCircle2_2 extends PApplet {







PGraphics canvas;

Minim minim;
AudioInput in;
FFT fft;
int sampleRate= 22050;
Gain gain;

boolean crt=false;
float r, xa, y, c, d, e, f, g, h, i, j, k, l, m, o, p;
int a;
int b;
int value;
int number=0;
int numpoints = 1;
int wheelAccumulator = 1;
float coordx= 0.5f;
float coordy= 0.5f;
float coordax= 1;
float coorday= 1;

boolean locked = false;
boolean locked2 = false;
boolean locked3 = false;
float xOffset = 0.0f; 
float yOffset = 0.0f; 
float bx;
float by;
float xOffset2 = 0.0f; 
float yOffset2= 0.0f; 
float bx2;
float by2;
float xOffset3 = 0.0f; 
float yOffset3= 0.0f; 
float bx3;
float by3;
float ratio= 0.5f;
float ratio3= 0.5f;
float dB = 80;
 


//////////////////////////////////////////////////////////////////////////////

public void setup()
{
  
  background(200);
  canvas = createGraphics(width,height);
  hint(ENABLE_STROKE_PURE);

  
  minim = new Minim(this);
  minim.debugOn();
  in = minim.getLineIn(Minim.MONO, 256, sampleRate);
  fft = new FFT(in.left.size(), sampleRate);
  gain = new Gain(60);

}


//////////////////////////////////////////////////////////////////////////////

public void draw() {
  background(200);
  noCursor();

    int d = PApplet.parseInt(random(in.left.level()*2000, in.left.level()*4000));
    float c =(in.left.level()*10000);
    float a =map(in.left.level(),0.0001f, 0.18f, 860, 1060);
    float e =map(in.left.level(),0.0001f, 0.16f, 440, 640)*ratio;
    float f =map(in.left.level(),0.0001f, 0.16f, 860, 1060);
    float h =map(in.left.level(),0.0001f, 0.16f, 0, 1920)*ratio;
    float xPad =map(in.left.level(),0.0001f, 0.16f, 860, 1060);
    float yPad =map(in.left.level(),0.0001f, 0.16f, 0, 1920);
    float g =map(in.left.level(),0.0001f, 0.16f, 1080, 0)*ratio;
    float j =map(in.left.level(),0.0001f, 0.17f, 1, 35);
    float jj =map(in.left.level(),0.0001f, 0.17f, 1, 16); 
    float k =map(in.left.level(),0.0001f, 0.18f, 440, 640)*ratio;
    float m =map(in.left.level(),0.0001f, 0.18f, 1060, 860)*ratio;
    float n =map(in.left.level(),0,1,50,800);
    float o =lerp(k,m,0.5f)*ratio;
    float mousey =map(mouseY,0,500,0,30);
    float mousey2 =map(mouseY,0,500,0,5);
      
       gain.setValue(dB);   
      

  if (in.left.level()>0.004f) {
    crt=true;
  } else crt=false;

 
  fadeGraphics(canvas, 2); 
  canvas.beginDraw();   

  for (int i = 0; i < numpoints; i++)
  {
    if (crt==true)  
    {     
    canvas.beginDraw();
    canvas.stroke(0);
    canvas.noFill();
    canvas.ellipse(o+coordx, m+coordy, j/2, j/2);
    canvas.ellipse(e+coordx, g+coordy, j/2+10,j/2+10);
    canvas.ellipse(k+coordx, h+coordy,j, j);
    canvas.strokeWeight(j*mousey);
    canvas.endDraw();
       }
    else 
      crt=false;      

       

  image(canvas, 0, 0);
  }
  
  
}

  


//////////////////////////////////////////////////////////////////////////////

public void mousePressed() {
  if (mouseButton == LEFT) {
    locked = true;
    coordx= map(mouseX,0,768,0,500) ;
    coordy= map(mouseY,0,1150,0,500) ;
  } else {
    coordx = coordx;
    coordy = coordy;
    locked = false;
  }
  
    if (mouseButton == RIGHT) {
    locked2 = true;
    ratio= map(mouseY,0,768,0,1) ;
  } else {
    ratio = ratio;
    locked2 = false;
  }
  
  xOffset = mouseX-bx; 
  yOffset = mouseY-by;
}

public void mouseDragged() {
  if(locked) {
    bx = mouseX-xOffset; 
    by = mouseY-yOffset; 
    coordx= map(mouseX,0,768,0,500) ;
    coordy= map(mouseY,0,1150,0,500) ;
  }
    if(locked2) {
    bx2 = mouseX-xOffset2; 
    by2 = mouseY-yOffset2; 
    ratio= map(mouseY,0,768,0,1) ;
  }
    if(locked3) {
    bx3 = mouseX-xOffset3; 
    by3 = mouseY-yOffset3; 
    ratio3= map(mouseY,0,768,0,1);
    }
}

public void mouseReleased() {
  locked = false;
  locked2 = false;
}

  
public void keyPressed() {
  if (value == '+') {
      dB = 80;
  } else if (value == '-')  {
      dB = -35;
  }
  if (value == ' ') {
        locked3= true;
        ratio3= map(mouseY,0,768,0,1) ;    
  } else {
      locked3 = false;
      ratio3= ratio3;
  }
  }

public void keyReleased() {
  if (value == ' ') {
  locked3 = false;
  }
}

public void fadeGraphics(PGraphics c, int fadeAmount) {
  c.beginDraw();
  c.loadPixels();
 

float mousex =map(mouseX,0,1280,0,11);
int mousexint = (int)mousex;

  // iterate over pixels
  for (int i =0; i<c.pixels.length; i++) {

    // get alpha value
    int alpha = (c.pixels[i] >> 24) & 0xFF ;

    // reduce alpha value
   // alpha = max(0, alpha-fadeAmount*p); // multiplying after fadeamount speeds up fadeout, and vice versa
    alpha = max(0, alpha-fadeAmount*mousexint);
    // assign color with new alpha-value
    c.pixels[i] = alpha<<24 | (c.pixels[i]) & 0xFFFFFF ;
  }

  canvas.updatePixels();
  canvas.endDraw();
}
 
public float mapLog(float value, float start1, float stop1, float start2, float stop2) {
  float inT = map(value, start1, stop1, 1, 2.8f);
  float outT = (log(inT)) ;
  return map(outT, 0, 1, start2, stop2);
}
public float mapSquared(float value, float start1, float stop1, float start2, float stop2) {
  float inT = map(value, start1, stop1, 0, 1);
  float outT = inT * inT;
  return map(outT, 0, 1, start2, stop2);
}
public float mapExp(float value, float start1, float stop1, float start2, float stop2) {
  float inT = map(value, start1, stop1, -10, 1);
  float outT = pow(2,inT);
  return map(outT, 0, 1, start2, stop2);
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "FeedbackLoopholeKeybCircle2_2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
