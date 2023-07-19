package mathijs.bos.garage_app.issue;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseDTO;

@Getter
@Setter
@Builder
public class IssueDTO extends BaseDTO {

    private String description;
    private Boolean fixAgreement;
    private Long serviceRecordId;
}
