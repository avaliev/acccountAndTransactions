package airat.valiev.domain;

import org.h2.jdbcx.JdbcDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceFactory {

    static JdbcDataSource dataSource;

    public static DataSource getDatasource() {

        if (dataSource == null) {
            dataSource = new JdbcDataSource();
            dataSource.setURL("jdbc:h2:mem:accounts;DB_CLOSE_DELAY=-1");
            dataSource.setUser("sa");
            dataSource.setPassword("sa");
//            Context ctx = new InitialContext();
//            ctx.bind("jdbc/accountDB", ds);
        }
        return dataSource;
    }
}
