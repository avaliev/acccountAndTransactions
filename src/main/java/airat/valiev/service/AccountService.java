package airat.valiev.service;

import airat.valiev.dal.AccountDAO;
import airat.valiev.dal.PersonDAO;
import airat.valiev.dal.TransferDAO;
import airat.valiev.domain.Account;
import airat.valiev.domain.Person;
import airat.valiev.domain.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class AccountService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    AccountDAO accountDAO = new AccountDAO();

    PersonDAO personDAO = new PersonDAO();

    TransferDAO transferDAO = new TransferDAO();

    public void createAccount(Long personId, BigDecimal balans) {
        Account account = new Account();
        account.setPersonId(personId);
        account.setBalans(balans);
        accountDAO.save(account);
    }


    public void createPerson(String name) {
        Person person = new Person();
        person.setName(name);
        personDAO.save(person);
    }


    public void makeTransfer(Long accFrom, Long accTo, double sum) {
        BigDecimal decimalSum = BigDecimal.valueOf(sum);
        accountDAO.beginTransaction();
        try {
            Transfer transfer = new Transfer();
            transfer.setIncomeAccount(accTo);
            transfer.setOutcomeAccount(accFrom);
            transfer.setSum(decimalSum);
            Account accountFrom = accountDAO.get(accFrom);
            Account accountTo = accountDAO.get(accTo);
            accountTo.setBalans(accountTo.getBalans().add(decimalSum));
            accountFrom.setBalans(accountTo.getBalans().subtract(decimalSum));

            transferDAO.save(transfer);
            accountDAO.save(accountFrom);
            accountDAO.save(accountTo);
        } catch (Exception ex) {
            logger.error(ex.toString());
            accountDAO.rollbackTransaction();
        } finally {

        }


    }

}
