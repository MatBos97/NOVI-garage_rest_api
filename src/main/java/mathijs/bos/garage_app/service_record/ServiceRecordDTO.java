package mathijs.bos.garage_app.service_record;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Currency;

@Getter
@Setter
@Builder
public class ServiceRecordDTO {

    private LocalDateTime inspection;
    private LocalDateTime repair;
    private Status status;
    private Byte[] receipt;
    private Currency totalCost;

}
