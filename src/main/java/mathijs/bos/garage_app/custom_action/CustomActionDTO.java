package mathijs.bos.garage_app.custom_action;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseDTO;

import java.util.Currency;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomActionDTO extends BaseDTO {

    private String description;
    private Currency price;
    private Long serviceRecordId;

}
