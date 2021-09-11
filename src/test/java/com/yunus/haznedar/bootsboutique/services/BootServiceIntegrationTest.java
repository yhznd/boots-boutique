package com.yunus.haznedar.bootsboutique.services;

import com.yunus.haznedar.bootsboutique.entities.Boot;
import com.yunus.haznedar.bootsboutique.enums.BootType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BootServiceIntegrationTest
{
    //WebEnvironment.NONE -> none of our controller components will be loaded

    @Autowired
    private  BootService bootService;

    @Test
    public void testAddBoot()
    {
        //Create a boot
        Boot boot = new Boot();
        boot.setMaterial("suede");
        boot.setQuantity(12);
        boot.setSize(12.4f);
        boot.setType(BootType.DRESS);

        //Test adding the boot
        Boot newBoot = bootService.addBoot(boot);

        //Verify the addition
        assertNotNull(newBoot); //smoke test, make sure object is saved
        assertNotNull(newBoot.getId()); // make sure object has an id

        //sample comparisons
        assertEquals("suede",newBoot.getMaterial());
        assertEquals(BootType.DRESS,newBoot.getType());
        assertEquals(new Float(12.4f),newBoot.getSize());
        assertEquals(new Integer(12),newBoot.getQuantity());

    }

}
