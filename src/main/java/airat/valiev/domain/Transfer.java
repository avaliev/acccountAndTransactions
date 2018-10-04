package airat.valiev.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transfer {


    Long id;

    Long outcomeAccount;

    Long incomeAccount;

    Timestamp datetime;

    BigDecimal sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOutcomeAccount() {
        return outcomeAccount;
    }

    public void setOutcomeAccount(Long outcomeAccount) {
        this.outcomeAccount = outcomeAccount;
    }

    public Long getIncomeAccount() {
        return incomeAccount;
    }

    public void setIncomeAccount(Long incomeAccount) {
        this.incomeAccount = incomeAccount;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
