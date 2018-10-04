package airat.valiev.dal;

import airat.valiev.domain.Transfer;

import java.sql.Timestamp;
import java.util.Map;

public class TransferDAO extends GenericDAO<Transfer> {

    public TransferDAO() {
        super("Transfer");
    }


    @Override
    public Transfer mapToObject(Map<String, Object> objectMap) {
        Transfer transfer = new Transfer();
        transfer.setId((Long) objectMap.get("id"));
        transfer.setDatetime((Timestamp) objectMap.get("datetime"));
        transfer.setIncomeAccount((Long) objectMap.get("incomeAccount"));
        transfer.setOutcomeAccount((Long) objectMap.get("outcomeAccount"));
        return transfer;
    }

    @Override
    public void save(Transfer entity) {
        if (entity.getId() != null) {
            getHandle().createUpdate("update transfer set incomeAccount=?, outcomeAccount=?, sum=? where id=?")
                    .bind(0, entity.getIncomeAccount())
                    .bind(1, entity.getOutcomeAccount())
                    .bind(2, entity.getSum())
                    .bind(3, entity.getId()).execute();
        } else {
            getHandle().createUpdate("insert into transfer (incomeAccount,outcomeAccount,sum) values (?,?,?)")
                    .bind(0, entity.getIncomeAccount())
                    .bind(1, entity.getOutcomeAccount())
                    .bind(2, entity.getSum())
                    .execute();
        }
    }
}
