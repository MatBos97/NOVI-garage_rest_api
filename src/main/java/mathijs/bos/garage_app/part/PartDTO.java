package mathijs.bos.garage_app.part;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@Builder
public class PartDTO {

    private Long id;
    private String name;
    private Currency price;
    private Integer stock;

}
