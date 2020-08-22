package ru.job4j.market.persistance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.market.model.Car;
import ru.job4j.market.model.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.function.Function;

public class DAO {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private DAO() {

    }

    /**
     * Create singleton.
     */
    private static final class Lazy {
        private static final DAO INST = new DAO();
    }

    public static DAO instOf() {
        return Lazy.INST;
    }

    public Car createCar(Car car) {
        request(
                session -> session.save(car));
        return car;
    }

    public User createUser(User user) {
        request(
                session -> session.save(user)
        );
        return user;
    }

    public Car findCarById(int id) {
        return request(
                session -> session.get(Car.class, id)
        );
    }

    public User findUserByEmail(String email) throws SQLException {
        User user;
        try {
            user = (User) request(
                    session -> session.createQuery("from User where email='" + email + "'").getSingleResult());
        } catch (Exception e) {
            throw new SQLException();
        }
        return user;
    }

    public void update(Car car) {
        request(session -> {
            session.update(car);
            return null;
        });
    }

    public Collection<Car> getCars() {
        return request(
                session -> session.createQuery("from Car").list()
        );
    }

    public Collection<Car> getCarsFilterDayPhotoName(String search) {
        return request(session ->
                session.createQuery("from Car " +
                        "where extract(day from publish) = extract(day from now()) " +
                        "and photo!='' and lower(name) like lower(:search) ")
                        .setParameter("search", search + "%")
                        .list());
    }

    public Collection<Car> getCarsFilerDay() {
        return request(session ->
                session.createQuery("from Car " +
                        "where extract(day from publish) = extract(day from now())").list());
    }

    public Collection<Car> getCarsFilterPhoto() {
        return request(session ->
                session.createQuery("from Car where photo != ''").list());
    }

    public Collection<Car> getCarsFilterName(String search) {
        return request(session ->
                session.createQuery("from Car where lower(name) like lower(:search) ")
                        .setParameter("search", search + "%")
                        .list());
    }

    public Collection<Car> getCarsFilterDayPhoto() {
        return request(session ->
                session.createQuery("from Car " +
                        "where extract(day from publish) = extract(day from now()) " +
                        "and photo!=''").list());
    }

    public Collection<Car> getCarsFilterPhotoName(String search) {
        return request(session ->
                session.createQuery("from Car" +
                        " where photo !=''" +
                        " and lower(name) like lower(:search)")
                        .setParameter("search", search + "%")
                        .list());
    }

    public Collection<Car> getCarsFilterDayName(String search) {
        return request(session ->
                session.createQuery("from Car " +
                        "where extract(day from publish) = extract(day from now()) " +
                        "and lower(name) like lower(:search)")
                        .setParameter("search", search + "%")
                        .list());
    }

    /**
     * Making function for request to database to exclude duplicating code.
     *
     * @param command request
     * @param <T>     model.
     * @return result of request.
     */
    private <T> T request(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
