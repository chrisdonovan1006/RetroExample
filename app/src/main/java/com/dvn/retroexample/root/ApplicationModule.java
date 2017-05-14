package com.dvn.retroexample.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Christopher on 14/05/2017.
 * <p>
 * (Class Info: )
 */
@Module
public class ApplicationModule {
	
	private Application application;
	
	public ApplicationModule(Application application) {
		this.application = application;
	}
	
	@Provides
	@Singleton
	public Context provideContext(){
		return application;
	}
}
