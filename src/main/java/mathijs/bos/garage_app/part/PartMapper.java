package mathijs.bos.garage_app.part;

import mathijs.bos.garage_app.base_classes.BaseMapper;


public class PartMapper implements BaseMapper<Part, PartDTO> {
    @Override
    public Part toEntity(PartDTO dto) {
        return new Part(
                dto.getId(),
                dto.getName(),
                dto.getPrice(),
                dto.getStock()
        );
    }

    @Override
    public PartDTO toDto(Part entity) {
        return new PartDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getStock()
        );
    }
}
