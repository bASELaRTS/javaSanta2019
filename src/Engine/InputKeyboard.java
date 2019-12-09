package Engine;

import java.util.Vector;

public class InputKeyboard {
  public static int KEY_LEFT = 37;
  public static int KEY_RIGHT = 39;
  public static int KEY_UP = 38;
  public static int KEY_DOWN = 40;  
  public static int KEY_SPACE = 32;
  public static int KEY_ENTER = 10;
  public static int KEY_ESCAPE = 27;

  private Vector<Key> m_keys;
  
  public InputKeyboard() {
    this.m_keys = new Vector<Key>();
    
    this.m_keys.add(new Key(KEY_LEFT));
    this.m_keys.add(new Key(KEY_RIGHT));
    this.m_keys.add(new Key(KEY_UP));
    this.m_keys.add(new Key(KEY_DOWN));
    this.m_keys.add(new Key(KEY_SPACE));
    this.m_keys.add(new Key(KEY_ENTER));
    this.m_keys.add(new Key(KEY_ESCAPE));
  }
  
  public Key findKey(int keyCode) {
    int i;
    Key key;
    for(i=0;i<this.m_keys.size();i++) {
      key = this.m_keys.elementAt(i);
      if (key.getKeyCode()==keyCode) {
        return key;
      }
    }
    return null;
  }
  
  public void setState(int keyCode, boolean state) {
    Key key = this.findKey(keyCode);
    if (key!=null) {
      key.setState(state);
    } else {
      System.out.println("" + keyCode);
    }
  }
  
  public boolean getState(int keyCode) {
    Key key = this.findKey(keyCode);
    if (key!=null) {
      return key.getState();
    }
    return false;
  }
  
  public class Key {
    private int m_keyCode;
    private boolean m_state;
    
    public Key(int keyCode) {
      this.setKeyCode(keyCode);
      this.setState(false);
    }
    
    public void setKeyCode(int keyCode) {this.m_keyCode=keyCode;}
    public int getKeyCode() {return this.m_keyCode;}    
    public void setState(boolean state) {this.m_state=state;}
    public boolean getState() {return this.m_state;}
  }
}
