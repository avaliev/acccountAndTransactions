package airat.valiev.dal;

import airat.valiev.domain.Account;
import org.jdbi.v3.core.statement.Update;

import java.math.BigDecimal;
import java.util.Map;

public class AccountDAO extends GenericDAO<Account> {


    public AccountDAO() {
        super("account");
    }

    @Override
    public Account mapToObject(Map<String, Object> objectMap) {
        Account account = new Account();
        account.setId((Long) objectMap.get("id"));
        account.setPersonId((Long) objectMap.get("person_id"));
        account.setBalans((BigDecimal) objectMap.get("balans"));
        return account;
    }

    @Override
    public void save(Account entity) {
        if (entity.getId() != null) {
            getHandle().createUpdate("update account set person_id=?, balans=? where id=?")
                    .bind(0, entity.getPersonId())
                    .bind(1, entity.getBalans())
                    .bind(2, entity.getId()).execute();
        } else {
            getHandle().createUpdate("insert into account (person_id,balans) values (?,?)")
                    .bind(0, entity.getPersonId())
                    .bind(1, entity.getBalans())
                    .execute();
        }
    }
}


