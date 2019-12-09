package Engine;

public class InputMouse {
  public boolean left;
  public boolean right;
  public boolean middle;
  
  private Vector2 m_position;
  
  public InputMouse() {
    this.m_position = new Vector2();
  }
  
  public void setState(int button, boolean state) {
    if (button==1) this.left = state;
    if (button==2) this.middle = state;
    if (button==3) this.right = state;
  }  
  
  public Vector2 getPosition() {return this.m_position;}
}
