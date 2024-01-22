import processing.core.PApplet;
import processing.core.PImage;

public class Sketch1 extends PApplet {


  // Global Variables
  int intMenuSelect; 
  float fltPlayerHp;
  float fltHpBarPercent;
  float fltHpBar = 250;
  float fltTotalHitPoints = 10;
  float fltCurrentHitPoints;
  boolean blnPlayerAlive;
  
  // Player Coord & Global Position cord
  int intPlayerPosX;
  int intPlayerPosY; 

  int intGlobalX;
  int intGlobalY;

  /** 
   * Enumeration for the state of the game, menu vs gameply
   * 
   */
	private enum STATE{
    MENU,
    GAME,
    SELECTION,
    QUIT
  }

  // Creates State variable to check what state the game is in based on
  private STATE State = STATE.MENU;

  // Player Images
  PImage imgPlayerUp1;
  PImage imgPlayerUp2;
  PImage imgPlayerLeft1;
  PImage imgPlayerLeft2;
  PImage imgPlayerRight1;
  PImage imgPlayerRight2;
  PImage imgPlayerDown1;
  PImage imgPlayerDown2;


  // Title Background
  PImage imgTitleScreen;
  /**
   * Called once at the beginning of execution, put your size all in this method
   */ 
  public void settings() {
	  // put your size call here
    size(800, 800);
    // Loading Titlescreen Image
    imgTitleScreen = loadImage("titlebackground.jpg");

    // Loading Player Images
    imgPlayerUp1 = loadImage("player_up_1.png");
    imgPlayerUp2 = loadImage("player_up_2.png");
    imgPlayerLeft1 = loadImage("player_left_1.png");
    imgPlayerLeft2 = loadImage("player_left_2.png");
    imgPlayerRight1 = loadImage("player_right_1.png");
    imgPlayerRight2 = loadImage("player_right_2.png");
    imgPlayerDown1 = loadImage("player_down_1.png");
    imgPlayerDown2 = loadImage("player_down_2.png");

  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(0, 0, 0);
    imgTitleScreen.resize(800, 800);
    imgPlayerUp1.resize(32, 32);
    imgPlayerUp2.resize(32, 32);
    imgPlayerLeft1.resize(32, 32);
    imgPlayerLeft2.resize(32, 32);
    imgPlayerRight1.resize(32, 32);
    imgPlayerRight2.resize(32, 32);
    imgPlayerDown1.resize(32, 32);
    imgPlayerDown2.resize(32, 32);

   
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    if ((intMenuSelect == 1) && (keyCode == ENTER)){
      background(255);
      State = STATE.GAME; 
    }
  
    // Determines game state to be menu or game, changes screen depending on state, determines menu button select
    if(State == STATE.MENU){
      background(0);
      image(imgTitleScreen, 0, 0);
      if (keyPressed) {
        if (keyCode == UP) {
          intMenuSelect -= 1;
          delay(100);
          if (intMenuSelect < 1) {
            intMenuSelect = 3;
          }
        } 
        else if (keyCode == DOWN) {
          intMenuSelect += 1;
          delay(100);
          if (intMenuSelect > 3) {
            intMenuSelect = 1;
          }
        }
      }
      
      fill(0);
      textSize(50);
      text("PLAY", 330, 245);

      fill(0);
      textSize(50);
      text("Character Selection", 180, 360);
      
      fill(0);
      textSize(50);
      text("QUIT", 330, 475);

      if (intMenuSelect == 1) {
        fill(255, 0, 0);
        textSize(50);
        text("PLAY", 330, 245);
      } 
      else {
        if (intMenuSelect == 2) { 
          fill(255, 0, 0);
          textSize(50);
          text("Character Selection", 180, 360);
        } 
        else {
          if (intMenuSelect == 3) { 
            fill(255, 0, 0);
            textSize(50);
            text("QUIT", 330, 475);
          }
      
        }

      }
    }
    else { 
      if(State == STATE.GAME){
        /*  if (fltCurrentHitPoints > 0){
          blnPlayerAlive = true;
        }
        if (blnPlayerAlive = true) {
          for(fltCurrentHitPoints > 0; fltCurrentHitPoints <=10)
        }
        */
        fltHpBarPercent = fltCurrentHitPoints / fltTotalHitPoints;
        strokeWeight(2);
        stroke(0);
        noFill();
        rect(525, 25, 250, 25);

        fill(255, 0, 0);
        rect(525, 25, fltHpBar * fltHpBarPercent, 25); 

          
      }
    }
  
  }
  
  // define other methods down here. 
}
