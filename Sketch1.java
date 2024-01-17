import processing.core.PApplet;
import java.awt.Font;

public class Sketch1 extends PApplet {
	
  /** 
   * Enumeration for the state of the game, menu vs gameply
   * 
   */
	private enum STATE{
    MENU,
    GAME
  };

  // Creates State variable to check what state the game is in based on
  private STATE State = STATE.MENU;

  int intMenuSelect; 
  /**
   * Called once at the beginning of execution, put your size all in this method
   */ 
  public void settings() {
	// put your size call here
    size(800, 800);
      
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(0, 0, 0);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    if (keyCode == ENTER){
      background(255);
      State = STATE.GAME; 
    }
  
  // Determines game state to be menu or game, changes screen depending on state, determines menu button select
  if(State == STATE.MENU){
  background(0);
  if (keyPressed) {
    if (keyCode == UP) {
      intMenuSelect -= 1;
      delay(80);
      if (intMenuSelect < 1) {
        intMenuSelect = 3;
      }
    } else if (keyCode == DOWN) {
      intMenuSelect += 1;
      delay(80);
      if (intMenuSelect > 3) {
        intMenuSelect = 1;
      }
    }
  }
  /*Font menuFont = new Font("8bitoperator JVE", Font.BOLD, 50);
  setFont(M)
  */
  stroke(255);
  fill(0);
  rect(250, 200, 300, 65);

  fill(255);
  textSize(40);
  text("PLAY", 350, 245);


  stroke(255);
  fill(0);
  rect(100, 315, 600, 65);

  fill(255);
  textSize(40);
  text("Character Selection", 215, 360);


  stroke(255);
  fill(0);
  rect(150, 430, 500, 65);

  fill(255);
  textSize(40);
  text("Options", 330, 475);

  if (intMenuSelect == 1) {
  stroke(97, 202, 255);
  fill(50, 50, 50);
  rect(250, 200, 300, 65);
  fill(97, 202, 255);
  textSize(40);
  text("PLAY", 350, 245);
  } else {
  if ( intMenuSelect == 2) { 
  stroke(97, 202, 255);
  fill(50, 50, 50);
  rect(100, 315, 600, 65);
  fill(97, 202, 255);
  textSize(40);
  text("Character Selection", 215, 360);
  } else {
  if (intMenuSelect == 3) { 
  stroke(97, 202, 255);
  fill(50, 50, 50);
  rect(150, 430, 500, 65);
  fill(97, 202, 255);
  textSize(40);
  text("Options", 330, 475);
  }
  
  }

  }
    } else { 
  if(State == STATE.GAME){
      
    }
  }
  
  }
  
  // define other methods down here. 
}
