package org.letustakearest.infrastructure.web.context;

import org.letustakearest.domain.repository.impl.MockedDataLoader;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author volodymyr.tsukur
 */
@WebListener
public class LoadMockedDataOnServletContextInitialization implements ServletContextListener {

    @Inject
    private MockedDataLoader mockedDataLoader;

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        mockedDataLoader.load();
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        // Nothing to do.
    }

}
