import processing.core.PApplet;
import processing.core.PImage;

/**
 * A program thats creates a little game made by Timothy Chen and Ryan Chan of Mr. Fabroa's 2023/2024 ICS3U1 class
 * @author: T. Chen & Ryan. Chan
 */

public class Sketch1 extends PApplet {

  // Global Variables
  int intMenuSelect; 
  float fltPlayerHp;
  float fltHpBar = 300;
  boolean blnPlayerAlive;
  // Player Variables
  int intPlayerPosX;
  int intPlayerPosY; 
  int intPlayerR; 

  // Speed
  int intPlayerS;
    
  // Variables to confirm player movement
  boolean blnFaceR = true;
  boolean blnFaceDown = true;
  boolean blnIdle = true;

  // Counts the ticks of the character moving to sync the animation
  int intPlayerMoveTick = 0;
  //  Number of ticks till the new step animation is animated
  int intStepLength = 5;
  // False is walk image 1, true is walk image 2
  boolean blnStep = false;

  // Enumeration for the state of the game, menu vs gameplay
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
    // Sets background colour
    background(0, 0, 0);

    // Resizes the loaded images
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
    //  Selects the state of the game
    if ((intMenuSelect == 1) && (keyCode == ENTER)){
      background(255);
      State = STATE.GAME; 
    }
  
  // Determines game state to be menu, selection, game or quit, changes screen depending on state, determines menu button select
  if(State == STATE.MENU) {
  background(0);
  image(imgTitleScreen, 0, 0);

  if (keyPressed) {
    if (keyCode == UP) {
      intMenuSelect -= 1;
      delay(100);
      if (intMenuSelect < 1) {
        intMenuSelect = 3;
      }
    } else if (keyCode == DOWN) {
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
    } else {
  if ( intMenuSelect == 2) { 
  fill(255, 0, 0);
  textSize(50);
  text("Character Selection", 180, 360);
    } else {
  if (intMenuSelect == 3) { 
  fill(255, 0, 0);
  textSize(50);
  text("QUIT", 330, 475);
      }
    }
  }

    } else {    
  if(State == STATE.GAME) {
    Player();
    Player(width / 2, height / 2, 32);
    playerDirection();
  /*while(blnPlayerAlive != false) {
  }*/
  healthBar();
    }
  }
}

public void healthBar() {
  // Creates Hp Bar
  strokeWeight(2);
  stroke(0);
  noFill();
  rect(50, 25, 250, 15);

  fill(255, 0, 0);
  rect(50, 25, fltHpBar, 15);  
  }
  // define other methods down here. 

  public void Player() { 
    intPlayerPosX = width / 2;
    intPlayerPosX = height / 2; 
    intPlayerR = 32;
  }

  public void Player(int intPosX, int intPosY, int intRadius) {
    this.intPlayerPosX = intPosX;
    this.intPlayerPosX = intPosX;
    intPlayerR = intRadius; 
  }

  public void playerDirection() {
    // Determines if player is facing right, if it is facing right, animate player moving right images
    if (blnFaceR) 
    {
      if(intPlayerMoveTick > intStepLength) 
      {
        blnStep = !blnStep;
        intPlayerMoveTick = 0;
      }
      if (blnStep) 
      {
        image(imgPlayerRight1, intPlayerPosX - intPlayerR, intPlayerPosY - intPlayerR);
      }
      else 
      { 
        image(imgPlayerRight2, intPlayerPosX - intPlayerR, intPlayerPosY - intPlayerR);
      }
    }
    // Determines if player is facing left, if it is facing leftt, animate player moving left images
    else if (!blnFaceR) 
    {
      if (intPlayerMoveTick > intStepLength)
      {
        blnStep = !blnStep;
        intPlayerMoveTick = 0;
      }

      if (blnStep)
      {
        image(imgPlayerLeft1, intPlayerPosX - intPlayerR, intPlayerPosY - intPlayerR);
      }
      else 
      {
        image(imgPlayerLeft2, intPlayerPosX - intPlayerR, intPlayerPosY - intPlayerR);
      }
    }
    // Determines if player is facing down, if it is facing down, animate player moving down images
    else if (blnFaceDown) 
    {
      if (intPlayerMoveTick > intStepLength)
      {
        blnStep = !blnStep;
        intPlayerMoveTick = 0;
      }

      if (blnStep)
      {
        image(imgPlayerDown1, intPlayerPosX - intPlayerR, intPlayerPosY - intPlayerR);
      }
      else 
      {
        image(imgPlayerDown2, intPlayerPosX - intPlayerR, intPlayerPosY - intPlayerR);
      }
    }
    // Determines if player is facing up, if it is facing up, animate player moving down images
    else if (!blnFaceDown) 
    {
      if (intPlayerMoveTick > intStepLength)
      {
        blnStep = !blnStep;
        intPlayerMoveTick = 0;
      }

      if (blnStep)
      {
        image(imgPlayerUp1, intPlayerPosX - intPlayerR, intPlayerPosY - intPlayerR);
      }
      else 
      {
        image(imgPlayerUp2, intPlayerPosX - intPlayerR, intPlayerPosY - intPlayerR);
      }
    }
  }
}






// TotalHp, sets the frame
// If damage is taken, hp goes lower