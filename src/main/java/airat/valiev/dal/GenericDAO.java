package airat.valiev.dal;

import airat.valiev.domain.DataSourceFactory;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.transaction.SerializableTransactionRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class GenericDAO<T> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String tableName;


    private Jdbi jdbi;

    private Handle connection;

    public GenericDAO(String tableName) {

        this.tableName = tableName;

        jdbi = Jdbi.create(DataSourceFactory.getDatasource());
        jdbi.installPlugin(new H2DatabasePlugin());

        LinkedList
        jdbi.setTransactionHandler(new SerializableTransactionRunner());

    }

    public abstract void save(T entity);

    public T get(Long id) {
        Handle handle = getHandle();
        Query query = handle.createQuery
                ("select * from " + tableName + " where id=:id")
                .bind("id", id);
        Optional<Map<String, Object>> first = query.mapToMap().findFirst();
        T object = mapToObject(first.get());
        handle.close();
        return object;

    }


    public abstract T mapToObject(Map<String, Object> objectMap);


    public void delete(Long id) {
        Handle handle = getHandle();
        handle.execute("delete from " + tableName + " where id=?", id);
        handle.close();
    }


    public List<T> getList() {
        Handle handle = getHandle();
        Query query = handle.createQuery("select * from " + tableName);
        List<Map<String, Object>> resultSet = query.mapToMap().list();
        List<T> result = resultSet.stream().map(m -> mapToObject(m)).collect(Collectors.toList());
        handle.close();
        return result;
    }


    public void beginTransaction() {
        connection = jdbi.open();
        connection.execute("SET LOCK_MODE 1");

    }


    public void commitTransaction() {
        if (connection.isInTransaction()) {
            connection.commit();
            connection.close();
        }
    }


    public void rollbackTransaction() {
        if (connection.isInTransaction()) {
            connection.rollback();
            connection.close();
        }
    }

    public Handle getHandle() {
        if (connection != null && connection.isInTransaction()) {
            return connection;
        } else {
            return jdbi.open();
        }
    }

    public void executrSQL(String script) {
        jdbi.open().createScript(script).execute();
    }


}
