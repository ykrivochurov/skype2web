package ru.ykey.model;

import java.io.Serializable;

/**
 * Date: 28.04.13
 * Time: 11:14
 */
public interface IEntity extends Serializable {

    Long getId();

    void setId(Long id);

}