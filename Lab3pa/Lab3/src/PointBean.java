import java.io.Serializable;

public class PointBean implements Serializable{
    private String valueX;
    private String valueY;
    private String valueR;

    public String submit(){
        return "result?faces-redirect=true";
    }
    public String getValueX() {
        return valueX;
    }

    public String getValueY() {
        return valueY;
    }

    public String getValueR() {
        return valueR;
    }

    public void setValueR(String valueR) {
        this.valueR = valueR;
    }

    public void setValueX(String valueX) {
        this.valueX = valueX;
    }

    public void setValueY(String valueY) {
        this.valueY = valueY;
    }

}