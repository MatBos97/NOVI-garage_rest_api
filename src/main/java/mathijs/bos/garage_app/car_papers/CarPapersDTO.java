package mathijs.bos.garage_app.car_papers;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CarPapersDTO {

    private Long id;
    private String filename;
    private String filetype;
    private Byte[] fileData;
    private Long fileSize;
    private Long carId;
}
