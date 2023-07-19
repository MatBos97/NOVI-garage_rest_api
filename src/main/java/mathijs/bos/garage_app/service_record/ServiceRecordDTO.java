package mathijs.bos.garage_app.service_record;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseDTO;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

@Getter
@Setter
@Builder
public class ServiceRecordDTO extends BaseDTO {

    private LocalDateTime inspection;
    private LocalDateTime repair;
    private Status status;
    private Byte[] receipt;
    private Currency totalCost;
    private Long carId;
    private List<Long> partIdList;
    private List<Long> actionIdList;
    private List<Long> customActionIdList;
    private List<Long> issueIdList;

}
