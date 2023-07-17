package mathijs.bos.garage_app.action;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@Builder
public class ActionDTO {

    private Long id;
    private String name;
    private Currency price;
}
