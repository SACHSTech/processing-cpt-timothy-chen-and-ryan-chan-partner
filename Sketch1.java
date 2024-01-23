import processing.core.PApplet;
import processing.core.PImage;

/**
 * A program thats creates a little game made by Timothy Chen and Ryan Chan of Mr. Fabroa's 2023/2024 ICS3U1 class
 * @author: T. Chen & Ryan. Chan
 */

public class Sketch1 extends PApplet {

  // Global Variables
  int intMenuSelect; 
  int intPlayerHp = 10;
  float fltHpBar = 300;
  boolean blnPlayerAlive;
  // Defining variables for weapon animation and selection
  int intWeaponSelect;
  int intWeaponYSpeed = 1;
  int intSwordY = 350;
  int intWandY = 390;
  int intHpBar = 300;

  // Player Variables
  int intPlayerX;
  int intPlayerY; 
  int intPlayerR; 
  int intPlayerHitBox = 40;

  // Speed
  int intPlayerSpeed = 8;
    
  // Variables to confirm player movement
  boolean blnFaceRight = false;
  boolean blnFaceUp = false;
  boolean blnFaceLeft = false;
  boolean blnFaceDown = true;
  boolean blnMoving = false;
  boolean blnMovingLeft = false;
  boolean blnMovingRight = false;
  boolean blnMovingDown = false;
  boolean blnMovingUp = false;

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

  // Selection menu objects
  PImage imgSelectScreen;
  PImage imgSwordS1;
  PImage imgWandS2;
  PImage imgSwordSelected;
  PImage imgWandSelected;
  // Other Images
  PImage imgSwordBeam;
  PImage imgHollowPurple;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */ 
  public void settings() {
	  // put your size call here
    size(800, 800);

    // Loading Titlescreen Image
    imgTitleScreen = loadImage("titlebackground.jpg");

    // Loading Selection menu object Images
    imgSelectScreen = loadImage("selectionbg.jpg");
    imgSwordS1 = loadImage("swords1.png");
    imgWandS2 = loadImage("wands2.png");
    imgSwordSelected = loadImage("swordSelected.png");
    imgWandSelected = loadImage("wandSelected.png");


    // Loading Player Images
    imgPlayerUp1 = loadImage("player_up_1.png");
    imgPlayerUp2 = loadImage("player_up_2.png");
    imgPlayerLeft1 = loadImage("player_left_1.png");
    imgPlayerLeft2 = loadImage("player_left_2.png");
    imgPlayerRight1 = loadImage("player_right_1.png");
    imgPlayerRight2 = loadImage("player_right_2.png");
    imgPlayerDown1 = loadImage("player_down_1.png");
    imgPlayerDown2 = loadImage("player_down_2.png");

    // Loading other images
    imgSwordBeam = loadImage("weaponbeam.png");
    imgHollowPurple = loadImage("bowlingballofdeath.png");

  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    // Sets background colour
    background(0, 0, 0);
    frameRate(60);
    // Resizes the loaded images
    imgSelectScreen.resize(800, 800);
    imgTitleScreen.resize(800, 800);
    imgSwordS1.resize(256, 256);
    imgWandS2.resize(128, 128);
    imgSwordSelected.resize(256, 256);
    imgWandSelected.resize(128, 128);
    imgPlayerUp1.resize(32, 32);
    imgPlayerUp2.resize(32, 32);
    imgPlayerLeft1.resize(32, 32);
    imgPlayerLeft2.resize(32, 32);
    imgPlayerRight1.resize(32, 32);
    imgPlayerRight2.resize(32, 32);
    imgPlayerDown1.resize(32, 32);
    imgPlayerDown2.resize(32, 32);
    imgSwordBeam.resize(16, 16);
    imgHollowPurple.resize(64, 64);
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
    else if ((intMenuSelect == 2) && (keyCode == ENTER)){
      State = STATE.SELECTION; 
    }
    else if ((intMenuSelect == 3) && (keyCode == ENTER)) {
      State = STATE.QUIT;
      if (State == STATE.QUIT) {
        exit();
      }
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
  text("Weapon Selection", 180, 360);
  
  fill(0);
  textSize(50);
  text("QUIT", 330, 475);

  // Draws the first menu text button (Play)
  if (intMenuSelect == 1) {

  fill(255, 0, 0);
  textSize(50);
  text("PLAY", 330, 245);

  // Draws the second menu text button (Weapon Selection)
  } 
  else if ( intMenuSelect == 2) { 

  fill(255, 0, 0);
  textSize(50);
  text("Weapon Selection", 180, 360);

  // Draws the third menu text button (Quit)
  } 
  else if (intMenuSelect == 3) {

  fill(255, 0, 0);
  textSize(50);
  text("QUIT", 330, 475);
  }


 }
  // Determines if it is game state, uses methods to draw game
  else if(State == STATE.GAME) {
    playerDirection();
    healthBar();
  }
  // Determines if it is selection state, draws selection objects
  else if (State == STATE.SELECTION) {
    image(imgSelectScreen, 0, 0, 800, 800); 
    image(imgSwordS1, 200, intSwordY);
    image(imgWandS2, 430, intWandY);

    // If statement to determine weapon select and giving the weapon a numerical value
    if (keyPressed) {
      
      if (keyCode == LEFT) {

        intWeaponSelect -= 1;
        delay(60);

        if (intMenuSelect < 1) {
          intMenuSelect = 1;
        }
      } else if (keyCode == RIGHT) {

        intWeaponSelect += 1;
        delay(60);
        
        if (intMenuSelect > 2) {
          intMenuSelect = 2;
        }
      }
      // If statement that will animate the weapon
      if (intWeaponSelect == 1) {
        image(imgSwordSelected, 200, intSwordY);
      }
      else if (intWeaponSelect == 2) {
        image(imgWandSelected, 430, intWandY);
      }
    }
  }
}
  // define other methods down here. 
  public void healthBar() {
    // Creates Hp Bar
    strokeWeight(2);
    stroke(0);
    noFill();
    rect(50, 25, 300, 15);
  
    fill(255, 0, 0);
    rect(50, 25, fltHpBar, 15);  
    }

  public void stageSelect() {

    int intStageNumber = 0;

    boolean blnClearCondition;
    // Sets the condition for player death
    if (intPlayerHp == 0) {
      blnPlayerAlive = false;
    }
    // Sets the clear condition of a stage to be based off the player alive status and stage changes
    if(blnPlayerAlive == false) {

      blnClearCondition = true; 
      if ((blnClearCondition == true) && (intStageNumber == 0)) {
        intStageNumber += 1;
      }

    }
     if (intStageNumber == 0) {
      // tutorial stuff
     }
     else if (intStageNumber == 2) {
      // Boss level
     }
    }

  // define other methods down here. 
  public void playerDirection() {
    // Determines if player is facing right, if it is facing right, animate player moving right images
    if (!blnMoving) {
      if (blnFaceDown) {
        image(imgPlayerDown1, intPlayerX, intPlayerY);
      } 
      else if (blnFaceLeft) {
        image(imgPlayerLeft1, intPlayerX, intPlayerY);
      }
      else if (blnFaceRight) {
        image(imgPlayerRight1, intPlayerX, intPlayerY);
      }
      else if (blnFaceUp) {
        image(imgPlayerUp1, intPlayerX, intPlayerY);
      }
    }
    else if (blnMoving && blnMovingUp) {
      if (intPlayerMoveTick > intStepLength) {
        blnStep = !blnStep;
        intPlayerMoveTick = 0;
      }
      if (blnStep) 
      {
        image(imgPlayerUp1, intPlayerX, intPlayerY);
      }
      else 
      { 
        image(imgPlayerUp2, intPlayerX, intPlayerY);
      }
    }

    // Determines if player is facing left, if it is facing leftt, animate player moving left images
    else if (blnMoving && blnMovingDown) 
    {
      if (intPlayerMoveTick > intStepLength)
      {
        blnStep = !blnStep;
        intPlayerMoveTick = 0;
      }
      if (blnStep)
      {
        image(imgPlayerDown1, intPlayerX, intPlayerY);
      }
      else 
      {
        image(imgPlayerDown2, intPlayerX, intPlayerY);
      }
    }
    // Determines if player is facing down, if it is facing down, animate player moving down images
    else if (blnMoving && blnMovingLeft) 
    {
      if (intPlayerMoveTick > intStepLength)
      {
        blnStep = !blnStep;
        intPlayerMoveTick = 0;
      }

      if (blnStep)
      {
        image(imgPlayerLeft1, intPlayerX, intPlayerY);
      }
      else 
      {
        image(imgPlayerLeft2, intPlayerX, intPlayerY);
      }
    }
    // Determines if player is facing up, if it is facing up, animate player moving down images
    else if (blnMoving && blnMovingRight) 
    {
      if (intPlayerMoveTick > intStepLength)
      {
        blnStep = !blnStep;
        intPlayerMoveTick = 0;
      }

      if (blnStep)
      {
        image(imgPlayerRight1, intPlayerX, intPlayerY);
      }
      else 
      {
        image(imgPlayerRight2, intPlayerX, intPlayerY);
      }
    }
  }

  public void hollowPurple() {
    /*if(keyPressed == "/") {

    }/* */

  } 
}







// TotalHp, sets the frame
// If damage is taken, hp goes lower