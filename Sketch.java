import processing.core.PApplet;
import processing.core.PImage;


public class Sketch extends PApplet {

  // Global Variables
  int intMenuSelect = 1; 
  int intPlayerHp = 300;
  boolean blnPlayerAlive = true;
  boolean blnWeapon1Selected; 
  int intStageNumber = 1;
  boolean blnClearCondition;
  
  // Temporary variables to hold the position of the mouse at time of mouse pressed
  int intTempX = mouseX;
  int intTempY = mouseY;

  // Defining variables for weapon animation and selection
  int intWeaponSpeed = 35;
  int intWeaponSelect = 0;
  int intSwordY = 350;
  int intWandY = 390;

  // Player Variables
  int intPlayerX = width / 2;
  int intPlayerY = height / 2; 
  int intPlayerHitBox = 40;

  // Global Variables for weapon beams
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

  // Player variables
  int intDashCooldown = 0;
  int intDashDistance = 20;
  boolean blnWPressed;
  boolean blnAPressed; 
  boolean blnSPressed;
  boolean blnDPressed;
  boolean blnDashReady = true;
  boolean blnDashPressed = false;

  // Orc Images
  PImage imgOrcDown1;
  PImage imgOrcLeft1;
  PImage imgOrcLeft2;
  PImage imgOrcRight1;
  PImage imgOrcRight2;

  // Orc Variables
  int[] intOrcX = new int[30];
  int[] intOrcY = new int[30];
  int[] intOrcHp = new int[30];
  int[] intOrcMoveTick = new int[30];
  int[] intOrcTakeStep = new int[30];
  int intOrcDirectionX;
  int intOrcDirectionY;
  int intOrcViewDistance = 200;
  int intOrcAttackRange = 40;
  int intOrcSpeed = 4;
  int intOrcDamage = 5;
  boolean [] blnOrcHideStatus = new boolean[30];
  boolean[] blnOrcMoving = new boolean[30];
  boolean[] blnOrcMoveRight = new boolean[30];
  boolean[] blnOrcMoveLeft = new boolean[30];
  boolean[] blnOrcMoveUp = new boolean[30];
  boolean[] blnOrcMoveDown = new boolean[30];
  boolean[] blnOrcStep = new boolean[30];
  
  // Tank Image Variables
  PImage[] imgTankExplode;
  PImage imgCannonBall1;
  PImage imgCannonBall2;
  PImage imgTankFaceLeft1;  
  PImage imgTankFaceRight1;
  PImage imgTankHitLeft;
  PImage imgTankhitRight;

  // Tank Variables
  float fltTankHp = 600;
  int intNumFrames = 3;
  int intMoveFrames = 0;
  int intNumTankExplode = 2;
  int intTankX = 700;
  int intTankY = 400;
  int intTankViewDistance = 800;
  int intTankAttackDistance = 500;
  int intTankSpeed = 10;
  int intTankHitBox = 110;
  int intCannonballX = intTankX - 50;
  int intCannonballY = intTankY - 50;
  int intCannonballCooldown = 60;
  int intCannonballFlyX = intTankX - 50;
  int intCannonballFlyY = intTankY - 50;
  int intCannonballMoveTick = 0;
  int intCannonballTakeStep = 5;
  int intCannonballSpeed = 10;
  boolean blnCannonballMoving;
  boolean blnTankHideStatus;
  boolean blnTankMoving;
  boolean blnTankAlive = true;
  boolean blnTankExplode1 = false;
  boolean blnTankExplode2 = false;
  boolean blnTankExplode3 = false;
  boolean blnCannonballLaunched = false; 
  boolean blnCannonballStep;
  boolean blnTankFaceLeft = true;
  boolean blnTankFaceRight = false;
  
  // Background Images
  PImage imgGrassBackground;
  PImage imgBrickBackground;
  PImage imgYouWinScreen;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */ 
  public void settings() {
    // put your size call here
    size(800, 800);

    // Sets the intPlayerX and Y location.
    intPlayerX = width/2;
    intPlayerY = height/2;

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

    // Load images for all of the image variables.
    imgOrcDown1 = loadImage("orc_down_1.png");
    imgOrcLeft1 = loadImage("orc_left_1.png");
    imgOrcLeft2 = loadImage("orc_left_2.png");
    imgOrcRight1 = loadImage("orc_right_1.png");
    imgOrcRight2 = loadImage("orc_right_2.png");
    imgCannonBall1 = loadImage("Cannon Ball_1.png");
    imgCannonBall2 = loadImage("Cannon Ball_2.png");
    imgGrassBackground = loadImage("Grass background.jpg");
    imgBrickBackground = loadImage("Brick Background.jpg");
    imgTankFaceLeft1 = loadImage("Tank Face Left_1.png"); 
    imgTankFaceRight1 = loadImage("Tank Face Right_1.png");
    imgTankHitLeft = loadImage("Tank Hit Left.png");
    imgTankhitRight = loadImage("Tank Hit Right.png");
    imgYouWinScreen = loadImage("You Win.jpg");
    imgTankExplode = new PImage[3];

    // for loop to set an array for the three tank explode images.
    for (int TankExplodeCount = 0; TankExplodeCount < 3; TankExplodeCount++) {
      imgTankExplode[TankExplodeCount] = loadImage("Tank Explode_" + TankExplodeCount + ".png");
    }

  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    // Sets background colour
    background(0, 0, 0);
    
    // Mode calls to change image mode to center.
    imageMode(CENTER);
    
    // Sets framerate of the game.
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

    // Image resize calls for the different images.
    imgGrassBackground.resize(200, 200);
    imgBrickBackground.resize(200, 200);
    imgOrcDown1.resize(30, 30);
    imgOrcRight1.resize(30, 30);
    imgOrcRight2.resize(30, 30);
    imgOrcLeft1.resize(30, 30);
    imgOrcLeft2.resize(30, 30);
    imgTankFaceLeft1.resize(150, 150);
    imgTankFaceRight1.resize(150, 150);
    imgCannonBall1.resize(80, 80);
    imgCannonBall2.resize(80, 80);
    imgYouWinScreen.resize(200, 200);
    
    // Background
    image(imgGrassBackground, width/2, height/2, 800, 800);
    image(imgBrickBackground, width/2, height/2, 800, 800);

    // For loop to initialise the ballhidestatus and setting it to false.
    for (int intCounterOrc = 0; intCounterOrc < blnOrcHideStatus.length; intCounterOrc++){
      blnOrcHideStatus[intCounterOrc] = false;
      blnOrcMoving[intCounterOrc] = false;
      blnOrcMoveRight[intCounterOrc] = false;
      blnOrcMoveLeft[intCounterOrc] = false;
      blnOrcMoveUp[intCounterOrc] = false;
      blnOrcMoveDown[intCounterOrc] = false;
      blnOrcStep[intCounterOrc] = false;
      intOrcMoveTick[intCounterOrc] = 0;
      intOrcTakeStep[intCounterOrc] = 5;
      intOrcHp[intCounterOrc] = 100;
      intOrcY[intCounterOrc] = (int) random(800);
      intOrcX[intCounterOrc] = (int) random(800);
    }

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
      image(imgTitleScreen, width/2, height/2);

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
      // Draws the base text in the menu state for the different selection options
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
      stageSelect();
      projectileMovement();
      healthBar();
      playerDirection();
      hollowPurple();
      
      // Player dash method
      playerDash();
      // Player movement method
      playerMovement();
      // Player edge detection method
      edgeDetection();

      // Determines the player status on the next stage after tutorial, sets conditions for losing and input to retry
      if ((blnPlayerAlive == false) && (intStageNumber == 2)) {
        textSize(32);
        text("Game Over, try again", 200, 300);
        text("Press 'e' to try again", 400, 500);
          if (key == 'e') {
            intPlayerHp = 300;
            blnPlayerAlive = true;
            fltTankHp = 600;
          }
        }
      }

    // Determines if it is selection state, draws selection objects
    else if (State == STATE.SELECTION) {
      background(imgSelectScreen);
      image(imgSwordS1, 310, intSwordY);
      image(imgWandS2, 525, intWandY);

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

        image(imgSwordSelected, 310, intSwordY);

      }
      else if (intWeaponSelect == 2) {

        image(imgWandSelected, 525, intWandY);

      }

      // Selects the class for your character
      if ((intWeaponSelect == 1) && (keyCode == ENTER)) {
        blnWeapon1Selected = true;
      } 
      else if ((intWeaponSelect == 2) && (keyCode == ENTER)) {
        blnWeapon1Selected = !true;
      }
      // Exits back into menu state
      if (key == 'q') {
        State = STATE.MENU;
      }
    }
    
      
  }

  /**
   * Moves the projectile when the mouse is pressed and to where the mouse is pressed
   */
  public void projectileMovement() {
    if(mousePressed) {
      image(imgSwordBeam, fltWeaponBeamX, fltWeaponBeamY);

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
    rect(50, 50, 300, 15);
  
    fill(255, 0, 0);
    rect(50, 50, intPlayerHp, 15);
    
    if (intStageNumber == 1) {
        noStroke();
        fill(132, 164, 20);
        rect(0, 40, 50, 30);  
    }

    // Using the boolean to determine which weapon was selected and bases the class icon drawn off that
    if (blnWeapon1Selected == true) {
      if (blnPlayerAlive == true) {
        image(imgSwordIcon, 370, 55);
      }
    } 
    else if (blnWeapon1Selected != true) {
      if (blnPlayerAlive == true) { 
        image(imgWandIcon, 370, 55);
      }
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
     if (intStageNumber == 1) {
      // Image Background
      image(imgGrassBackground, width/2, height/2, 800, 800);
      Orc();
      
     }
     else if (intStageNumber == 2) {
      // Image Backgounr
      image(imgBrickBackground, width/2, height/2, 800, 800);
      
      
      if (blnPlayerAlive == true) {
        //Tank method
        Tank();
      }
      else if (blnPlayerAlive == false) {
        fill(0);
        rect(0, 0, 800, 800);
      }

     }
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

    if (key == '0') {
      image(imgHollowPurple, CENTER, height / 2, intHollowPurpleX, intHollowPurpleY);  
      if (frameCount % 5 == 0) {
        intHollowPurpleX += intSizeIncrease;
        intHollowPurpleY += intSizeIncrease;
      }
    }
  }

  /**
   * Orc method that stores all of the Orc code.
   */
  public void Orc() {
    // for loop to have an orc counter for the array amount of the orc array.
    for (int intOrcCounter = 0; intOrcCounter < intOrcY.length; intOrcCounter++) {
      // if statement to set the blnOrcHideStatus for each of the Orcs to false.
      if (blnOrcHideStatus[intOrcCounter] == false) { 
        // if statement to draw the orc image while standing still.
        if (blnOrcMoving[intOrcCounter] == false) {
          image(imgOrcDown1, intOrcX[intOrcCounter], intOrcY[intOrcCounter]);
        }
        // if statement to when the player reaches the view distance of the orc.
        if (dist(intPlayerX, intPlayerY, intOrcX[intOrcCounter], intOrcY[intOrcCounter]) <= intOrcViewDistance) {
          // if statement for orc left movement animation.
          if (blnOrcMoving[intOrcCounter] && blnOrcMoveLeft[intOrcCounter] && !blnOrcMoveRight[intOrcCounter]) {
            blnOrcMoveRight[intOrcCounter] = false;
            if (intOrcMoveTick[intOrcCounter] > intOrcTakeStep[intOrcCounter]) {
              blnOrcStep[intOrcCounter] = !blnOrcStep[intOrcCounter];
              intOrcMoveTick[intOrcCounter] = 0;
            }
            if (blnOrcStep[intOrcCounter]) {
              image(imgOrcLeft1, intOrcX[intOrcCounter], intOrcY[intOrcCounter]);
            }
            else {
              image(imgOrcLeft2, intOrcX[intOrcCounter], intOrcY[intOrcCounter]);
            }
          }
          // if statement for orc right movement animation and sets the right image to false.
          if (blnOrcMoving[intOrcCounter] && blnOrcMoveRight[intOrcCounter]) {
            if (intOrcMoveTick[intOrcCounter] > intOrcTakeStep[intOrcCounter]) {
              blnOrcStep[intOrcCounter] = !blnOrcStep[intOrcCounter];
              intOrcMoveTick[intOrcCounter] = 0;
            }
            if (blnOrcStep[intOrcCounter]) {
              image(imgOrcRight1, intOrcX[intOrcCounter], intOrcY[intOrcCounter]);
            }
            else {
              image(imgOrcRight2, intOrcX[intOrcCounter], intOrcY[intOrcCounter]);
            }
            if (dist(intPlayerX, intPlayerY, intOrcX[intOrcCounter], intOrcY[intOrcCounter]) <= 100) {
              blnOrcMoveRight[intOrcCounter] = false;
            }
          }
          
          // if statements to move the orc in the direction of the player triggering the movement animations.
          if (intPlayerX < intOrcX[intOrcCounter] && intPlayerY < intOrcY[intOrcCounter]) {
            intOrcX[intOrcCounter] -= intOrcSpeed;
            intOrcY[intOrcCounter] -= intOrcSpeed;
            blnOrcMoving[intOrcCounter] = true;
            blnOrcMoveLeft[intOrcCounter] = true;
            intOrcMoveTick[intOrcCounter]++;
          }
          if (intPlayerX < intOrcX[intOrcCounter] && intPlayerY > intOrcY[intOrcCounter]) {
            intOrcX[intOrcCounter] -= intOrcSpeed;
            intOrcY[intOrcCounter] += intOrcSpeed;
            blnOrcMoving[intOrcCounter] = true;
            blnOrcMoveLeft[intOrcCounter] = true;
            intOrcMoveTick[intOrcCounter]++;
          }
          if (intPlayerX > intOrcX[intOrcCounter] && intPlayerY < intOrcY[intOrcCounter]) {
            intOrcX[intOrcCounter] += intOrcSpeed;
            intOrcY[intOrcCounter] -= intOrcSpeed;
            blnOrcMoving[intOrcCounter] = true;
            blnOrcMoveRight[intOrcCounter] = true;
            intOrcMoveTick[intOrcCounter]++;
          }
          if (intPlayerX > intOrcX[intOrcCounter] && intPlayerY > intOrcY[intOrcCounter])  {
            intOrcX[intOrcCounter] += intOrcSpeed;
            intOrcY[intOrcCounter] += intOrcSpeed;
            blnOrcMoving[intOrcCounter] = true;
            blnOrcMoveRight[intOrcCounter] = true;
            intOrcMoveTick[intOrcCounter]++;
          }
          // if statement to detect the distance between the orcs attack range and the players location.
          if (dist(intPlayerX, intPlayerY, intOrcX[intOrcCounter], intOrcY[intOrcCounter]) <= intOrcAttackRange) {
            System.out.println(intPlayerHp);
            intPlayerHp -= intOrcDamage;
          }
          if (intOrcHp[intOrcCounter] <= 0) {
            blnOrcHideStatus[intOrcCounter] = true;
          }
        }
        // else statement to set all the orc movement variables to false.
        else {
          blnOrcMoving[intOrcCounter] = false;
          blnOrcMoveDown[intOrcCounter] = false;
          blnOrcMoveLeft[intOrcCounter] = false;
          blnOrcMoveRight[intOrcCounter] = false;
          blnOrcMoveUp[intOrcCounter] = false;
        }
      }
    }
  }
  
/**
 * Tank method that stores all of the tank code.
 */
  public void Tank() {
    // if statement to draw the tank if the hide status is false.
    if (blnTankHideStatus == false) { 
      // if statement to draw the tank image while standing still and facing left setting the facing left variable to true and right facing variable to false.
      if (blnTankMoving == false && blnTankAlive == true && intPlayerX <= intTankX) {
        blnTankFaceLeft = true;
        blnTankFaceRight = false;
        image(imgTankFaceLeft1, intTankX, intTankY);
      }
      // else if statement to draw the tank image while standing still and facing right setting the facing right variable to true and left facing variable to false.
      else if (blnTankMoving == false && blnTankAlive == true && intPlayerX >= intTankX) {
        blnTankFaceRight = true;
        blnTankFaceLeft = false;
        image(imgTankFaceRight1, intTankX, intTankY);

      }
      // Tank death animation method.
      tankDeathAnimation();
      
      // Cannonball method
      tankCannonball();

      // Boss healthbar and name code.
      fill(255);
      textSize(20);
      stroke(0);
      text("Tanker the Wheeless", 100, 660);
      
      noStroke();
      fill(255, 0, 0);
      rect(100, 670, fltTankHp, 50);

      stroke(0);
      noFill();
      rect(100, 670, 600, 50);
    }
    else {
      // Draws the you win screen when the tank dies.
      image(imgYouWinScreen, width/2, height/2, 800, 800);
    }
  }

  /**
   * Tank cannonball method that draws the tank cannonball animation and cannonball shooting code.
   */
  public void tankCannonball() {
    // if statement to determine if the player is within the tanks view distance.
    if (dist(intPlayerX, intPlayerY, intTankX, intTankY) <= intTankViewDistance) {
      // if statement to determine if the playerX location is further than the tank ttack distance.
      if (dist(intPlayerX, intPlayerY, intTankX, intTankY) > intTankAttackDistance ) {
        intTankX -= 10;
      }
      // if statment to determine if the player is inside of the tanks attack distance.
      if (dist(intPlayerX, intPlayerY, intTankX, intTankY) <= intTankAttackDistance) {
        // if statment to determine of the tank is facing left.
        if (blnTankFaceLeft == true) {
          // if statement to set the cannonball launched boolean to true, temporary cannonball fly X and Y variables to the player location alongside resetting the cannonball X and Y coordinates before drawing the cannonball.
          if (frameCount % 60 == 0) {
            blnCannonballLaunched = true;
            intCannonballFlyX = intPlayerX;
            intCannonballFlyY = intPlayerY;
            intCannonballX = intTankX - 50;
            intCannonballY = intTankY - 50;
            image(imgCannonBall1, intCannonballX, intCannonballY);
          }
          // if statemnet to draw the cannonball animation if the cannonball launched boolean is true.
          if (blnCannonballLaunched == true) {
            if (blnCannonballMoving) {
              if (intCannonballMoveTick > intCannonballTakeStep) {
                blnCannonballStep = !blnCannonballStep;
                intCannonballMoveTick = 0;
              }
              if (blnCannonballStep) {
                image(imgCannonBall1, intCannonballX, intCannonballY);
              }
              else {
                image(imgCannonBall2, intCannonballX, intCannonballY);
              }
            }
            // if statement to reset the cannonball X and Y coordinates, setting the cannonball launched boolean to false, removing player hp, setting the cannonball cooldown to 0, if the distance of the cannonball hits the player hitbox. 
            if (dist(intPlayerX, intPlayerY, intCannonballX, intCannonballY) <= intPlayerHitBox) {
              blnCannonballLaunched = false;
              intCannonballX = intTankX - 50;
              intCannonballY = intTankY - 50;
              intCannonballFlyX = intTankX - 50;
              intCannonballFlyY = intTankY - 50;
              intPlayerHp -= 150;
              intCannonballCooldown = 0;
            }
            // if statement to reset the cannon ball X and Y variables, the temporary variables, cannonball launched boolean to false, cannonball cooldown to 0, if the cannonball reaches the temporary cannonball location.
            if (dist(intCannonballX, intCannonballY, intCannonballFlyX, intCannonballFlyY) <= 10) {
              blnCannonballLaunched = false;
              intCannonballX = intTankX - 50;
              intCannonballY = intTankY - 50;
              intCannonballFlyX = intTankX - 50;
              intCannonballFlyY = intTankY - 50;
              intCannonballCooldown = 0;
            }        
          }
        }
        // else if statement to do the same thing the if statement with the tank facing left but with the tank facing right.
        else if (blnTankFaceRight == true) {
          if (frameCount % 60 == 0) {
            blnCannonballLaunched = true;
            intCannonballFlyX = intPlayerX;
            intCannonballFlyY = intPlayerY;
            intCannonballX = intTankX + 50;
            intCannonballY = intTankY - 50;
            image(imgCannonBall1, intCannonballX, intCannonballY);
          }
          if (blnCannonballLaunched == true) {
            if (blnCannonballMoving) {
              if (intCannonballMoveTick > intCannonballTakeStep) {
                blnCannonballStep = !blnCannonballStep;
                intCannonballMoveTick = 0;
              }
              if (blnCannonballStep) {
                image(imgCannonBall1, intCannonballX, intCannonballY);
              }
              else {
                image(imgCannonBall2, intCannonballX, intCannonballY);
              }
            }
            if (dist(intPlayerX, intPlayerY, intCannonballX, intCannonballY) <= intPlayerHitBox) {
              blnCannonballLaunched = false;
              intCannonballX = intTankX + 50;
              intCannonballY = intTankY - 50;
              intCannonballFlyX = intTankX + 50;
              intCannonballFlyY = intTankY - 50;
              intPlayerHp -= 150;
              intCannonballCooldown = 0;
            }
            if (dist(intCannonballX, intCannonballY, intCannonballFlyX, intCannonballFlyY) <= 10) {
              blnCannonballLaunched = false;
              intCannonballX = intTankX + 50;
              intCannonballY = intTankY - 50;
              intCannonballFlyX = intTankX + 50;
              intCannonballFlyY = intTankY - 50;
              intCannonballCooldown = 0;
            }        
          }
        }
      }
      
      // if statements to move the cannonball to the temporary cannonball coordinates alongside triggering the cannonball animation.
      if (intCannonballFlyX < intCannonballX) {
        intCannonballX = intCannonballX - intCannonballSpeed;
        blnCannonballMoving = true;
        intCannonballMoveTick++;
      }
      if (intCannonballFlyX > intCannonballX) {
        intCannonballX = intCannonballX + intCannonballSpeed;
        blnCannonballMoving = true;
        intCannonballMoveTick++;
      }
      if (intCannonballY < intCannonballFlyY) {
        intCannonballY = intCannonballY + intCannonballSpeed;
        blnCannonballMoving = true;
        intCannonballMoveTick++;
      }
      if (intCannonballY > intCannonballFlyY) {
        intCannonballY = intCannonballY - intCannonballSpeed;
        blnCannonballMoving = true;
        intCannonballMoveTick++;
      }
      // if statment to reset the cannonball cooldown
      if (intCannonballCooldown < 60) {
        intCannonballCooldown++;
      }
    }
  }

  /**
   * Tank death animation method to draw the tank deaht animation when the tank hp is zero.
   */
  public void tankDeathAnimation() {
    // if statement to keep the tank hp at 0 and the blnTankAlive to false.
    if (fltTankHp <= 0) {
      fltTankHp = 0;
      blnTankAlive = false;
      // if statement to check if the blnTankAlive is false.
      if (blnTankAlive == false) {
        // if and else statments to draw the tank explode animation
        if (intMoveFrames == 1){
          image(imgTankExplode[intMoveFrames], intTankX, intTankY);
        }
        else {
          image(imgTankExplode[intMoveFrames], intTankX, intTankY);
        }
        // if statement to draw the seperate frames after a certian amount of frames and finally set the blnTankHideStatus to true;
        if (intMoveFrames >= 0) {
          if (frameCount % 60 == 0) {
            intMoveFrames = 1;
          }
          if (frameCount % 120 == 0 && intMoveFrames < 2) {
            intMoveFrames = 2;
          }
          if (frameCount % 160 == 0) {
            blnTankHideStatus = true;
          }
        }
      }
    }
  }

  /**
   * Player edge detection method to keep the player inside of the drawn area.
   */
  public void edgeDetection() {
    if (intPlayerX - 20 <= 0) {
      intPlayerX = 20;
    }
    if (intPlayerX + 20 >= width) {
      intPlayerX = 780;
    }
    if (intPlayerY - 20 <= 0) {
      intPlayerY = 20;
    }
    if (intPlayerY + 20 >= height) {
      intPlayerY = 780;
    }
  }

  /**
   * Player movement method to move the player if the keys WASD are pressed.
   */
  public void playerMovement() {
    // Player Movement
    if (blnWPressed) {
      intPlayerY -= intPlayerSpeed;
    }
    if (blnSPressed) {
      intPlayerY += intPlayerSpeed;
    }
    if (blnAPressed) {
      intPlayerX -= intPlayerSpeed;
    }
    if (blnDPressed) {
      intPlayerX += intPlayerSpeed;
    }
  }

  /**
   * Player dash method to move the player a certian amount depending on which direction they are moving when the dash key is pressed.
   */
  public void playerDash() {
    // if statement to move the player when the dash key (spacebar) is pressed.
    if (blnDashPressed == true && blnDashReady == true) {
      blnDashReady = false;
      if (blnWPressed == true){
        intPlayerY -= intDashDistance;
      }
      if (blnAPressed == true){
        intPlayerX -= intDashDistance;
      }
      if (blnSPressed == true){
        intPlayerY += intDashDistance;
      }
      if (blnDPressed == true){
        intPlayerX += intDashDistance;
      }
      if (blnWPressed == true && blnAPressed == true){
        intPlayerX -= intDashDistance;
        intPlayerY -= intDashDistance;
      }
      if (blnWPressed == true && blnDPressed == true){
        intPlayerX += intDashDistance;
        intPlayerY -= intDashDistance;
      }
      if (blnSPressed == true && blnAPressed == true){
        intPlayerX -= intDashDistance;
        intPlayerY += intDashDistance;
      }
      if (blnSPressed == true && blnDPressed == true){
        intPlayerX += intDashDistance;
        intPlayerX += intDashDistance;
      }
    }
    // if statement for a 3 second dash cooldown.
    if (blnDashReady == false) {
      if (frameCount % 180 == 0) {
        blnDashReady = true;
      }
    }
  }

  /**
   * Player movement method for when the keys are pressed
   */
  public void keyPressed() {
    // if and if else statements to set the boolean values to true and activated the player animation.
    if (key == 'w' || key == 'W') {
      blnWPressed = true;
      blnMoving = true;
      blnMovingUp = true;
      intPlayerMoveTick++;
    }
    else if (key == 'a' || key == 'A') {
      blnAPressed = true;
      blnMoving = true;
      blnMovingLeft = true;
      intPlayerMoveTick++;
    }
    else if (key == 's' || key == 'S'){
      blnSPressed = true;
      blnMoving = true;
      blnMovingDown = true;
      intPlayerMoveTick++;
    }
    else if (key == 'd' || key == 'D'){
      blnDPressed = true;
      blnMoving = true;
      blnMovingRight = true;
      intPlayerMoveTick++;
    }   
    if (key == ' ') {
      blnDashPressed = true;
    }
  }

  /**
   * Player movement method for when the keys are released.
   */
  public void keyReleased() {
    // if  and if else statements to set the boolean values to false except the face it was pointing
    if (key == 'w' || key == 'W') {
      blnWPressed = false;
      blnMoving = false;
      blnMovingUp = false;
      blnFaceUp = true;
      blnFaceDown = false;
      blnFaceRight = false;
      blnFaceLeft = false;
    }
    else if (key == 'a' || key == 'A') {
      blnAPressed = false;
      blnMoving = false;
      blnMovingLeft = false;
      blnFaceUp = false;
      blnFaceDown = false;
      blnFaceRight = false;
      blnFaceLeft = true;
    }
    else if (key == 's' || key == 'S'){
      blnSPressed = false;
      blnMoving = false;
      blnMovingDown = false;
      blnFaceUp = false;
      blnFaceDown = true;
      blnFaceRight = false;
      blnFaceLeft = false;
    }
    else if (key == 'd' || key == 'D'){
      blnDPressed = false;
      blnMoving = false;
      blnMovingRight = false;
      blnFaceUp = false;
      blnFaceDown = false;
      blnFaceRight = true;
      blnFaceLeft = false;
    } 
    if (key == ' ') {
      blnDashPressed = false;
    }
  }


  
}

  






