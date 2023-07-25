package mathijs.bos.garage_app.part;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartService extends BaseService<Part, PartDTO, Long> {

    private final PartRepository partRepository;
    private final PartMapper partMapper;

    @Autowired
    public PartService(PartRepository partRepository, PartMapper partMapper) {
        super(partRepository);
        this.partRepository = partRepository;
        this.partMapper = partMapper;
    }

    @Override
    public Part create(PartDTO dto) throws EntityNotFoundException {
        dto.setId(null);
        Part part = partMapper.toEntity(dto);

        return partRepository.save(part);
    }

    @Override
    public Part update(Long id, PartDTO dto) throws EntityNotFoundException {
        return partRepository.findById(id).map(
                part -> {
                    part.setId(dto.getId());
                    part.setName(dto.getName());
                    part.setPrice(dto.getPrice());
                    part.setStock(dto.getStock());

                    return partRepository.save(part);
                }
        ).orElseThrow(EntityNotFoundException::new);
    }
}
