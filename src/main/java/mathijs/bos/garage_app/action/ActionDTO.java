package mathijs.bos.garage_app.action;

import lombok.*;
import mathijs.bos.garage_app.base_classes.BaseDTO;

import java.util.Currency;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionDTO extends BaseDTO {

    private String name;

    private Currency price;
}
