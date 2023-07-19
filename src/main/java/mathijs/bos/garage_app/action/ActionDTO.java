package mathijs.bos.garage_app.action;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseDTO;

import java.util.Currency;

@Getter
@Setter
@Builder
public class ActionDTO extends BaseDTO {

    private String name;
    private Currency price;
}
