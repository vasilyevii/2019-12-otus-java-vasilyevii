package ru.otus;

import org.hibernate.SessionFactory;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.core.service.user.DBServiceUser;
import ru.otus.core.service.user.DbServiceUserHibernateImpl;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DbServiceHibernateDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
                User.class, AddressDataSet.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserHibernateImpl(userDao);

        AddressDataSet address = new AddressDataSet("Lenina avenue");
        List<PhoneDataSet> phones = new ArrayList<>();
        phones.add(new PhoneDataSet("01"));
        phones.add(new PhoneDataSet("03"));

        long id = dbServiceUser.saveUser(new User(0, "Вася", 10, address, phones));
        Optional<User> mayBeCreatedUser = dbServiceUser.getUser(id);
        System.out.println(mayBeCreatedUser);

    }
}
