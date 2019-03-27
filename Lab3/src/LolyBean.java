import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="lolybean", eager=true)
@javax.faces.bean.SessionScoped
public class LolyBean
{
//    static
//    {
//        String strSshUser = "s247407";
//        String strSshPassword = "hat507";
//        String strSshHost = "se.ifmo.ru";
//        int nSshPort = 2222;
//        String strRemoteHost = "pg";
//        int nLocalPort = 63333;
//        int nRemotePort = 5432;
//        String strDbUser = "s247407";
//        String strDbPassword = "hat507";
//
//        JSch jsch = new JSch();
//        Session session = null;
//        try {
//            session = jsch.getSession(strSshUser, strSshHost, 2222);
//            session.setPassword(strSshPassword);
//
//            session.setConfig("StrictHostKeyChecking", "no");
//
//            session.connect();
//            System.out.println("tonnel created");
//            session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
//        } catch (JSchException e) {
//            e.printStackTrace();
//        }
//    }


    private ValueXYRFromForm values = new ValueXYRFromForm();
    private List<ValueXYRFromForm> list_with_values = new ArrayList();

    public void addXYR() {
        values.find();
        list_with_values.add(values);
        try
        {
            new ObjectDataForXYR().addXYR(values);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        values = new ValueXYRFromForm();
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

    public LolyBean() {}
}
