import processing.core.PApplet;
import processing.core.PImage;

public class SketchTest extends PApplet {
	
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

  
  // Orc Images
  PImage imgOrcAttackDown1;
  PImage imgOrcAttackDown2;
  PImage imgOrcAttackLeft1;
  PImage imgOrcAttackLeft2;
  PImage imgOrcAttackRight1;
  PImage imgOrcAttackRight2;
  PImage imgOrcAttackUp1;
  PImage imgOrcAttackUp2;
  PImage imgOrcDown1;
  PImage imgOrcDown2;
  PImage imgOrcLeft1;
  PImage imgOrcLeft2;
  PImage imgOrcRight1;
  PImage imgOrcRight2;
  PImage imgOrcUp1;
  PImage imgOrcUp2;
  
	
  // Enumeration for the state of the game, menu vs gameply
	private enum STATE{
    MENU,
    GAME
  };

  // Creates State variable to check what state the game is in based on
  private STATE State = STATE.MENU;
  
  /**
   * Called once at the beginning of execution, put your size all in this method
   */ 
  public void settings() {
	// put your size call here
    size(800, 800);
    imgOrcAttackDown1 = loadImage("orc_attack_down_1.png");
    imgOrcAttackDown2 = loadImage("orc_attack_down_2.png");
    imgOrcAttackLeft1 = loadImage("orc_attack_left_1.png");
    imgOrcAttackLeft2 = loadImage("orc_attack_left_2.png");
    imgOrcAttackRight1 = loadImage("orc_attack_right_1.png");
    imgOrcAttackRight2 = loadImage("orc_attack_right_2.png");
    imgOrcAttackUp1 = loadImage("orc_attack_up_1.png");
    imgOrcAttackUp2 = loadImage("orc_attack_up_2.png");
    imgOrcDown1 = loadImage("orc_down_1.png");
    imgOrcDown2 = loadImage("orc_down_2.png");
    imgOrcLeft1 = loadImage("orc_left_1.png");
    imgOrcLeft2 = loadImage("orc_left_2.png");
    imgOrcRight1 = loadImage("orc_right_1.png");
    imgOrcRight2 = loadImage("orc_right_2.png");
    imgOrcUp1 = loadImage("orc_up_1.png");
    imgOrcUp2 = loadImage("orc_up_2.png");
      
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    if(State ==  STATE.MENU) {
    imageMode(CENTER);
    frameRate(60);
    background(0, 0, 0);
    }
    if(State ==  STATE.GAME) {
      imageMode(CENTER);
      frameRate(60);
      background(255, 255, 255);
      }
  }
  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  game();
    if (keyCode == ENTER){
      State = STATE.GAME; 
    }
	// sample code, delete this stuff
  
  }
  
  // define other methods down here. 
  public void game(){
    if(State == STATE.GAME){
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
  
      if (blnDashReady == false) {
        if (frameCount % 180 == 0) {
          blnDashReady = true;
        }
      }
  
      
  
  
      image(imgOrcAttackDown1, intPlayerX, intPlayerY);
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
    if (key == ' ') {
      blnDashPressed = true;
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
    if (key == ' ') {
      blnDashPressed = false;
    }

  }

    


  
}


