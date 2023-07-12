package mathijs.bos.garage_app.car;

import mathijs.bos.garage_app.base_classes.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends BaseRepository<Car, Long> {
}
