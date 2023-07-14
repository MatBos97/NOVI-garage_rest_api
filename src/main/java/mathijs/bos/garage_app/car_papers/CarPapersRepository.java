package mathijs.bos.garage_app.car_papers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPapersRepository extends JpaRepository<CarPapers, Long> {
}