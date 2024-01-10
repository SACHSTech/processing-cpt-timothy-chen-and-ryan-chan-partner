import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
	
  

  int intworldX;
  int intworldY;

  // Player movememnt variables
  boolean blnWPressed;
  boolean blnAPressed; 
  boolean blnSPressed;
  boolean blnDPressed;

  PImage imgOrcAttackDown;
  
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);

    imgOrcAttackDown = loadImage("orc_attack_down_1.png");
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
	// sample code, delete this stuff
    stroke(128);
    line(150, 25, 270, 350);  

    stroke(255);
    line(50, 125, 70, 50);  
  }
  

  public void keyPressed() {
    if (key == 'w' || key == 'W') {
      blnWPressed = true;
    }
    else if (key == 'a' || key == 'A') {
      blnAPressed = true;
    }
    else if (key == 's' || key == 'S'){
      blnSPressed = true;
    }
    else if (key == 'd' || key == 'D'){
      blnDPressed = true;
    }   
  }

  public void keyReleased() {
    if (key == 'w' || key == 'W') {
      blnWPressed = false;
    }
    else if (key == 'a' || key == 'A') {
      blnAPressed = false;
    }
    else if (key == 's' || key == 'S'){
      blnSPressed = false;
    }
    else if (key == 'd' || key == 'D'){
      blnDPressed = false;
    }
  }
}