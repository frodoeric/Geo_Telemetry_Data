package com.eric.data.model;

import java.io.Serializable;

public interface IEntity<T extends Serializable> {

    public T getId();
}
