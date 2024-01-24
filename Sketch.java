import processing.core.PApplet;
import processing.core.PImage;


public class Sketch extends PApplet {

  // Global Variables
  int intMenuSelect; 
  float fltPlayerHp;
  int intHpBar = 300;
  boolean blnPlayerAlive;
  int intPlayerHitBox = 40; 
  
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

  int intGlobalX;
  int intGlobalY;

  // Ryan Code
  int intWorldX = 4800;
  int intWorldY = 4800;
  
  // Player variables
  int intPlayerX;
  int intPlayerY;
  int intPlayerSpeed = 8;
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
  float[] fltOrcHp = new float[10];
  float fltOrcDirectionX;
  float fltOrcDirectionY;
  int intOrcViewDistance = 200;
  int intOrcAttackRange = 40;
  int intOrcSpeed = 4;
  int intOrcDamage = 1;
  int[] intOrcMoveTick = new int[10];
  int[] intOrcTakeStep = new int[10];
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
  int intTankHp = 100;
  int intTankX = 700;
  int intTankY = 400;
  int intTankViewDistance = 600;
  int intTankAttackDistance = 500;
  int intTankAttackDamage;
  int intTankSpeed;
  int intTankMoveTick;
  int intTankMoving;
  int intTankHitBox = 110;
  int intCannonBallX = intTankX - 50;
  int intCannonBallY = intTankY - 50;
  boolean blnTankHideStatus;
  boolean blnTankMoving;
  boolean blnTankAlive = true;
  boolean blnTankExplode1 = false;
  boolean blnTankExplode2 = false;
  boolean blnTankExplode3 = false;
  
  // Background Images
  PImage imgGrassBackground;
  PImage imgBrickBackground;

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

    // put your size call here
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
    if ((intMenuSelect == 1) && (keyCode == ENTER)){
      background(255);
      State = STATE.GAME; 
    }
  
    // Determines game state to be menu or game, changes screen depending on state, determines menu button select
    if(State == STATE.MENU){
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

        // Image Background
        image(imgGrassBackground, width/2, height/2, 800, 800);
        image(imgBrickBackground, width/2, -800, 800, 800);
        
        // Temporary Player Rectangle
        rect(intPlayerX, intPlayerY, 40, 40);
        playerMovement();
        playerDirection();

        Orc();
        Tank();
        playerDash();
        healthBar();
          
      }
    }
  
  }

  public void healthBar() {
    strokeWeight(2);
    stroke(0);
    noFill();
    rect(175, 50, 300, 20);

    fill(255, 0, 0);
    rect(175, 50, intHpBar, 20); 

    if(intHpBar > 0) {
      blnPlayerAlive = true;
    } 
    else if (intHpBar <= 0) {
      blnPlayerAlive = false;
      if(blnPlayerAlive == false) { 
        text("GAME OVER", CENTER, CENTER);
      }
    }
    if ((intHpBar > 0) && (blnPlayerAlive == true)) {
      /*  if (dist(intPlayerX, intPlayerY, fltOrcX, fltOrcY) < 5) {
         Player loses hp
         intHpBar -= whatever attack damage
        intHpBar -= intOrcDamage;

      } */
    }
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
            if (fltOrcHp[intOrcCounter] == 0) {
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

    
    public void Tank() {
      // Tank Code
      for (int intTankCounter = 0; intTankCounter < fltOrcY.length; intTankCounter++) {
        if (blnTankHideStatus == false) { 
            
            // if statement to draw the orc image while standing still.
              
            if (blnTankMoving == false) {
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
                
                  if (dist(intPlayerX, intPlayerY, intCannonBallX, intCannonBallY) <= intTankAttackDistance) {
                    
                    image(imgCannonBall1, intCannonBallX, intCannonBallY);
                  }
                  
                
              }
            }
            if (dist(intPlayerX, intPlayerY, intTankX, intTankY) <= intTankHitBox - 20) {
              
              System.out.println(dist(intPlayerX, intPlayerY, intTankX, intTankY) <= intTankHitBox - 20);
              
            }
              
              
            
            
            if (intTankHp <= 0) {
                
              blnTankAlive = false;
              
              if (blnTankAlive == false) {
              
                blnTankExplode1 = true;
                
                if (frameCount % 120 == 0) {
                  blnTankExplode2 = true;
                  blnTankExplode1 = false;
                  System.out.println("bob");
                }

                if (frameCount % 240 == 0) {
                  blnTankExplode3 = true;
                  blnTankExplode2 = false;
                  System.out.println("joe");
                }
              }
            }
          
          
        }
        if (blnTankExplode1 == true) {
          image(imgTankExplode1, intTankX, intTankY);
        }
        if (blnTankExplode2 == true) {
          image(imgTankExplode2, intTankX, intTankY);
        }
        if (blnTankExplode3 == true) {
          image(imgTankExplode3, intTankX, intTankY);
        }
        
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

  public void playerMovement() {
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

  






