package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwListener;
import ru.otus.core.dao.ObjectDataDao;
import ru.otus.core.model.User;
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
import java.util.stream.LongStream;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbServiceDemo {
    private static Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) throws Exception {

        int objectCount = 10000;
        int nameSize = 1000;

        DataSource dataSource = new DataSourceH2();
        DbServiceDemo demo = new DbServiceDemo();

        // user
        demo.createUserTable(dataSource);

        SessionManagerJdbc sessionManager = new SessionManagerJdbc(dataSource);
        DbExecutor<User> dbExecutor = new DbExecutor<>();
        ObjectDataDao objectDataDao = new ObjectDataDaoJdbc(sessionManager, dbExecutor);

        DBServiceUser dbServiceUser = new DbServiceUserImpl(objectDataDao);

        // listener
        dbServiceUser.getCache().addListener(new HwListener<Long, User>() {
            @Override
            public void notify(Long key, User value, String action) {
                logger.info("key:{}, value:{}, action: {}", key, value, action);
            }
        });

        // create users
        LongStream.range(0, objectCount).forEach(id -> dbServiceUser.saveUser(new User(id, createDataSize(nameSize), 10)));

        // get users first time
        long beginTime = System.currentTimeMillis();
        LongStream.range(0, objectCount).forEach(id -> dbServiceUser.getUser(id));
        System.out.println("first get: " + ((System.currentTimeMillis() - beginTime)));
        System.out.println("cache size: " + dbServiceUser.getCache().getCacheSize());

//        System.gc();

        // get users second time
        beginTime = System.currentTimeMillis();
        LongStream.range(0, objectCount).forEach(id -> dbServiceUser.getUser(id));
        System.out.println("second get: " + ((System.currentTimeMillis() - beginTime)));
        System.out.println("cache size: " + dbServiceUser.getCache().getCacheSize());

    }

    private void createUserTable(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement("create table user(id bigint(20) NOT NULL auto_increment, name varchar(1000000000), age int(3))")) {
            pst.executeUpdate();
        }
        System.out.println("user table created");
    }

    private static String createDataSize(int msgSize) {
        StringBuilder sb = new StringBuilder(msgSize);
        for (int i=0; i<msgSize; i++) {
            sb.append('a');
        }
        return sb.toString();
    }

}
