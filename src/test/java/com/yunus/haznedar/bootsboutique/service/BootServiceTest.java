package com.yunus.haznedar.bootsboutique.service;

import com.yunus.haznedar.bootsboutique.entities.Boot;
import com.yunus.haznedar.bootsboutique.enums.BootType;
import com.yunus.haznedar.bootsboutique.repositories.BootRepository;
import com.yunus.haznedar.bootsboutique.services.BootService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class BootServiceTest
{

    @Mock
    private BootRepository bootRepository;

    @InjectMocks
    private BootService bootService;


    @Test
    @DisplayName("Boot Add Test")
    public void addBootTest()
    {
        Mockito.when(bootRepository.save(Mockito.any(Boot.class))).thenReturn(new Boot(1, BootType.CHELSEA,3.23f,10,"PL"));
        Boot boot=new Boot();
        assertEquals("PL",bootRepository.save(boot).getMaterial());
        assertNotEquals(BootType.DRESS,bootRepository.save(boot).getType());
        assertFalse(bootRepository.save(boot).getSize()>3.24f);
        assertTrue(bootRepository.save(boot).getQuantity()!=14);
    }

    @Test
    @DisplayName("Delete Boot Test")
    public void deleteBootTest()
    {
        Mockito.when(bootRepository.findById(1)).thenReturn(Optional.of(new Boot(1, BootType.DRESS,4.53f,69,"SR")));
        Optional<Boot> boot=bootRepository.findById(1);

        assertDoesNotThrow(()->bootService.deleteBoot(boot.get().getId()));
        assertThrows(ResponseStatusException.class,()->bootService.deleteBoot(2));

    }


    @Test
    @DisplayName("Boot Increment/Decrement Test")
    public void incrementBootQtyTest()
    {
        Mockito.when(bootRepository.findById(4)).thenReturn(Optional.of(new Boot(4, BootType.MOTOCROSS,3.00f,33,"CD")));
        Optional<Boot> boot=bootRepository.findById(4);

        assertEquals(boot.get().getQuantity()+1,
                bootService.incrementQuantity(boot.get().getId()).getQuantity());
        assertEquals(boot.get().getQuantity()-1,
                bootService.decrementQuantity(boot.get().getId()).getQuantity());

    }


    @ParameterizedTest
    @ValueSource(ints = {20,25})
    @DisplayName("Search Test")
    public void bootSearchTest(int qty)
    {
        List<Boot> mockBootList= Arrays.asList(
                new Boot(1, BootType.CHELSEA,3.23f,9,"PL")
                ,new Boot(2, BootType.RACING,3.43f,27,"XD")
                ,new Boot(3, BootType.GALOSH,3.83f,29,"FF"));

        Mockito.when(bootRepository.findBootByQuantityGreaterThan(qty)).thenReturn(Arrays.asList(mockBootList.get(1),mockBootList.get(2)));
        assertEquals(2,bootRepository.findBootByQuantityGreaterThan(qty).size());
        assertEquals(Arrays.asList(mockBootList.get(1),mockBootList.get(2)),bootRepository.findBootByQuantityGreaterThan(qty));
    }


}