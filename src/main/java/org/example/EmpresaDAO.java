package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EmpresaDAO {

    // Crear una nueva empresa
    public void crearEmpresa(Empresa empresa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(empresa);
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

    // Leer una empresa por su ID
    public Empresa leerEmpresa(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Empresa.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Actualizar una empresa existente
    public void actualizarEmpresa(Empresa empresa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(empresa);
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

    // Eliminar una empresa existente
    public void eliminarEmpresa(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Empresa empresa = session.get(Empresa.class, id);
            if (empresa != null) {
                session.delete(empresa);
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

    // Leer todas las empresas (opcional, para fines demostrativos)
    public List<Empresa> leerTodasLasEmpresas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("from Empresa", Empresa.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Eliminar todas las empresas
    public void eliminarTodasLasEmpresas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Empresa").executeUpdate();
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