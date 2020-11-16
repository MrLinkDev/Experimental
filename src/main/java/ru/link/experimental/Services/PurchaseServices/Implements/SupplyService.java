package ru.link.experimental.Services.PurchaseServices.Implements;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Entities.*;
import ru.link.experimental.Exceptions.PageExceptions.*;
import ru.link.experimental.Repositories.SupplyRepository;
import ru.link.experimental.Services.PurchaseServices.SupplyServiceInterface;

import java.util.*;

@Service
public class SupplyService implements SupplyServiceInterface {

    private final SupplyRepository supplyRepository;

    public SupplyService(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    @Override
    public void create(UUID purchaseId, Long cost, String technologyStack, String structure, String comment) {
        SupplyEntity supplyEntity = new SupplyEntity(
                purchaseId,
                cost,
                technologyStack,
                structure,
                comment
        );

        supplyRepository.save(supplyEntity);
    }

    @Override
    public void update(UUID id, Long cost, String technologyStack, String structure, String comment) {
        SupplyEntity supplyEntity = supplyRepository.getOne(id);

        if (cost != null){
            supplyEntity.setCost(cost);
        }

        if (technologyStack != null){
            supplyEntity.setTStack(technologyStack);
        }

        if (structure != null){
            supplyEntity.setStructure(structure);
        }

        if (comment != null){
            supplyEntity.setComment(comment);
        }

        supplyRepository.saveAndFlush(supplyEntity);
    }

    @Override
    public void delete(UUID id) {
        supplyRepository.delete(supplyRepository.getOne(id));
    }

    @Override
    public SupplyDTO get(UUID id) {
        SupplyEntity supplyEntity = supplyRepository.getOne(id);
        return new SupplyDTO(supplyEntity.getCost(), supplyEntity.getTStack(), supplyEntity.getStructure(), supplyEntity.getComment());
    }

    @Override
    public List<SupplyDTO> getPage(int pageIndex, int pageSize) throws PageIndexException, PageSizeException {
        if (pageSize > 100) {
            pageSize = 10;
        }

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<SupplyEntity> page = supplyRepository.findAll(pageable);

        List<SupplyDTO> out = new ArrayList<>();
        for (SupplyEntity supplyEntity : page){
            out.add(new SupplyDTO(supplyEntity.getCost(), supplyEntity.getTStack(), supplyEntity.getStructure(), supplyEntity.getComment()));
        }

        return out;
    }
}
