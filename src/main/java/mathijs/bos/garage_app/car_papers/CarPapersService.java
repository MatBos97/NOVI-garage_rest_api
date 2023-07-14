package mathijs.bos.garage_app.car_papers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class CarPapersService {

    private final CarPapersRepository repository;

    @Autowired
    public CarPapersService(CarPapersRepository repository) {
        this.repository = repository;
    }

    public CarPapers uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String type = file.getContentType();
        Long size = file.getSize();

        CarPapers carPapers = new CarPapers();
        carPapers.setFileName(fileName);
        carPapers.setFileType(type);
        carPapers.setFileSize(size);

        byte[] bytes = file.getBytes();

        Byte[] wrapper = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            wrapper[i] = bytes[i];
        }

        carPapers.setFileData(wrapper);

        return repository.save(carPapers);
    }

    public Optional<CarPapers> downloadFile(Long id){
        return repository.findById(id);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
