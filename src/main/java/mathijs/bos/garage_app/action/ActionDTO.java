package mathijs.bos.garage_app.action;

import lombok.Builder;
import lombok.Data;

import java.util.Currency;

@Data
@Builder
public class ActionDTO {

    private Long id;
    private String name;
    private Currency price;
}
