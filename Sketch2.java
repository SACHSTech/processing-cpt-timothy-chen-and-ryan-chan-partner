import processing.core.PApplet;
import processing.core.PImage;

public class Sketch2 extends PApplet {
	

  int intWorldX = 4800;
  int intWorldY = 4800;
  
  // Player variables
  int intPlayerX;
  int intPlayerY;
  int intPlayerSpeed = 2;
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
  int intOrcViewDistance = 200;
  int intOrcAttackRange = 20;
  int intOrcSpeed = 4;
  float fltOrcDirectionX;
  float fltOrcDirectionY;

  

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
    

    image(imgGrassBackground, width/2, height/2, 800, 800);
    image(imgBrickBackground, width/2, -800, 800, 800);

    for (int intOrcY = 0; intOrcY < fltOrcY.length; intOrcY++) {
      fltOrcY[intOrcY] = random(400);
    }
    
    // For loop to initialise the snowflakes on the x coordinate setting them to a random location across the screen.
    for (int intOrcX = 0; intOrcX < fltOrcX.length; intOrcX++) {
      fltOrcX[intOrcX] = random(400);
    }
    // For loop to initialise the ballhidestatus and setting it to false.
    for (int intBooleanCount = 0; intBooleanCount < blnOrcHideStatus.length; intBooleanCount++){
      blnOrcHideStatus[intBooleanCount] = false;
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
      // if statement to set the blnBallHideStatus for each of the snowflakes to false.
      if (blnOrcHideStatus[intOrcCounter] == false) {
        
        image(imgOrcDown1, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]);

        // Variable to set the speed in which the snowflakes falls.
        // fltOrcY[intOrcCounter] += intOrcSpeed;
      }
      
      if (dist(intPlayerX, intPlayerY, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]) <= intOrcViewDistance) {
        for (int i = 0; i < platformNames.length; i++) {
          
        }
        if (intPlayerX < fltOrcX[intOrcCounter] && intPlayerY < fltOrcY[intOrcCounter]) {
          
          fltOrcX[intOrcCounter] -= intOrcSpeed;
          fltOrcY[intOrcCounter] -= intOrcSpeed;
        }
        else if (intPlayerX < fltOrcX[intOrcCounter] && intPlayerY > fltOrcY[intOrcCounter]) {
          fltOrcX[intOrcCounter] -= intOrcSpeed;
          fltOrcY[intOrcCounter] += intOrcSpeed;
        }
        else if (intPlayerX > fltOrcX[intOrcCounter] && intPlayerY < fltOrcY[intOrcCounter]) {
          fltOrcX[intOrcCounter] += intOrcSpeed;
          fltOrcY[intOrcCounter] -= intOrcSpeed;
        }
        else if (intPlayerX > fltOrcX[intOrcCounter] && intPlayerY > fltOrcY[intOrcCounter])  {
          fltOrcX[intOrcCounter] += intOrcSpeed;
          fltOrcY[intOrcCounter] += intOrcSpeed;
        }
        if (dist(intPlayerX, intPlayerY, fltOrcX[intOrcCounter], fltOrcY[intOrcCounter]) <= intOrcAttackRange) {

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


