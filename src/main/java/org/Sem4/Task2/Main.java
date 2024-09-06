package org.Sem4.Task2;

import org.Sem4.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            Student student = Student.create();
            session.save(student);
            System.out.println("Object student saved");

            Student retrievedStudent = session.get(Student.class, student.getId());
            System.out.println("Retrieved student " + retrievedStudent);

            retrievedStudent.updateName();
            retrievedStudent.updateAge();
            session.update(retrievedStudent);
            System.out.println("Object student updated");

            session.delete(retrievedStudent);
            System.out.println("Object student deleted");

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
