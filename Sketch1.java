import processing.core.PApplet;

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
  if(State == STATE.MENU){
  background(0);
  text("Play", 200, 200);
  textAlign(CENTER);
  textSize(60);
    } else { 
  if(State == STATE.GAME){
      
    }
  }
  if (keyCode == ENTER){
    State = STATE.GAME; 
  }
  }
  
  // define other methods down here. 
}
