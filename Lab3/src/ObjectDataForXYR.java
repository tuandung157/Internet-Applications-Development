import org.hibernate.*;

public class ObjectDataForXYR
{
  public ObjectDataForXYR() {}

  public void addXYR(ValueXYRFromForm obj) throws java.sql.SQLException
  {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(obj);
      session.getTransaction().commit();
    } catch (Exception e) {
      javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "LOL", 0);
    } finally {
      if ((session != null) && (session.isOpen()))
      {
        session.close(); }
    }
  }

  public ValueXYRFromForm getById(Integer id) throws java.sql.SQLException {
    Session session = HibernateUtil.getSessionFactory().openSession();
    ValueXYRFromForm xyr = null;
    try
    {
      xyr = (ValueXYRFromForm)session.load(ValueXYRFromForm.class, id);
    } catch (Exception e) {
      javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", 0);
    } finally {
      if ((session != null) && (session.isOpen())) {
        session.close();
      }
    }
    return xyr;
  }

  public java.util.Collection getAllBusses() throws java.sql.SQLException { Session session = null;
    java.util.List xyrs = new java.util.ArrayList();
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      xyrs = session.createCriteria(ValueXYRFromForm.class).list();
    } catch (Exception e) {
      javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", 0);
    } finally {
      if ((session != null) && (session.isOpen())) {
        session.close();
      }
    }
    return xyrs;
  }
}
