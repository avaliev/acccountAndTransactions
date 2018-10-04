package airat.valiev;

import airat.valiev.dal.AccountDAO;
import airat.valiev.dal.TransferDAO;
import airat.valiev.domain.Account;
import airat.valiev.domain.DataSourceFactory;
import airat.valiev.domain.Person;
import airat.valiev.domain.Transfer;
import airat.valiev.service.AccountService;
import org.h2.tools.RunScript;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */

public class AppTest {

    static AccountDAO accountDAO = new AccountDAO();

    static TransferDAO transferDAO = new TransferDAO();

    static AccountService accountService = new AccountService();

    @BeforeClass
    public static void init() throws IOException, InterruptedException, SQLException {

        accountDAO = new AccountDAO();
        new ArrayList<>()
        InputStream inputStream = AccountDAO.class.getClassLoader().getResourceAsStream("tables.sql");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        InputStream inputStream2 = AccountDAO.class.getClassLoader().getResourceAsStream("data.sql");

        InputStreamReader inputStreamReader2 = new InputStreamReader(inputStream2, Charset.forName("UTF-8"));
        Connection connection = DataSourceFactory.getDatasource().getConnection();
        RunScript.execute(connection, inputStreamReader);
        RunScript.execute(connection, inputStreamReader2);
        List<Account> list = accountDAO.getList();

    }

    @Test
    public void shouldAnswerWithTrue() {

        Account account = accountDAO.get(1l);
        Account account2 = accountDAO.get(2l);

        accountService.makeTransfer(account.getId(), account2.getId(), 10d);

        Transfer transfer = transferDAO.get(1l);

    }
}
