package mathijs.bos.garage_app.car_papers;

import mathijs.bos.garage_app.base_classes.BaseMapper;

public class CarPapersMapper implements BaseMapper<CarPapers, CarPapersDTO> {
    @Override
    public CarPapers toEntity(CarPapersDTO dto) {
        return new CarPapers(
                dto.getId(),
                dto.getFilename(),
                dto.getFiletype(),
                dto.getFileData(),
                dto.getFileSize()
        );
    }

    @Override
    public CarPapersDTO toDto(CarPapers entity) {
        return new CarPapersDTO(
                entity.getFileName(),
                entity.getFileType(),
                entity.getFileData(),
                entity.getFileSize(),
                entity.getCar().getId()
        );
    }
}
