import processing.core.PApplet;
import processing.core.PImage;

/**
 * A program thats creates a little game made by Timothy Chen and Ryan Chan of Mr. Fabroa's 2023/2024 ICS3U1 class, description is in the read me!
 * @author: T. Chen & Ryan. Chan
 */

public class Sketch1 extends PApplet {

  // Global Variables
  int intMenuSelect = 1; 
  int intPlayerHp = 300;
  boolean blnPlayerAlive;
  boolean blnWeapon1Selected; 
  int intStageNumber = 0;
  boolean blnClearCondition;
  boolean blnGameWon = false;
  
  // Temporary variables to hold the position of the mouse at time of mouse pressed
  int intTempX = mouseX;
  int intTempY = mouseY;

  // Defining variables for weapon animation and selection
  int intWeaponSpeed = 35;
  int intWeaponSelect = 0;
  int intSwordY = 420;
  int intWandY = 470;

  // Player Variables
  int intPlayerX = width / 2;
  int intPlayerY = height / 2; 
  int intPlayerHitBox = 40;

  // Global Variables for weapon beams
  float fltWeaponAtk = 1;
  float fltWeaponBeamX = intPlayerX;
  float fltWeaponBeamY = intPlayerY;

  // Hollow Purple X & Y
  int intHollowPurpleX = 256;
  int intHollowPurpleY = 256;
  int intSizeIncrease = 64;

  // Speed
  int intPlayerSpeed = 4;
    
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

  // Number of ticks till the new step animation is animated
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
  PImage imgWandBeam;
  PImage imgHollowPurple;
  PImage imgSwordIcon;
  PImage imgWandIcon;

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
    imgSwordIcon = loadImage("classSelected1.png");
    imgWandIcon = loadImage("classSelected2.png");


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
    imgWandBeam = loadImage("wandbeam.png");
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
    imgSwordBeam.resize(64, 64);
    imgWandBeam.resize(64, 64);
    imgHollowPurple.resize(256, 256);
    imgSwordIcon.resize(32, 32);
    imgWandIcon.resize(32, 32);

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // Selects the state of the game
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
  // Draws the base text in the menu state for the different selection options and game title
  fill(0) ;
  textSize(80);
  text("Boss Bachi", 200, 200);
  
  fill(0);
  textSize(50);
  text("PLAY", 330, 245);

  fill(0);
  textSize(50);
  text("Weapon Selection", 180, 360);
  
  fill(0);
  textSize(50);
  text("QUIT", 330, 475);

  // Draws the first menu text button if selected (Play)
  if (intMenuSelect == 1) {

  fill(255, 0, 0);
  textSize(50);
  text("PLAY", 330, 245);

  // Draws the second menu text button if selected (Weapon Selection)
  } 
  else if ( intMenuSelect == 2) { 

  fill(255, 0, 0);
  textSize(50);
  text("Weapon Selection", 180, 360);

  // Draws the third menu text button if selected (Quit)
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
    hollowPurple();
    projectileMovement();
    stageSelect();
    if ((key == 'q') || (key == 'Q')) {
      State = STATE.MENU;
    }
    
    // Determines the player status on the next stage after tutorial, sets conditions for losing and input to retry
    if ((blnPlayerAlive == false) && (intStageNumber == 2)) {
      textSize(32);
      text("Game Over, try again", 200, 300);
      text("Press 'e' to try again", 400, 500);
      if ((key == 'e') || (key == 'E')) {
        intStageNumber = 2;
        blnGameWon = false;
        intPlayerHp = 300;
        blnPlayerAlive = true;
        // fltTankHp = 600;
      }
    }
  }

  // Determines if it is selection state, draws selection objects
  else if (State == STATE.SELECTION) {
    background(imgSelectScreen);
    image(imgSwordS1, 200, intSwordY);
    image(imgWandS2, 430, intWandY);

    // If statement to determine weapon select and giving the weapon a numerical value
    if (keyPressed) {
      if (keyCode == LEFT) {
        delay(50);
        intWeaponSelect -= 1;

        if (intWeaponSelect < 1) {
          intWeaponSelect = 1;
        }
      } 
      if (keyCode == LEFT)  {
        delay(50);
        intWeaponSelect -= 1;

        if (intWeaponSelect < 1) {
          intWeaponSelect = 1;
        }
      }
      if (keyCode == RIGHT)  {
        delay(50);
        intWeaponSelect += 1;

        if (intWeaponSelect > 2) {
          intWeaponSelect = 2;
        }
      } 
      if (keyCode == RIGHT)  {
        delay(50);
       intWeaponSelect += 1;

        if (intWeaponSelect > 2) {
          intWeaponSelect = 2;
        }
      }
    }
    // With the weapon select value chosen in the select screen, draws weapon selected image
    if (intWeaponSelect == 1) {

      image(imgSwordSelected, 200, intSwordY);

    }
    else if (intWeaponSelect == 2) {

      image(imgWandSelected, 430, intWandY);

    }

    // Selects the class for your character
    if ((intWeaponSelect == 1) && (keyCode == ENTER)) {
      blnWeapon1Selected = true;
    } 
    else if ((intWeaponSelect == 2) && (keyCode == ENTER)) {
      blnWeapon1Selected = !true;
    }
    // Exits back into menu state
    else if ((key == 'q') || (key == 'Q')) {
      State = STATE.MENU;
    }
  }


}
  

  // define other methods down here. 
  /**
   * Moves the projectile when the mouse is pressed and to where the mouse is pressed
   */
  public void projectileMovement() {
    if(mousePressed) {
      // Determines weapon beam according to weapon selected
      if(intWeaponSelect == 1) {
      image(imgSwordBeam, fltWeaponBeamX, fltWeaponBeamY);
    } 
      else if(intWeaponSelect == 2) {
      image(imgWandBeam, fltWeaponBeamX, fltWeaponBeamY);
    }
      if (fltWeaponBeamX < intTempX) {

          fltWeaponBeamX += intWeaponSpeed;

      } 
      else if (fltWeaponBeamX > intTempX) {

          fltWeaponBeamX -= intWeaponSpeed;
  
      }
      if (fltWeaponBeamY < intTempY) {

          fltWeaponBeamY += intWeaponSpeed;
    
      }
      else if (fltWeaponBeamY > intTempY) {

          fltWeaponBeamY -= intWeaponSpeed;

      } 
    }
  }
  /**
   * Detects mouseinput, sets temporary variable values to mouseX and mouseY so that the projectile does not constantly track the mouse
   */
  public void mousePressed() {
    intTempX = mouseX;
    intTempY = mouseY;
  }

  /**
   * Draws the health bar for the player, draws an icon for the player hp to determine weapon class
   */
  public void healthBar() {

    // Creates Hp Bar
    strokeWeight(2);
    stroke(0);
    noFill();
    rect(50, 25, 300, 15);
  
    fill(255, 0, 0);
    rect(50, 25, intPlayerHp, 15);  

    // Using the boolean to determine which weapon was selected and bases the class icon drawn off that
    if (blnWeapon1Selected == true) {
      image(imgSwordIcon, 370, 15);
    } 
    else if (blnWeapon1Selected != true) {
      image(imgWandIcon, 370, 15);
    }
  }

  /**
   * Sets conditions for player death and creates the clear condition for a stage, contains stage information
   */
  public void stageSelect() {

    // Sets the condition for player death
    if (intPlayerHp <= 0) {
      
      blnPlayerAlive = false;
    }
    // Sets the clear condition of a stage to be based off the player alive status and stage changes
    if(blnPlayerAlive == false) {
      
      blnClearCondition = true; 
      if ((blnClearCondition == true) && (intStageNumber == 1)) {
        
        intStageNumber += 1;
        intPlayerHp = 300;
        blnPlayerAlive = true;
      }

    }
    // Stage information 
     /* if (intStageNumber == 1) {
      // Image Background
      image(imgGrassBackground, width/2, height/2, 800, 800);
      Orc();
      
     }
     else if (intStageNumber == 2) {
      // Image Backgound
      if (blnGameWon != true) {

        image(imgBrickBackground, width/2, height/2, 800, 800);

      } else if (blnGameWon = true) {

        image(imgYouWinScreen, width/2, height/2, 800, 800);

      }

      if (blnPlayerAlive == true) {
        // Tank method (Ryan's Code)
        Tank(); 
      } 
      else if ((blnPlayerAlive == false) && (blnGameWon != true)) {
        fill(0);
        rect(0, 0, 800, 800);
      }
    } */
  }

  /**
   * Determines player movement animation and direction sprite based off keyboard input
   */ 
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

  /**
   * If button is pressed, the one who left it all behind regains a fragment of their powers to wield in combat.  
   */
  public void hollowPurple() {
    int intHollowPurpleAtk = 221801;
    boolean blnHollowPurpleActive = false;

    /*if (dist(intHollowPurpleX, intHollowPurpleY, intTankX, intTankY) <= 250) {
      fltTankHp -= intHollowPurpleAtk;
    } */

    if (key == '0') {
      blnHollowPurpleActive = true;
  }
  if (blnHollowPurpleActive == true) {

    image(imgHollowPurple, 400, 400, intHollowPurpleX, intHollowPurpleY);  
    if (frameCount % 5 == 0) {

      intHollowPurpleX += intSizeIncrease;
      intHollowPurpleY += intSizeIncrease;

      if(intHollowPurpleX > 1536) {
        blnHollowPurpleActive = false;
        intHollowPurpleX = 256;
        intHollowPurpleY = 256;
      }
    }
  }
}
}


