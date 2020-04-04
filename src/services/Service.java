package services;

import java.util.List;

public interface Service<T> {

    T create(T object);
    void update(T object);
    void delete(int id);
    T selectOne(int id);
    List<T> selectAll();
    List<T> filter(String whereQuery);

}
