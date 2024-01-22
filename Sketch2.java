import processing.core.PApplet;
import processing.core.PImage;

public class Sketch2 extends PApplet {
	
  int intWorldX = 4800;
  int intWorldY = 4800;
  
  // Player variables
  int intPlayerX;
  int intPlayerY;
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
  float[] fltOrcHp = new float[10];
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
  PImage imgTankExplode1;
  PImage imgTankExplode2;
  PImage imgTankExplode3;
  PImage imgTankFaceLeft1; 
  PImage imgTankFaceLeft2; 
  PImage imgTankFaceRight1;
  PImage imgTankFaceRight2;
  PImage imgTankHitLeft;
  PImage imgTankhitRight;

  // Tank Boss Variables
  float fltTankHp = 100;
  int intTankX = 400;
  int intTankY = 200;
  int intTankViewDistance;
  int intTankAttackDistance;
  int intTankAttackDamage;
  int intTankSpeed;
  int intTankMoveTick;
  int intTankMoving;
  boolean blnTankHideStatus;
  boolean blnTankMoving;
  boolean blnTankExplode1 = false;
  boolean blnTankExplode2 = false;
  boolean blnTankExplode3 = false;
  

  
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
    imgTankExplode1 = loadImage("Tank Explode_1.png");
    imgTankExplode2 = loadImage("Tank Explode_2.png");
    imgTankExplode3 = loadImage("Tank Explode_3.png");
    imgTankFaceLeft1 = loadImage("Tank Face Left_1.png"); 
    imgTankFaceLeft2 = loadImage("Tank Face Left_2.png");
    imgTankFaceRight1 = loadImage("Tank Face Right_1.png");
    imgTankFaceRight2 = loadImage("Tank Face Right_2.png");
    imgTankHitLeft = loadImage("Tank Hit Left.png");
    imgTankhitRight = loadImage("Tank Hit Right.png");

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
    for (int intBooleanCount = 0; intBooleanCount < blnOrcHideStatus.length; intBooleanCount++){
      blnOrcHideStatus[intBooleanCount] = false;
      blnOrcMoving[intBooleanCount] = false;
      blnOrcMoveRight[intBooleanCount] = false;
      blnOrcMoveLeft[intBooleanCount] = false;
      blnOrcMoveUp[intBooleanCount] = false;
      blnOrcMoveDown[intBooleanCount] = false;
      blnOrcStep[intBooleanCount] = false;
      intOrcMoveTick[intBooleanCount] = 0;
      intOrcTakeStep[intBooleanCount] = 5;
      fltOrcHp[intBooleanCount] = 100;

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
    rect(intPlayerX, intPlayerY, intPlayerHitBox, intPlayerHitBox);
    
    // For loop to for a counter for the array length. 
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
          if (fltOrcHp[intOrcCounter] == 0) {
            blnOrcHideStatus[intOrcCounter] = true;
          }
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
    

    // Tank Code
    for (int intTankCounter = 0; intTankCounter < fltOrcY.length; intTankCounter++) {
      if (blnTankHideStatus == false) { 
        // if statement to draw the orc image while standing still.
          
        if (blnTankMoving == false) {
          image(imgTankFaceLeft1, intTankX, intTankY);

        }
        if (dist(intPlayerX, intPlayerY, intTankX, intTankY) <= 400) {
          fltTankHp--;
          System.out.println(fltTankHp);
          
        }
        if (fltTankHp <= 0) {
          fltTankHp = 0;
          blnTankMoving = true;
          if (blnTankExplode1) {
            
          }
        }
        
        
      }
      
      
    }
    
    

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

  // Player movement method.
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


