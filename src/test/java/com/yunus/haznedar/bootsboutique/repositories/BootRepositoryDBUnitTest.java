package com.yunus.haznedar.bootsboutique.repositories;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.yunus.haznedar.bootsboutique.entities.Boot;
import com.yunus.haznedar.bootsboutique.enums.BootType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class,
DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:test-dataset.xml")
public class BootRepositoryDBUnitTest
{
    @Autowired
    private BootRepository bootRepository;


    @Test
    public void testFindBoot()
    {
        List<Boot> foundBoot=bootRepository.findBootByTypeAndSize(BootType.CHUKKA,3.4f);

        if(foundBoot.size()>0)
        {
            for (Boot bootL:foundBoot)
            {
                assertEquals(bootL.getType(), BootType.CHUKKA);
                assertEquals(bootL.getSize(),new Float( 3.4f));
            }

        }


    }
}
