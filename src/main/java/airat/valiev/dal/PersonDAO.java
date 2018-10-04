package airat.valiev.dal;

import airat.valiev.domain.Person;

import java.util.Map;

public class PersonDAO extends GenericDAO<Person> {

    public PersonDAO() {
        super("Person");
    }

    @Override
    public Person mapToObject(Map<String, Object> objectMap) {
        Person person = new Person();
        person.setId((Long) objectMap.get("id"));
        person.setName((String) objectMap.get("name"));
        return person;
    }

    @Override
    public void save(Person entity) {
        if (entity.getId() != null) {
            getHandle().createUpdate("update person set name=? where id=?")
                    .bind(0, entity.getName())
                    .bind(1, entity.getId());
        } else {
            getHandle().createUpdate("insert into person (name) values (?)")
                    .bind(0, entity.getName())
                    .execute();
        }
    }
}
