package com.yunus.haznedar.bootsboutique.services;

import com.yunus.haznedar.bootsboutique.entities.Boot;
import com.yunus.haznedar.bootsboutique.enums.BootType;
import com.yunus.haznedar.bootsboutique.repositories.BootRepository;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BootAddUnitTest
{
   @Mock
   private BootRepository bootRepository;

   @InjectMocks
   private BootService bootService;

    @Before
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


   @Test
    public void testAddBoot()
    {
        //Create a boot
        Boot bootMockObject = new Boot();
        bootMockObject.setMaterial("leather");
        bootMockObject.setQuantity(47);
        bootMockObject.setSize(10.1f);
        bootMockObject.setType(BootType.CHELSEA);

        when(bootRepository.save(any(Boot.class))).thenReturn(bootMockObject);
        //When a bootRepository receives a call on its save method for any boot class
        //then simply return the mock contact

        //Save boot
        Boot newBoot=bootService.addBoot(null);

        //Verify the save
        assertEquals("leather",newBoot.getMaterial());


    }


}
