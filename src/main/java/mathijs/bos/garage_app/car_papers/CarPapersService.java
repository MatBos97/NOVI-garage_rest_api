package mathijs.bos.garage_app.car_papers;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.car.Car;
import mathijs.bos.garage_app.car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class CarPapersService {

    private final CarPapersRepository carPapersRepository;
    private final CarRepository carRepository;

    @Autowired
    public CarPapersService(CarPapersRepository carPapersRepository, CarRepository carRepository) {
        this.carPapersRepository = carPapersRepository;
        this.carRepository = carRepository;
    }

    public CarPapers uploadFile(Long carId, MultipartFile file) throws IOException {
        Car car = carRepository.findById(carId).orElseThrow(EntityNotFoundException::new);
        String fileName = file.getOriginalFilename();
        String type = file.getContentType();
        Long size = file.getSize();

        CarPapers carPapers = CarPapers.builder()
                .car(car)
                .fileName(fileName)
                .fileType(type)
                .fileSize(size)
                .build();

        byte[] bytes = file.getBytes();

        Byte[] wrapper = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            wrapper[i] = bytes[i];
        }

        carPapers.setFileData(wrapper);

        return carPapersRepository.save(carPapers);
    }

    public Optional<CarPapers> downloadFile(Long id){
        return carPapersRepository.findById(id);
    }

    public void delete(Long id){
        carPapersRepository.deleteById(id);
    }

}
