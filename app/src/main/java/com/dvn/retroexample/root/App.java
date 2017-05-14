package com.dvn.retroexample.root;

import android.app.Application;

import com.dvn.retroexample.http.ApiModule;

/**
 * Created by Christopher on 14/05/2017.
 * <p>
 * (Class Info: )
 */

public class App extends Application {
	
	private ApplicationComponent applicationComponent;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		applicationComponent = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.apiModule(new ApiModule())
				.build();
	}
	
	public ApplicationComponent getApplicationComponent() {
		return applicationComponent;
	}
}
