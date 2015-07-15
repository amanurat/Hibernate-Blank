package com.hibernate.annotation.utils;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * User: assanai.manurat
 * Date: 4/6/2014
 * Time: 9:34 PM
 */
public class MyInterceptor extends EmptyInterceptor {

    Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        super.onDelete(entity, id, state, propertyNames, types);
        logger.info("> Delete event");

    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        System.out.println();
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        return super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public void postFlush(Iterator entities) {
        super.postFlush(entities);
    }

    @Override
    public void preFlush(Iterator entities) {
        super.preFlush(entities);
    }
}
