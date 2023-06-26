package mathijs.bos.garage_app.car;

import mathijs.bos.garage_app.base_classes.BaseController;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/car")
public class CarController extends BaseController<Car> {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        super(carService);
        this.carService = carService;
    }

    public ResponseEntity<String> uploadCarPapers(@PathVariable Long carId, @RequestParam("file")MultipartFile file){
        try{
            carService.saveCarPapers(carId, file);
            return ResponseEntity.ok("Car papers uplaoded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload car papers.");
        }
    }
}
