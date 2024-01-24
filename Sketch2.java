import processing.core.PApplet;
import processing.core.PImage;

/**
 * Sketch2 class to execute sketch2
 * @author RyanChan25
 *
 */
public class Sketch2 extends PApplet {

  // Player variables
  int intPlayerHp;
  int intPlayerX;
  int intPlayerY;
  int intPlayerMoveTick = 0;
  int intPlayerSpeed = 8;
  int intDashCooldown = 0;
  int intDashDistance = 20;
  int intPlayerHitBox = 40;
  boolean blnWPressed;
  boolean blnAPressed; 
  boolean blnSPressed;
  boolean blnDPressed;
  boolean blnDashReady = true;
  boolean blnDashPressed = false;
  boolean blnFaceRight = false;
  boolean blnFaceUp = false;
  boolean blnFaceLeft = false;
  boolean blnFaceDown = true;
  boolean blnMoving = false;
  boolean blnMovingLeft = false;
  boolean blnMovingRight = false;
  boolean blnMovingDown = false;
  boolean blnMovingUp = false;

  // Orc Images
  PImage imgOrcDown1;
  PImage imgOrcLeft1;
  PImage imgOrcLeft2;
  PImage imgOrcRight1;
  PImage imgOrcRight2;

  // Orc Variables
  int[] intOrcX = new int[300];
  int[] intOrcY = new int[300];
  int[] intOrcHp = new int[300];
  int[] intOrcMoveTick = new int[300];
  int[] intOrcTakeStep = new int[300];
  int intOrcDirectionX;
  int intOrcDirectionY;
  int intOrcViewDistance = 200;
  int intOrcAttackRange = 40;
  int intOrcSpeed = 4;
  int intOrcDamage;
  boolean [] blnOrcHideStatus = new boolean[300];
  boolean[] blnOrcMoving = new boolean[300];
  boolean[] blnOrcMoveRight = new boolean[300];
  boolean[] blnOrcMoveLeft = new boolean[300];
  boolean[] blnOrcMoveUp = new boolean[300];
  boolean[] blnOrcMoveDown = new boolean[300];
  boolean[] blnOrcStep = new boolean[300];
  
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
  int intTankAttackDamage;
  int intTankSpeed;
  int intTankMoveTick;
  int intTankMoving;
  int intTankHitBox = 110;
  int intCannonballX = intTankX - 50;
  int intCannonballY = intTankY - 50;
  int intCannonballCooldown = 60;
  int intCannonballFlyX = intTankX - 50;
  int intCannonballFlyY = intTankY - 50;
  int intCannonballMovement;
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
	  // Size Call
    size(800, 800);

    // Sets the intPlayerX and Y location.
    intPlayerX = width/2;
    intPlayerY = height/2;

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
    // Mode calls to change image mode to center.
    imageMode(CENTER);
    
    // Sets framerate of the game.
    frameRate(60);
    
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
    imgCannonBall1.resize(60, 60);
    imgCannonBall2.resize(60, 60);
    imgYouWinScreen.resize(200, 200);
    
    // Background
    image(imgGrassBackground, width/2, height/2, 800, 800);
    image(imgBrickBackground, width/2, -800, 800, 800);

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
    // Image Background
    // image(imgGrassBackground, width/2, height/2, 800, 800);
    image(imgBrickBackground, width/2, height/2, 800, 800);
    
    // Temp Player Rectangle
    fill(255, 0, 0);
    rect(intPlayerX - 20, intPlayerY - 20, intPlayerHitBox, intPlayerHitBox);

    // Methods
    // Orc method
    Orc();
    //Tank method
    Tank();
    // Player dash method
    playerDash();
    // Player movement method
    playerMovement();
    // Player edge detection method
    edgeDetection();
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
          if (frameCount % 120 == 0) {
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
          if (frameCount % 120 == 0) {
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


