/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IRepository<T> {
    List<T> display(T object);
    void addNew(T object);
    void edit(T object);
    void delete(T object);
    List<T> find(T object);
    Boolean checkIdIsExist(T object);
}
