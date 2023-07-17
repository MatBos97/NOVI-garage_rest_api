package mathijs.bos.garage_app.custom_action;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomActionDTO {

    private Long id;
    private String description;
    private Currency price;

}
