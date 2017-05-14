package com.dvn.retroexample.http;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Christopher on 14/05/2017.
 * <p>
 * (Class Info: )
 */

@Module
public class ApiModule {
	public final String BASE_URL = "https://api.twitch.tv/kraken/";
	
	@Provides
	public OkHttpClient provideClient() {
		
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		
		return new OkHttpClient.Builder().addInterceptor(interceptor).build();
		
	}
	
	@Provides
	public Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
		return new Retrofit.Builder()
				.baseUrl(baseURL)
				.client(client)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}
	
	@Provides
	public TwitchAPI provideApiService() {
		return provideRetrofit(BASE_URL, provideClient()).create(TwitchAPI.class);
	}
	
}