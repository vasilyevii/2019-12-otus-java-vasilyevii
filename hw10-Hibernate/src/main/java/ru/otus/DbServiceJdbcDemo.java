package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.ObjectDataDao;
import ru.otus.core.model.Account;
import ru.otus.core.model.User;
import ru.otus.core.service.account.DBServiceAccount;
import ru.otus.core.service.account.DbServiceAccountImpl;
import ru.otus.core.service.user.DBServiceUser;
import ru.otus.core.service.user.DbServiceUserImpl;
import ru.otus.h2.DataSourceH2;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.dao.ObjectDataDaoJdbc;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbServiceJdbcDemo {
    private static Logger logger = LoggerFactory.getLogger(DbServiceJdbcDemo.class);

    public static void main(String[] args) throws Exception {
        DataSource dataSource = new DataSourceH2();
        DbServiceJdbcDemo demo = new DbServiceJdbcDemo();

        // user
        demo.createUserTable(dataSource);

        SessionManagerJdbc sessionManager = new SessionManagerJdbc(dataSource);
        DbExecutor<User> dbExecutor = new DbExecutor<>();
        ObjectDataDao objectDataDao = new ObjectDataDaoJdbc(sessionManager, dbExecutor);

        DBServiceUser dbServiceUser = new DbServiceUserImpl(objectDataDao);

        dbServiceUser.saveUser(new User(100, "dima", 10));
        long id = dbServiceUser.updateUser(new User(100, "vasya", 40));
        Optional<User> user = dbServiceUser.getUser(id);

        System.out.println(user);
        user.ifPresentOrElse(
                crUser -> logger.info("created user, name:{}", crUser.getName()),
                () -> logger.info("user was not created")
        );

        //account
        demo.createAccountTable(dataSource);

        DbExecutor<Account> dbExecutorAccount = new DbExecutor<>();
        ObjectDataDao objectDataDaoAccount = new ObjectDataDaoJdbc(sessionManager, dbExecutorAccount);
        DBServiceAccount dbServiceAccount = new DbServiceAccountImpl(objectDataDaoAccount);

        dbServiceAccount.saveAccount(new Account(100, "debit", 10));
        long no = dbServiceAccount.updateAccount(new Account(100, "credit", 40));
        Optional<Account> account = dbServiceAccount.getAccount(no);

        System.out.println(account);
        account.ifPresentOrElse(
                crAccount -> logger.info("created account, type:{}", crAccount.getNo()),
                () -> logger.info("account was not created")
        );
    }

    private void createUserTable(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement("create table user(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))")) {
            pst.executeUpdate();
        }
        System.out.println("user table created");
    }

    private void createAccountTable(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement("create table account(no bigint(20) NOT NULL auto_increment, type varchar(255), rest number)")) {
            pst.executeUpdate();
        }
        System.out.println("account table created");
    }
}
