import java.sql.Time;
import java.util.Calendar;

public class ValueXYRFromForm
{
  private Integer id;
  private Float x;
  private Float y;
  private Float r = Float.valueOf(1.0F);
  private Boolean result;
  private Time time_now;
  private Long time_work;
  
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
  
  public Time getTime_now() {
    return time_now;
  }
  
  public void setTime_now(Time time_now) {
    this.time_now = time_now;
  }
  
  public Long getTime_work() {
    return time_work;
  }
  
  public void setTime_work(Long time_work) {
    this.time_work = time_work;
  }
  
  public void find()
  {
    Calendar calendar = Calendar.getInstance();
    long now = calendar.getTimeInMillis();
    
    boolean result = false;
    if (y.floatValue() >= 0.0F) {
      if ((4.0F * x.floatValue() * x.floatValue() + 4.0F * y.floatValue() * y.floatValue() <= r.floatValue() * r.floatValue()) && (x.floatValue() <= 0.0F)) {
        result = true;
      }
    } else if ((y.floatValue() >= -r.floatValue()) && 
      (y.floatValue() >= -x.floatValue() - r.floatValue()) && (2.0F * x.floatValue() <= r.floatValue())) {
      result = true;
    }
    
    result = false;
    setResult(Boolean.valueOf(result));
    setTime_now(new Time(now));
  }
}
