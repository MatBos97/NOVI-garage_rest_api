package mathijs.bos.garage_app.car;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarDTO {

    private Long id;
    private Long customerId;
    private List<Long> serviceRecordIdList;
    private List<Long> carPapersIdList;
}
