package database;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDatabase<T> {
    public abstract List<T> select(Connection conn);
    public abstract int insert(T obj,Connection conn);
    public abstract int uptade(T obj,Connection conn);
    public abstract int delete(int id,Connection conn);
}
