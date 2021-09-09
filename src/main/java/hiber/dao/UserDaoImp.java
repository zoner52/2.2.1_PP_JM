package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override

   public List<User> listUsers() {
      Query query = sessionFactory.getCurrentSession().createQuery("FROM User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      Query query = sessionFactory.getCurrentSession().createQuery(
              "FROM User U WHERE U.car.model = :model AND U.car.series = :series"
      );
      query.setParameter("model", model);
      query.setParameter("series", series);
      return (User) query.getSingleResult();
   }
}
