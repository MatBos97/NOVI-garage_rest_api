package mathijs.bos.garage_app.car;

import mathijs.bos.garage_app.base_classes.BaseController;
import mathijs.bos.garage_app.car_papers.CarPapers;
import mathijs.bos.garage_app.car_papers.CarPapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController extends BaseController<Car, CarDTO, Long> {

    private final CarPapersService carPapersService;

    @Autowired
    public CarController(CarService carService, CarPapersService carPapersService) {
        super(carService);
        this.carPapersService = carPapersService;
    }

    @GetMapping("/car_papers/{id}")
    public ResponseEntity<Byte[]> downloadCarPapers(@PathVariable Long id){
        Optional<CarPapers> optional = carPapersService.downloadFile(id);

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

    @PostMapping("/{id}/upload_car_papers")
    public ResponseEntity<CarPapers> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file){
        CarPapers carPapers;
        try {
            carPapers = carPapersService.uploadFile(id, file);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(carPapers);
    }

    @DeleteMapping("/car_papers/{id}")
    public ResponseEntity<?> deleteCarPapers(@PathVariable Long id) {
        carPapersService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
