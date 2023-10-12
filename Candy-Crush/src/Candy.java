import javax.swing.*;

public class Candy extends JButton {
    String Color,Icon,Behavior,type;
    public Candy(String Color , String Icon,String Behavior,String type)
    {
        this.Color=Color;
        setIcon(new ImageIcon(Icon));
        this.Behavior=Behavior;
        this.type=type;
    }
    public String getColor()
    {
        return Color;
    }
    public void setColor(String color)
    {
        Color=color;
    }
    public void setBehavior(String Behavior){
        this.Behavior=Behavior;
    }
    public String getBehavior(){
        return Behavior;
    }
    public void setType(String type)
    {
        this.type=type;
    }
    public String getType()
    {
        return type;
    }
}
