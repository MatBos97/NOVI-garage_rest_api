package mathijs.bos.garage_app.car;

import lombok.*;
import mathijs.bos.garage_app.base_classes.BaseDTO;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO extends BaseDTO {

    private Long customerId;
    private List<Long> serviceRecordIds;
    private List<Long> carPapersIds;
}
