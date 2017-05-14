package com.dvn.retroexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dvn.retroexample.http.TwitchAPI;
import com.dvn.retroexample.http.apimodel.Top;
import com.dvn.retroexample.http.apimodel.Twitch;
import com.dvn.retroexample.root.App;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
	
	@Inject
	TwitchAPI twitchAPI;
	
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView) findViewById(R.id.text);
		
		((App)getApplication()).getApplicationComponent().inject(this);
		
		Call<Twitch> call = twitchAPI.getTopGames(TwitchClient.TWITCH_CLIENT_ID);
		
		call.enqueue(new Callback<Twitch>() {
			@Override
			public void onResponse(Call<Twitch> call, Response<Twitch> response) {
				List<Top> gameList = response.body().getTop();
				
				for (Top top : gameList){
					textView.append(top.getGame().getName() + "\n");
					System.out.println(top.getGame().getName());
				}
			}
			
			@Override
			public void onFailure(Call<Twitch> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}
}
