package com.yunus.haznedar.bootsboutique.repositories;

import com.yunus.haznedar.bootsboutique.entities.Boot;
import com.yunus.haznedar.bootsboutique.enums.BootType;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface BootRepository extends CrudRepository<Boot,Integer>
{

    //single
    Optional<Boot> findById(Integer id);
    List<Boot> findBootBySize(Float size);
    List<Boot> findBootByType(BootType type);
    List<Boot> findBootByMaterial(String material);
    List<Boot> findBootByQuantityGreaterThan(Integer quantity);

    //material
    List<Boot> findBootByMaterialAndSizeAndType(String material, Float size, BootType bootType);
    List<Boot> findBootByMaterialAndQuantityGreaterThan(String material, Integer quantity);
    List<Boot> findBootByMaterialAndSizeAndTypeAndQuantityGreaterThan(String material, Float size, BootType bootType,Integer quantitiy);


    //type
    List<Boot> findBootByTypeAndSize(BootType type, Float size);
    List<Boot> findBootByTypeAndQuantityGreaterThan(BootType type, Integer quantity);
    List<Boot> findBootByTypeAndSizeAndQuantityGreaterThan(BootType bootType,Float size,Integer quantity);


    //size
    List<Boot> findBootBySizeAndQuantityGreaterThan(Float size, Integer quantity);





}
