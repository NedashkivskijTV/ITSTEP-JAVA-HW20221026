package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        //create();
        read();
    }

    public static void create(){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // конфігураційний файл, де вказано налаштування доступу до БД (<property name="connection.url">jdbc:mysql://localhost:3306/my_db_4</property>)
                .addAnnotatedClass(Exam.class) // передаються сутності (Entity)
                .addAnnotatedClass(Student.class) // передаються сутності (Entity)
                .buildSessionFactory();
        Session session = factory.getCurrentSession(); // створення сесії
        session.beginTransaction(); // відкриття сесії

        Student ivan = new Student("Ivan", "Franko", "High school #100");
        Student taras = new Student("Taras", "Shevchenko", "High school #100");
        Student grugorij = new Student("Grugorij", "Skovoroda", "High school #100");
        Student lesia = new Student("Lesia", "Ukrainka", "High school #222");
        Student marko = new Student("Marko", "Vovchok", "High school #222");
        Student yurij = new Student("Yurij", "Androhovich", "High school #222");
        Student sergij = new Student("Sergij", "Zhadan", "High school #14");
        Student vasil = new Student("Vasil", "Stus", "High school #14");
        Student lina = new Student("Lina", "Kostenko", "High school #14");

        Exam history = new Exam("History of Ukraine", "", new GregorianCalendar(2022, Calendar.JUNE, 10, 10, 0));
        Exam literature = new Exam("Ukrainian Literature", "", new GregorianCalendar(2022, Calendar.JUNE, 20, 11, 30));
        Exam mathematics = new Exam("Mathematics", "", new GregorianCalendar(2022, Calendar.JULY, 5, 9, 45));

        history.addStudent(ivan, taras, grugorij, lesia, marko, yurij, sergij, vasil, lina);
        literature.addStudent(ivan, taras, grugorij, lesia, marko, yurij, sergij, vasil, lina);
        mathematics.addStudent(ivan, taras, grugorij, lesia, marko, yurij, sergij, vasil, lina);

        session.persist(history);
        session.persist(literature);
        session.persist(mathematics);

        session.getTransaction().commit(); // закриття сесії - знімок - збереження в БД зроблених змін
    }

    public static void read(){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // конфігураційний файл, де вказано налаштування доступу до БД (<property name="connection.url">jdbc:mysql://localhost:3306/my_db_4</property>)
                .addAnnotatedClass(Exam.class) // передаються сутності (Entity)
                .addAnnotatedClass(Student.class) // передаються сутності (Entity)
                .buildSessionFactory();
        Session session = factory.getCurrentSession(); // створення сесії
        session.beginTransaction(); // відкриття сесії

        Exam exam = session.get(Exam.class, 1);
        System.out.println(exam);
        exam.getStudents().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------");
        Student student = session.get(Student.class, 2);
        System.out.println(student);
        student.getExams().forEach(System.out::println);

        session.getTransaction().commit(); // закриття сесії - знімок - збереження в БД зроблених змін

    }
}
