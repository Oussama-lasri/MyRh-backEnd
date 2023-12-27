package com.example.myrh.BaseInterface;

import java.util.List;

public interface IBaseService <T> {

    public T create(T t);
    public T update(long id,T t);
    public T findById(long id);
    public void delete(long id);
    public List<T> findAll();

}
