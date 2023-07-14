package mathijs.bos.garage_app.base_classes;

import java.util.List;

public interface BaseMapper<D extends BaseDTO, E extends BaseEntity> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}
