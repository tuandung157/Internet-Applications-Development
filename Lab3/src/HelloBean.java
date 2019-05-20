import org.hibernate.Session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;

public class HelloBean implements Serializable {
    private LinkedList<Point> points = new LinkedList<>();
    private double x;
    private double y;
    private double r;
    private boolean ok;

    public LinkedList<Point> getPoints(){ return points; }

    public void setPoints(LinkedList<Point> points) {
        this.points = points;
    }

    public double getR() {
        return r;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean getOk(){
        if(x>=0 && y<=0 && x*x+y*y<=r*r){
            return true;
        }
        if(x>=0 && y>=0 && (x+y <r/2)){
            return true;
        }
        if(x<=0 && y<=0 && x>=-r && y>=-r/2){
            return true;
        }
        return false;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }


    public void newPoint(){
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            Point p = new Point(getX(), getY(), getR(), getOk());
            points.add(p);
            session.save(p);
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
    }
    public String changePage(){
        return "index?faces-redirect=true";
    }

}