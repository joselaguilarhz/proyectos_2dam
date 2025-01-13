package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EmpleadoDAO {

    // Crear un nuevo empleado
    public void crearEmpleado(Empleado empleado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(empleado);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Leer un empleado por su ID
    public Empleado leerEmpleado(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Empleado.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Actualizar un empleado existente
    public void actualizarEmpleado(Empleado empleado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(empleado);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Eliminar un empleado existente
    public void eliminarEmpleado(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Empleado empleado = session.get(Empleado.class, id);
            if (empleado != null) {
                session.delete(empleado);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Leer todos los empleados (opcional, para fines demostrativos)
    public List<Empleado> leerTodosLosEmpleados() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("from Empleado", Empleado.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Leer empleados por departamento
    public List<Empleado> leerEmpleadosPorDepartamento(Long departamentoId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("from Empleado where departamento.id = :departamentoId", Empleado.class)
                    .setParameter("departamentoId", departamentoId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Eliminar todos los empleados
    public void eliminarTodosLosEmpleados() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Empleado").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void reiniciarAutoIncrement(String tableName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("ALTER TABLE " + tableName + " AUTO_INCREMENT = 1").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}