package services;

import dao.DAO;
import exceptions.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class BaseService<T> implements IService<T>{

    @Autowired
    private DAO<T> baseDAO;


    @Override
    @Transactional
    public void add(T t) throws LogicException {

        baseDAO.add(t);
    }

    @Override
    @Transactional
    public void delete(long id) {

        baseDAO.delete(id);
    }

    @Override
    @Transactional
    public void modify(T t) throws LogicException {

        baseDAO.modify(t);
    }

    @Override
    public T get(long id) {

        return baseDAO.get(id);
    }
}
