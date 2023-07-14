package mathijs.bos.garage_app.car_papers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/car_papers")
public class CarPapersController {

    private final CarPapersService service;

    @Autowired
    public CarPapersController(CarPapersService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Byte[]> downloadCarPapers(@PathVariable Long id){
        Optional<CarPapers> optional = service.downloadFile(id);

        if(optional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        CarPapers carPapers = optional.get();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + carPapers.getFileName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, carPapers.getFileType())
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(carPapers.getFileSize()))
                .body(carPapers.getFileData());
    }

    @PostMapping
    public ResponseEntity<CarPapers> uploadFile(@RequestParam("file")MultipartFile file){
        CarPapers carPapers;
        try {
            carPapers = service.uploadFile(file);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(carPapers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
