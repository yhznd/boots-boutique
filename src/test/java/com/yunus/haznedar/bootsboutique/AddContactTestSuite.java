package com.yunus.haznedar.bootsboutique;

import com.yunus.haznedar.bootsboutique.controllers.BootControllerIntegrationTest;
import com.yunus.haznedar.bootsboutique.repositories.BootRepositoryIntegrationTest;
import com.yunus.haznedar.bootsboutique.services.BootServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BootServiceIntegrationTest.class, BootControllerIntegrationTest.class, BootRepositoryIntegrationTest.class})
public class AddContactTestSuite
{
    //intentionally empty- Test Suite setup annotation is sufficient.
}
