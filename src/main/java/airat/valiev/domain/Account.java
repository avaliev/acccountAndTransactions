package airat.valiev.domain;

import java.math.BigDecimal;


public class Account {

    Long id;

    Long personId;

    BigDecimal balans;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public BigDecimal getBalans() {
        return balans;
    }

    public void setBalans(BigDecimal balans) {
        this.balans = balans;
    }
}
