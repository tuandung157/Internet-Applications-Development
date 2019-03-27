import java.text.SimpleDateFormat;

@javax.faces.bean.ManagedBean(name="time", eager=true)
@javax.faces.bean.SessionScoped
public class Time
{
  private static final long serialVersionUID = 1L;
  
  public Time() {}
  
  SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd 'и время' hh:mm:ss");
  
  public String newTime() {
    return "Текущая дата " + formatForDateNow.format(new java.util.Date());
  }
}
