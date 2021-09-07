package com.yunus.haznedar.bootsboutique.controllers;

import com.yunus.haznedar.bootsboutique.entities.Boot;
import com.yunus.haznedar.bootsboutique.enums.BootType;
import com.yunus.haznedar.bootsboutique.services.BootService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(BootController.class)
public class BootControllerUnitTest
{
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BootService bootService;

    @InjectMocks
    private BootController bootController;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddBoot() throws Exception
    {
        Boot mockBoot = new Boot();
        mockBoot.setMaterial("leather");
        mockBoot.setQuantity(47);
        mockBoot.setSize(10.1f);
        mockBoot.setId(2);
        mockBoot.setType(BootType.CHELSEA);

        when(bootService.addBoot(any(Boot.class))).thenReturn(mockBoot);

        //simulate the form bean that would POST from the web page
        Boot newBoot=new Boot();
        newBoot.setMaterial("leather");
        newBoot.setQuantity(47);
        newBoot.setSize(10.1f);
        newBoot.setId(3);
        newBoot.setType(BootType.CHELSEA);

        //simulate the form submit (POST)
        mockMvc.perform(put("/api/v1/boots/"+newBoot.getId()+"/quantity/increment", newBoot))
                .andExpect(status().isOk()).andExpect(mvcResult -> assertTrue(newBoot.getQuantity()>47));



    }
}