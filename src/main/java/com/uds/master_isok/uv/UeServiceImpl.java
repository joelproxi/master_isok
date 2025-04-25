
package com.uds.master_isok.uv;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.uds.master_isok.exceptions.DuplicateResourceException;
import com.uds.master_isok.exceptions.ResourceNotFoundException;
import static com.uds.master_isok.utils.AppConstants.ID;

@Service
public class UeServiceImpl implements UeService {

    private final UeRepository ueRepository;
    private final UeMapper ueMapper;

    public UeServiceImpl(UeRepository ueRepository, UeMapper ueMapper) {
        this.ueRepository = ueRepository;
        this.ueMapper = ueMapper;
    }

    @Override
    public Page<UeResponse> getAllUes(int page, int size, String search, String... sort) {
        return ueRepository.findAllPaginated(
                search,
                page,
                size,
                sort
        ).map(ueMapper::toDto);
    }

    @Override
    public UeResponse getUeById(Long id) {
        return ueRepository.findById(id)
                .map(ueMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("UE", ID, id));
    }
    
    @Override
    public Long createUe(UeRequest dto) {
        UE ue = ueMapper.toEntity(dto);
        // Check if the UE already exists
        if (ueRepository.existsByCodeIgnoreCase(ue.getcode())) {
            throw new DuplicateResourceException("exiting UE");
        }
        ue = ueRepository.save(ue);
        System.out.println(String.format("Created UE with ID: %d", ue.getId()));
        return ue.getId();
    }

    @Override
    public Long updateUe(Long id, UeRequest dto) {
        UE existingUe = ueRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("UE", ID, id)
        );
  
        UE updatedUe = ueMapper.toEntity(dto);
        updatedUe.setId(existingUe.getId());
        ueRepository.save(updatedUe);
        System.out.println(String.format("Updated UE with ID: %d", id));
        return updatedUe.getId();
    }

    @Override
    public void deleteUe(Long id) {
        ueRepository.findById(id).ifPresentOrElse(
            ue -> {
                ueRepository.deleteById(id);
                System.out.println(String.format("Deleted UE with ID: %d", id));
            },
            () -> {
                System.out.println(String.format("UE with ID: %d not found", id));
                throw new ResourceNotFoundException("UE", ID, id);
            }
        );
    
    }

    @Override
    public Page<UeResponse> getUesByTeacherId(Long teacherId, int page, int size, String search, String... sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUesByTeacherId'");
    }

}