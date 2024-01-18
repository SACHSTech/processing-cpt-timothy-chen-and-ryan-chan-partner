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

  // Orc Variables
  float[] fltOrcX = new float[10];
  float[] fltOrcY = new float[10];
  boolean [] blnOrcHideStatus = new boolean[10];
  float fltOrcHp = 100;
  float fltOrcDirectionX;
  float fltOrcDirectionY;
  int intOrcViewDistance = 200;
  int intOrcAttackRange = 20;
  int intOrcSpeed = 2;
  int intMoveTick = 0;
  int intTakeStep = 5;
  boolean[] blnOrcMoving = new boolean[10];
  boolean[] blnOrcMoveRight = new boolean[10];
  boolean[] blnOrcMoveLeft = new boolean[10];
  boolean[] blnOrcMoveUp = new boolean[10];
  boolean[] blnOrcMoveDown = new boolean[10];
  boolean[] blnStep = new boolean[10];
  

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
    imgGrassBackground = loadImage("Grass background.jpg");
    imgBrickBackground = loadImage("Brick Background.jpg");
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
    imgOrcRight1.resize(20, 20);
    imgOrcRight2.resize(20, 20);
    imgOrcLeft1.resize(20, 20);
    imgOrcLeft2.resize(20, 20);
    

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
      blnStep[intBooleanCount] = false;
    }

   
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // Image Background
    image(imgGrassBackground, width/2, height/2, 800, 800);
    // image(imgBrickBackground, width/2, -800, 800, 800);
    
    rect(intPlayerX, intPlayerY, 40, 40);
    
    
    for (int intOrcCounter = 0; intOrcCounter < fltOrcY.length; intOrcCounter++) {
      // if statement to set the blnOrcHideStatus for each of the Orcs to false.
      if (blnOrcHideStatus[intOrcCounter] == false) { 
        if (blnOrcMoving[intOrcCounter] == false) {
          image(imgOrcDown1, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);
        }
        
        if (dist(intPlayerX, intPlayerY, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]) <= intOrcViewDistance) {
          
          
          if (blnOrcMoving[intOrcCounter] && blnOrcMoveLeft[intOrcCounter] && !blnOrcMoveRight[intOrcCounter]) {
            blnOrcMoveRight[intOrcCounter] = false;

            if (intMoveTick > intTakeStep) {
              blnStep[intOrcCounter] = !blnStep[intOrcCounter];
              intMoveTick = 0;
            }

            if (blnStep[intOrcCounter]) {
              image(imgOrcLeft1, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);
            }
            else {
              image(imgOrcLeft2, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);
            }
            
          }
          if (blnOrcMoving[intOrcCounter] && blnOrcMoveRight[intOrcCounter]) {
            if (intMoveTick > intTakeStep) {
              blnStep[intOrcCounter] = !blnStep[intOrcCounter];
              intMoveTick = 0;
            }

            if (blnStep[intOrcCounter]) {
              image(imgOrcRight1, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);
            }
            else {
              image(imgOrcRight2, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);
            }
            if (dist(intPlayerX, intPlayerY, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]) <= 100) {
              blnOrcMoveRight[intOrcCounter] = false;
            }

          }
          
          /*
          else if (blnOrcMoving[intOrcCounter] && blnOrcMoveRight) {
            if (intMoveTick > intTakeStep) {
              blnStep = !blnStep;
              intMoveTick = 0;
            }

            if (blnStep) {
              image(imgOrcRight1, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);
            }
            else {
              image(imgOrcRight2, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);
            }
          }
          */
          
          

          if(intPlayerX < fltOrcX[intOrcCounter] && intPlayerY < fltOrcY[intOrcCounter]) {
            
            fltOrcX[intOrcCounter] -= intOrcSpeed;
            fltOrcY[intOrcCounter] -= intOrcSpeed;
            
            blnOrcMoving[intOrcCounter] = true;
            blnOrcMoveLeft[intOrcCounter] = true;
            intMoveTick++;
            
          }
          if (intPlayerX < fltOrcX[intOrcCounter] && intPlayerY > fltOrcY[intOrcCounter]) {
            fltOrcX[intOrcCounter] -= intOrcSpeed;
            fltOrcY[intOrcCounter] += intOrcSpeed;

            blnOrcMoving[intOrcCounter] = true;
            blnOrcMoveLeft[intOrcCounter] = true;
            intMoveTick++;
          }
          if (intPlayerX > fltOrcX[intOrcCounter] && intPlayerY < fltOrcY[intOrcCounter]) {
            fltOrcX[intOrcCounter] += intOrcSpeed;
            fltOrcY[intOrcCounter] -= intOrcSpeed;

            blnOrcMoving[intOrcCounter] = true;
            blnOrcMoveRight[intOrcCounter] = true;
            intMoveTick++;
          }
          if (intPlayerX > fltOrcX[intOrcCounter] && intPlayerY > fltOrcY[intOrcCounter])  {
            fltOrcX[intOrcCounter] += intOrcSpeed;
            fltOrcY[intOrcCounter] += intOrcSpeed;

            blnOrcMoving[intOrcCounter] = true;
            blnOrcMoveRight[intOrcCounter] = true;
            intMoveTick++;
          }
          if (dist(intPlayerX, intPlayerY, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]) <= intOrcAttackRange) {

          }
        }
        
        else {
          blnOrcMoving[intOrcCounter] = false;
          blnOrcMoveDown[intOrcCounter] = false;
          blnOrcMoveLeft[intOrcCounter] = false;
          blnOrcMoveRight[intOrcCounter] = false;
          blnOrcMoveUp[intOrcCounter] = false;
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


