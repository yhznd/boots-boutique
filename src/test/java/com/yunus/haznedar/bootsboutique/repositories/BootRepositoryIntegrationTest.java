package com.yunus.haznedar.bootsboutique.repositories;


import com.yunus.haznedar.bootsboutique.entities.Boot;
import com.yunus.haznedar.bootsboutique.enums.BootType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//That annotation allowed us to specify what type of test database we want to use.
// The options were an embedded database,
// or porting to an external database,
// or simply accepting the default database that's already configured and found on our class path during execution of the test code.
public class BootRepositoryIntegrationTest
{
    @Autowired
    private BootRepository bootRepository;


    @Test
    public void testFindBoot()
    {
        Boot boot =new Boot();
        boot.setType(BootType.CHUKKA);
        boot.setSize(3.4f);
        bootRepository.save(boot);

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
