package mathijs.bos.garage_app.car_papers;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseDTO;

@Getter
@Setter
@Builder
public class CarPapersDTO extends BaseDTO {

    private String filename;
    private String filetype;
    private Byte[] fileData;
    private Long fileSize;
    private Long carId;
}
