package mathijs.bos.garage_app.car;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CarDTO {

    private Long id;
    private Long customerId;
    private List<Long> serviceRecordIdList;
    private List<Long> carPapersIdList;
}
