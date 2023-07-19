package mathijs.bos.garage_app.car;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseDTO;

import java.util.List;

@Getter
@Setter
@Builder
public class CarDTO extends BaseDTO {

    private Long customerId;
    private List<Long> serviceRecordIdList;
    private List<Long> carPapersIdList;

}
