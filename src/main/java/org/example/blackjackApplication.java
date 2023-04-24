package org.example;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import org.example.resources.DealResource;
import org.example.resources.HitResource;
import org.example.resources.StandResource;

public class blackjackApplication extends Application<blackjackConfiguration> {

    public static void main(final String[] args) throws Exception {
        new blackjackApplication().run(args);
    }

    @Override
    public String getName() {
        return "blackjack";
    }

    @Override
    public void initialize(final Bootstrap<blackjackConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final blackjackConfiguration configuration,
                    final Environment environment) {
        final DealResource dealResource = new DealResource();
        environment.jersey().register(dealResource);

        final HitResource hitResource = new HitResource();
        environment.jersey().register(hitResource);

        final StandResource standResource = new StandResource();
        environment.jersey().register(standResource);
    }

}
