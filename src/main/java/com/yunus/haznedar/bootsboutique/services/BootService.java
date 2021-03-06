package com.yunus.haznedar.bootsboutique.services;

import com.yunus.haznedar.bootsboutique.entities.Boot;
import com.yunus.haznedar.bootsboutique.enums.BootType;
import com.yunus.haznedar.bootsboutique.repositories.BootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BootService
{
    @Autowired
    private BootRepository bootRepository;

    public Iterable<Boot> getAllBoots()
    {
        return bootRepository.findAll();
    }

    public Optional<Boot> findBoot(@PathVariable Integer id)
    {
        return bootRepository.findById(id);
    }

    public Boot addBoot(@RequestBody Boot boot)
    {
        return bootRepository.save(boot);
    }

    public Boot deleteBoot(@PathVariable("id") Integer id) {

        Boot bootOptional=bootRepository.findById(id)
                .orElseThrow(()->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No boot found with this provided ID, delete failed."));

        bootRepository.delete(bootOptional);
        return bootOptional;

    }

    public Boot incrementQuantity(@PathVariable("id") Integer id)
    {
        Boot bootOptional = bootRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No boot found with this provided ID, increment failed"));

        bootOptional.setQuantity(bootOptional.getQuantity()+1);
        bootRepository.save(bootOptional);
        return bootOptional;

    }

    public Boot decrementQuantity(@PathVariable("id") Integer id)
    {
        Boot bootOptional = bootRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No boot found with this provided ID, decrement failed"));

        bootOptional.setQuantity(bootOptional.getQuantity()-1);
        bootRepository.save(bootOptional);
        return bootOptional;

    }

    public List<Boot> searchBoots(@RequestParam(required = false) String material,
                                  @RequestParam(required = false) BootType type, @RequestParam(required = false) Float size,
                                  @RequestParam(required = false, name = "quantity") Integer minQuantity) throws ResponseStatusException {
        if (Objects.nonNull(material))
        {
            if (Objects.nonNull(type) && Objects.nonNull(size) && Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a material, type, size, and minimum
                // quantity
                return bootRepository.findBootByMaterialAndSizeAndTypeAndQuantityGreaterThan(material,size,type,minQuantity);
            } else if (Objects.nonNull(type) && Objects.nonNull(size))
            {
                // call the repository method that accepts a material, size, and type
                return bootRepository.findBootByMaterialAndSizeAndType(material,size,type);
            } else if (Objects.nonNull(type) && Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a material, a type, and a minimum
                // quantity
                return bootRepository.findBootByMaterialAndQuantityGreaterThan(material,minQuantity);
            } else if (Objects.nonNull(type)) {

            } else {
                // call the repository method that accepts only a material
                return bootRepository.findBootByMaterial(material);
            }
        }
        else if (Objects.nonNull(type))
        {
            if (Objects.nonNull(size) && Objects.nonNull(minQuantity))
            {
                // call the repository method that accepts a type, size, and minimum quantity
                return bootRepository.findBootByTypeAndSizeAndQuantityGreaterThan(type,size,minQuantity);
            }
            else if (Objects.nonNull(size))
            {
                // call the repository method that accepts a type and a size
                return bootRepository.findBootByTypeAndSize(type,size);
            }
            else if (Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a type and a minimum quantity
                return bootRepository.findBootByTypeAndQuantityGreaterThan(type,minQuantity);
            }
            else
            {
                // call the repository method that accept only a type
                return bootRepository.findBootByType(type);
            }
        }
        else if (Objects.nonNull(size))
        {
            if (Objects.nonNull(minQuantity))
            {
                // call the repository method that accepts a size and a minimum quantity
                return bootRepository.findBootBySizeAndQuantityGreaterThan(size,minQuantity);
            }
            else
            {
                // call the repository method that accepts only a size
                return bootRepository.findBootBySize(size);
            }
        }
        else if (Objects.nonNull(minQuantity))
        {
            // call the repository method that accepts only a minimum quantity
            return bootRepository.findBootByQuantityGreaterThan(minQuantity);
        }
        else

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This query is not supported! Try a different combination of search parameters.");
        return new ArrayList<>();
    }



}
