package org.example.dao;

import jakarta.transaction.Transactional;
import org.example.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDao {
    private final SessionFactory sessionFactory;
    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p",Person.class)
                .getResultList();
    }
    @Transactional
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }
    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }
    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());
        person.setEmail(updatedPerson.getEmail());
    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }
}
