package rs.ac.bg.fon.ps.repository;

import java.util.List;

/**
 *
 * @author Cartman
 * @param <T>
 */
public interface Repository<T> {
    List<T> getAll(T param) throws Exception;
    boolean add(T param) throws Exception;
    Integer addReturnKey(T param) throws Exception;
    boolean edit(T param) throws Exception;
    boolean delete(T param)throws Exception;
    //List<T> getAll();
}
