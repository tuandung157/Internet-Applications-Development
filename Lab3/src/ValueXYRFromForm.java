import java.sql.Timestamp;
import java.util.Calendar;

public class ValueXYRFromForm
{
  private Integer id;
  private Float x;
  private Float y;
  private Float r = Float.valueOf(1.0F);
  private Boolean result;
  private Timestamp time_now;
  
  public ValueXYRFromForm() {}
  
  public Boolean getResult() { return result; }
  
  public void setResult(Boolean result)
  {
    this.result = result;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public Float getX() {
    return x;
  }
  
  public void setX(Float x) {
    this.x = x;
  }
  
  public Float getY() {
    return y;
  }
  
  public void setY(Float y) {
    this.y = y;
  }
  
  public Float getR() {
    return r;
  }
  
  public void setR(Float r) {
    this.r = r;
  }
  
  public Timestamp getTime_now() {
    return time_now;
  }
  
  public void setTime_now(Timestamp time_now) {
    this.time_now = time_now;
  }
  
  public void find()
  {
    Calendar calendar = Calendar.getInstance();
    long now = calendar.getTimeInMillis();
    
    boolean result = false;
//    if (y.floatValue() >= 0.0F) {
//      if ((4.0F * x.floatValue() * x.floatValue() + 4.0F * y.floatValue() * y.floatValue() <= r.floatValue() * r.floatValue()) && (x.floatValue() <= 0.0F)) {
//        result = true;
//      }
//    } else if ((y.floatValue() >= -r.floatValue()) &&
//      (y.floatValue() >= -x.floatValue() - r.floatValue()) && (2.0F * x.floatValue() <= r.floatValue())) {
//      result = true;
//    }

    if((y <= r)&&(y >= 0)&&(x >= -r/2)&&(x <= 0))
        result = true;
    else if((x >= 0)&&(y > 0)&&(Math.sqrt(x*x +y*y) <= r))
        result = true;
    else if((x >= 0)&&y <= 0&&((Math.abs(x)+Math.abs(y)) <= r))
        result = true;

//    result = false;
    setResult(Boolean.valueOf(result));
    setTime_now(new Timestamp(now));
  }
}
