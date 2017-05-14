package com.dvn.retroexample.root;

import com.dvn.retroexample.MainActivity;
import com.dvn.retroexample.http.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Christopher on 14/05/2017.
 * <p>
 * (Class Info: )
 */
@Singleton
@Component(modules= {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
	
	void inject(MainActivity target);
	
}
