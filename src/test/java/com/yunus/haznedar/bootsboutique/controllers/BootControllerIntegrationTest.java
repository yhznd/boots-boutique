package com.yunus.haznedar.bootsboutique.controllers;

import com.yunus.haznedar.bootsboutique.entities.Boot;
import com.yunus.haznedar.bootsboutique.enums.BootType;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BootControllerIntegrationTest
{
    //WebEnvironment.RANDOM_PORT -> we want to simulate a real environment with a real
    //servlet engine running

    @Autowired
    private BootController bootController;

    @Test
    public void testAddBoot()
    {
        //Create a boot
        Boot boot= new Boot();

        //Post our Boot from bean to the controller; check the outcome

        Boot newBoot=bootController.addBoot(boot);

        assertNotNull(newBoot); //smoke test, make sure object is saved
        assertNotNull(newBoot.getId()); // make sure object has an id

        //sample comparison
        assertNotEquals("suede",newBoot.getMaterial());

    }
}
