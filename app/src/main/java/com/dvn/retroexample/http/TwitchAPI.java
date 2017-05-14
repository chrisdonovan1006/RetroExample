package com.dvn.retroexample.http;


import com.dvn.retroexample.http.apimodel.Twitch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by Christopher on 14/05/2017.
 * <p>
 * (Class Info: )
 */

public interface TwitchAPI {
	
	@GET("games/top")
	Call<Twitch> getTopGames(@Header("Client-Id") String clientId);
	
	@GET("games/top")
	Observable<Twitch> getTopGamesObservable(@Header("Client-Id") String clientId);
}
