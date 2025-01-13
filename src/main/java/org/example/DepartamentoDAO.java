package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DepartamentoDAO {

    // Crear un nuevo departamento
    public void crearDepartamento(Departamento departamento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(departamento);
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

    // Leer un departamento por su ID
    public Departamento leerDepartamento(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Departamento.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Actualizar un departamento existente
    public void actualizarDepartamento(Departamento departamento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(departamento);
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

    // Eliminar un departamento existente
    public void eliminarDepartamento(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Departamento departamento = session.get(Departamento.class, id);
            if (departamento != null) {
                session.delete(departamento);
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

    // Leer todos los departamentos (opcional, para fines demostrativos)
    public List<Departamento> leerTodosLosDepartamentos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("from Departamento", Departamento.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Leer departamentos con el conteo de empleados
    public List<Object[]> leerDepartamentosConConteoEmpleados(Long empresaId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Object[]> query = session.createQuery(
                    "SELECT d.id, d.nombre, COUNT(e.id) " +
                            "FROM Departamento d LEFT JOIN d.empleados e " +
                            "WHERE d.empresa.id = :empresaId " +
                            "GROUP BY d.id, d.nombre", Object[].class);
            query.setParameter("empresaId", empresaId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Eliminar todos los departamentos
    public void eliminarTodosLosDepartamentos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Departamento").executeUpdate();
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

    // Reiniciar AUTO_INCREMENT
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