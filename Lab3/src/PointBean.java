//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;

@ManagedBean(name="pointbean", eager=true)
@javax.faces.bean.ApplicationScoped
//@javax.faces.bean.SessionScoped
public class PointBean
{
    static {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
    }
    private ValueXYRFromForm values = new ValueXYRFromForm();
    private List<ValueXYRFromForm> list_with_values = new ArrayList();

    public void addXYR() {

        try
        {
            values.find();
            list_with_values.add(values);
            new ObjectDataForXYR().addXYR(values);
            values = new ValueXYRFromForm();
        } catch (SQLException e) {
            e.printStackTrace();
            values = new ValueXYRFromForm();
        }
    }

    public List<ValueXYRFromForm> getList_with_values() {
        return list_with_values;
    }

    public void setList_with_values(List<ValueXYRFromForm> list_with_values) {
        this.list_with_values = list_with_values;
    }

    public ValueXYRFromForm getValues() {
        return values;
    }

    public void setValues(ValueXYRFromForm values) {
        this.values = values;
    }
    public PointBean(){}
}
