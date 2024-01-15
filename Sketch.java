import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
  int intWorldX;
  int intWorldY;
  int intPlayerX = 40;
  int intPlayerY = 40;

  int intDashCooldown = 0;

  // Player movememnt variables
  boolean blnWPressed;
  boolean blnAPressed; 
  boolean blnSPressed;
  boolean blnDPressed;
  boolean blnDashReady = true;
  boolean blnDashPressed = false;

  

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
    imageMode(CENTER);
    frameRate(60);
    background(210, 255, 173);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
	// sample code, delete this stuff
  if (blnDashPressed == true && blnDashReady == true) {
    blnDashReady = false;
    if (blnWPressed == true){
      intPlayerY -= 10;
    }
    if (blnAPressed == true){
      intPlayerX -= 10;
    }
    if (blnSPressed == true){
      intPlayerY += 10;
    }
    if (blnDPressed == true){
      intPlayerX += 10;
    }
    if (blnWPressed == true && blnAPressed == true){
      intPlayerX -= 10;
      intPlayerY -= 10;
    }
    if (blnWPressed == true && blnDPressed == true){
      intPlayerX += 10;
      intPlayerY -= 10;
    }
    if (blnSPressed == true && blnAPressed == true){
      intPlayerX -= 10;
      intPlayerY += 10;
    }
    if (blnSPressed == true && blnDPressed == true){
      intPlayerX += 10;
      intPlayerX += 10;
    }
  }


  }
  

  public void keyPressed() {
    if (key == 'w' || key == 'W') {
      blnWPressed = true;
    
      intWorldY--;
    }
    else if (key == 'a' || key == 'A') {
      blnAPressed = true;
     
      intWorldX++;
    }
    else if (key == 's' || key == 'S'){
      blnSPressed = true;
      
      intWorldY++;
    }
    else if (key == 'd' || key == 'D'){
      blnDPressed = true;
      
      intWorldX--;
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