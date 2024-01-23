import processing.core.PApplet;
import processing.core.PImage;

public class Sketch2 extends PApplet {
	
  int intWorldX = 4800;
  int intWorldY = 4800;
  
  // Player variables
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
  PImage imgOrcDown2;
  PImage imgOrcLeft1;
  PImage imgOrcLeft2;
  PImage imgOrcRight1;
  PImage imgOrcRight2;
  PImage imgOrcUp1;
  PImage imgOrcUp2;

  // Orc Variables
  float[] fltOrcX = new float[10];
  float[] fltOrcY = new float[10];
  int[] intOrcHp = new int[10];
  float fltOrcDirectionX;
  float fltOrcDirectionY;
  int intOrcViewDistance = 200;
  int intOrcAttackRange = 40;
  int intOrcSpeed = 4;
  int intOrcDamage;
  int[] intOrcMoveTick = new int[10];
  int[] intOrcTakeStep = new int[10];
  boolean [] blnOrcHideStatus = new boolean[10];
  boolean[] blnOrcMoving = new boolean[10];
  boolean[] blnOrcMoveRight = new boolean[10];
  boolean[] blnOrcMoveLeft = new boolean[10];
  boolean[] blnOrcMoveUp = new boolean[10];
  boolean[] blnOrcMoveDown = new boolean[10];
  boolean[] blnOrcStep = new boolean[10];
  
  // Tank Image Variables
  PImage imgCannonBall1;
  PImage imgCannonBall2;
  PImage[] imgTankExplode;
  PImage imgTankFaceLeft1; 
  PImage imgTankFaceLeft2; 
  PImage imgTankFaceRight1;
  PImage imgTankFaceRight2;
  PImage imgTankHitLeft;
  PImage imgTankhitRight;

  // Tank Boss Variables
  int intNumFrames = 3;
  int intMoveFrames = 0;
  int intNumTankExplode = 2;
  int intTankHp = 1000;
  int intTankX = 700;
  int intTankY = 400;
  int intTankViewDistance = 600;
  int intTankAttackDistance = 500;
  int intTankAttackDamage;
  int intTankSpeed;
  int intTankMoveTick;
  int intTankMoving;
  int intTankHitBox = 110;
  int[] intCannonballX = new int[50];
  int[] intCannonballY = new int[50];
  int[] intCannonballCooldown = new int[50];
  int[] intCannonballFlyX = new int[50];
  int[] intCannonballFlyY = new int[50];
  boolean blnTankHideStatus;
  boolean blnTankMoving;
  boolean blnTankAlive = true;
  boolean blnTankExplode1 = false;
  boolean blnTankExplode2 = false;
  boolean blnTankExplode3 = false;
  boolean[] blnCannonBallLaunched = new boolean[50]; 

  

  
  // Background Images
  PImage imgGrassBackground;
  PImage imgBrickBackground;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	  // put your size call here
    size(800, 800);
    intPlayerX = width/2;
    intPlayerY = height/2;

    imgOrcDown1 = loadImage("orc_down_1.png");
    imgOrcDown2 = loadImage("orc_down_2.png");
    imgOrcLeft1 = loadImage("orc_left_1.png");
    imgOrcLeft2 = loadImage("orc_left_2.png");
    imgOrcRight1 = loadImage("orc_right_1.png");
    imgOrcRight2 = loadImage("orc_right_2.png");
    imgOrcUp1 = loadImage("orc_up_1.png");
    imgOrcUp2 = loadImage("orc_up_2.png");
    imgGrassBackground = loadImage("Grass background.jpg");
    imgBrickBackground = loadImage("Brick Background.jpg");
    imgCannonBall1 = loadImage("Cannon Ball_1.png");
    imgCannonBall2 = loadImage("Cannon Ball_2.png");
    imgTankFaceLeft1 = loadImage("Tank Face Left_1.png"); 
    imgTankFaceLeft2 = loadImage("Tank Face Left_2.png");
    imgTankFaceRight1 = loadImage("Tank Face Right_1.png");
    imgTankFaceRight2 = loadImage("Tank Face Right_2.png");
    imgTankHitLeft = loadImage("Tank Hit Left.png");
    imgTankhitRight = loadImage("Tank Hit Right.png");
    imgTankExplode = new PImage[3];
    for (int TankExplodeCount = 0; TankExplodeCount < 3; TankExplodeCount++) {
      imgTankExplode[TankExplodeCount] = loadImage("Tank Explode_" + TankExplodeCount + ".png");
    }

  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    imageMode(CENTER);
    rectMode(CENTER);
    ellipseMode(CENTER);
    frameRate(60);
    
    imgGrassBackground.resize(200, 200);
    imgBrickBackground.resize(200, 200);
    imgOrcDown1.resize(30, 30);
    imgOrcRight1.resize(30, 30);
    imgOrcRight2.resize(30, 30);
    imgOrcLeft1.resize(30, 30);
    imgOrcLeft2.resize(30, 30);
    imgTankFaceLeft1.resize(150, 150);
    imgCannonBall1.resize(20, 20);
    imgCannonBall2.resize(20, 20);
    
    

    image(imgGrassBackground, width/2, height/2, 800, 800);
    image(imgBrickBackground, width/2, -800, 800, 800);

    for (int intOrcY = 0; intOrcY < fltOrcY.length; intOrcY++) {
      fltOrcY[intOrcY] = random(800);
    }
    
    // For loop to initialise the snowflakes on the x coordinate setting them to a random location across the screen.
    for (int intOrcX = 0; intOrcX < fltOrcX.length; intOrcX++) {
      fltOrcX[intOrcX] = random(800);
    }
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
      

    }

    for (int intCounterCannonball = 0; intCounterCannonball < intCannonballX.length; intCounterCannonball++) {
      intCannonballX[intCounterCannonball] = intTankX - 50;
      intCannonballY[intCounterCannonball] = intTankY - 50;
      intCannonballFlyX[intCounterCannonball] = 0;
      intCannonballFlyY[intCounterCannonball] = 0;
      intCannonballCooldown[intCounterCannonball] = 60;
      blnCannonBallLaunched[intCounterCannonball] = false;

    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // Image Background
    image(imgGrassBackground, width/2, height/2, 800, 800);
    image(imgBrickBackground, width/2, -800, 800, 800);
    
    // Temporary Player Rectangle
    noFill();
    stroke(0);
    rect(intPlayerX, intPlayerY, intPlayerHitBox, intPlayerHitBox);
    
    // For loop to for a counter for the array length. 
    Orc();
    Tank();
    playerDash();
    playerMovement();
    edgeDetection();
    
    
    
    

    

      
    }

  public void Orc() {
    for (int intOrcCounter = 0; intOrcCounter < fltOrcY.length; intOrcCounter++) {
      // if statement to set the blnOrcHideStatus for each of the Orcs to false.
      if (blnOrcHideStatus[intOrcCounter] == false) { 
        
        
        // if statement to draw the orc image while standing still.
        if (blnOrcMoving[intOrcCounter] == false) {
          image(imgOrcDown1, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);
        }
        // if statement to when the player reaches the view distance of the orc.
        if (dist(intPlayerX, intPlayerY, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]) <= intOrcViewDistance) {
          
          // if statement for orc left movement animation.
          if (blnOrcMoving[intOrcCounter] && blnOrcMoveLeft[intOrcCounter] && !blnOrcMoveRight[intOrcCounter]) {
            blnOrcMoveRight[intOrcCounter] = false;

            if (intOrcMoveTick[intOrcCounter] > intOrcTakeStep[intOrcCounter]) {
              blnOrcStep[intOrcCounter] = !blnOrcStep[intOrcCounter];
              intOrcMoveTick[intOrcCounter] = 0;
            }

            if (blnOrcStep[intOrcCounter]) {
              image(imgOrcLeft1, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);
            }
            else {
              image(imgOrcLeft2, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);
            }
          }
          // if statement for orc right movement animation and sets the right image to false.
          if (blnOrcMoving[intOrcCounter] && blnOrcMoveRight[intOrcCounter]) {
            if (intOrcMoveTick[intOrcCounter] > intOrcTakeStep[intOrcCounter]) {
              blnOrcStep[intOrcCounter] = !blnOrcStep[intOrcCounter];
              intOrcMoveTick[intOrcCounter] = 0;
            }

            if (blnOrcStep[intOrcCounter]) {
              image(imgOrcRight1, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);
            }
            else {
              image(imgOrcRight2, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);
            }
            if (dist(intPlayerX, intPlayerY, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]) <= 100) {
              blnOrcMoveRight[intOrcCounter] = false;
            }

          }
          
          // if statements to move the orc in the direction of the player triggering the movement animations.
          if(intPlayerX < fltOrcX[intOrcCounter] && intPlayerY < fltOrcY[intOrcCounter]) {
            
            fltOrcX[intOrcCounter] -= intOrcSpeed;
            fltOrcY[intOrcCounter] -= intOrcSpeed;
            
            blnOrcMoving[intOrcCounter] = true;
            blnOrcMoveLeft[intOrcCounter] = true;
            intOrcMoveTick[intOrcCounter]++;
            
          }
          if (intPlayerX < fltOrcX[intOrcCounter] && intPlayerY > fltOrcY[intOrcCounter]) {
            fltOrcX[intOrcCounter] -= intOrcSpeed;
            fltOrcY[intOrcCounter] += intOrcSpeed;

            blnOrcMoving[intOrcCounter] = true;
            blnOrcMoveLeft[intOrcCounter] = true;
            intOrcMoveTick[intOrcCounter]++;
          }
          if (intPlayerX > fltOrcX[intOrcCounter] && intPlayerY < fltOrcY[intOrcCounter]) {
            fltOrcX[intOrcCounter] += intOrcSpeed;
            fltOrcY[intOrcCounter] -= intOrcSpeed;

            blnOrcMoving[intOrcCounter] = true;
            blnOrcMoveRight[intOrcCounter] = true;
            intOrcMoveTick[intOrcCounter]++;
          }
          if (intPlayerX > fltOrcX[intOrcCounter] && intPlayerY > fltOrcY[intOrcCounter])  {
            fltOrcX[intOrcCounter] += intOrcSpeed;
            fltOrcY[intOrcCounter] += intOrcSpeed;

            blnOrcMoving[intOrcCounter] = true;
            blnOrcMoveRight[intOrcCounter] = true;
            intOrcMoveTick[intOrcCounter]++;
            
          }
          // if statement to detect the distance between the orcs attack range and the players location.
          if (dist(intPlayerX, intPlayerY, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]) <= intOrcAttackRange) {
            // intPlayerHp - intOrcDamage;
          }
          if (intOrcHp[intOrcCounter] == 0) {
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

  public void Tank() {
    // Tank Code
    for (int intTankCounter = 0; intTankCounter < intCannonballX.length; intTankCounter++) {
      if (blnTankHideStatus == false) { 
         
         
        // if statement to draw the orc image while standing still.
          
        if (blnTankMoving == false && blnTankAlive == true) {
          rect(intTankX, intTankY, intTankHitBox, intTankHitBox);
          image(imgTankFaceLeft1, intTankX, intTankY);
        }
        if (dist(intPlayerX, intPlayerY, intTankX, intTankY) <= intTankViewDistance) {
          if (dist(intPlayerX, intPlayerY, intTankX, intTankY) > intTankAttackDistance) {
            
            
            if (intPlayerX < intTankX) {
              intTankX--;
            }
            
          }
          
            if (dist(intPlayerX, intPlayerY, intTankX, intTankY) <= intTankAttackDistance) {
              if (intCannonballCooldown[intTankCounter] == 60) {
                blnCannonBallLaunched[intTankCounter] = true;
                if (blnCannonBallLaunched[intTankCounter] == true) {
                  intCannonballFlyX[intTankCounter] = intPlayerX;
                  intCannonballFlyY[intTankCounter] = intPlayerY;
                  blnCannonBallLaunched[intTankCounter] = false;
                  
                }              
              }

              image(imgCannonBall1, intCannonballX[intTankCounter], intCannonballY[intTankCounter]);

              
              intCannonballCooldown[intTankCounter] = 0;
                
              
              
            }
            if (intCannonballFlyX[intTankCounter] < intCannonballX[intTankCounter]) {
              intCannonballX[intTankCounter]--;
            }
            if (intCannonballFlyX[intTankCounter] > intCannonballX[intTankCounter]) {
              intCannonballX[intTankCounter]++;
            }
            if (intCannonballY[intTankCounter] < intCannonballFlyY[intTankCounter]) {
              intCannonballY[intTankCounter]++;
            }
            if (intCannonballY[intTankCounter] > intCannonballFlyY[intTankCounter]) {
              intCannonballY[intTankCounter]--;
            }
            noStroke();
            fill(255, 0, 0);
            rect(400, 750, intTankHp, 50);

            stroke(0);
            noFill();
            rect(400, 750, 600, 50);

            if (intCannonballCooldown[intTankCounter] < 60) {
              intCannonballCooldown[intTankCounter]++;
              //System.out.println(intCannonballCooldown[intTankCounter]);
            }
        }
        if (dist(intPlayerX, intPlayerY, intTankX, intTankY) <= intTankHitBox - 20) {
          
          // System.out.println(dist(intPlayerX, intPlayerY, intTankX, intTankY) <= intTankHitBox - 20);
          if (frameCount % 60 == 0) {
           
          }
        }

        if (intTankHp <= 0) {
          intTankHp = 0;
          blnTankAlive = false;
          
          
          
          if (blnTankAlive == false) {
            if (intMoveFrames == 1){
              image(imgTankExplode[intMoveFrames], intTankX, intTankY);
              
            }
            else {
              image(imgTankExplode[intMoveFrames], intTankX, intTankY);
            }

            
            
            if (intMoveFrames >= 0) {
              if (frameCount % 60 == 0) {
                intMoveFrames = 1;
              }
                
              if (frameCount % 120 == 0 && intMoveFrames < 2) {
                intMoveFrames = 2;
                System.out.println(intMoveFrames);
              }
              
              if (frameCount % 160 == 0) {
                blnTankHideStatus = true;
              }
              
              
            }
            
            
            
          }
        }
      }
      
    }
  }

  public void playerMovement() {
    // Player Movement
    if (blnWPressed) {
      intPlayerY -= intPlayerSpeed;
      intWorldY -= intPlayerSpeed;
    }
    if (blnSPressed) {
      intPlayerY += intPlayerSpeed;
      intWorldY += intPlayerSpeed;
    }
    if (blnAPressed) {
      intPlayerX -= intPlayerSpeed;
      intWorldX -= intPlayerSpeed;
    }
    if (blnDPressed) {
      intPlayerX += intPlayerSpeed;
      intWorldX += intPlayerSpeed;
    }
  }

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
  

  // Player movement method.
  public void keyPressed() {
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

  // Player movement method.
  public void keyReleased() {
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


