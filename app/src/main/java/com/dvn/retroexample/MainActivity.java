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
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
	
	@Inject
	TwitchAPI twitchAPI;
	
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView) findViewById(R.id.text);
		
		((App) getApplication()).getApplicationComponent().inject(this);
		
		Call<Twitch> call = twitchAPI.getTopGames(TwitchClient.TWITCH_CLIENT_ID);
		
		call.enqueue(new Callback<Twitch>() {
			@Override
			public void onResponse(Call<Twitch> call, Response<Twitch> response) {
				List<Top> gameList = response.body().getTop();
				
				for (Top top : gameList) {
					textView.append(top.getGame().getName() + "\n");
					System.out.println(top.getGame().getName());
				}
			}
			
			@Override
			public void onFailure(Call<Twitch> call, Throwable t) {
				t.printStackTrace();
			}
		});
		
		twitchAPI.getTopGamesObservable(TwitchClient.TWITCH_CLIENT_ID).flatMap(new Func1<Twitch, Observable<Top>>() {
			@Override
			public Observable<Top> call(Twitch twitch) {
				return Observable.from(twitch.getTop());
			}
		}).flatMap(new Func1<Top, Observable<String>>() {
			@Override
			public Observable<String> call(Top top) {
				return Observable.just(top.getGame().getName());
			}
		}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
			@Override
			public void onCompleted() {
				
			}
			
			@Override
			public void onError(Throwable e) {
				
			}
			
			@Override
			public void onNext(String s) {
				textView.append("From rx java: " + s + "\n");
				System.out.println("From rx java: " + s);
			}
		});
		
		
		twitchAPI.getTopGamesObservable(TwitchClient.TWITCH_CLIENT_ID).flatMap(new Func1<Twitch, Observable<Top>>() {
			@Override
			public Observable<Top> call(Twitch twitch) {
				
				return Observable.from(twitch.getTop());
			}
		}).flatMap(new Func1<Top, Observable<Integer>>() {
			@Override
			public Observable<Integer> call(Top top) {
				
				return Observable.just(top.getGame().getPopularity());
				
			}
		}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
			@Override
			public void onCompleted() {
				
			}
			
			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
				
			}
			
			@Override
			public void onNext(Integer integer) {
				textView.append("From rx java: Popularity is " + integer.toString() + "\n");
				System.out.println("From rx java: Popularity is " + integer.toString());
				
			}
		});
		
		twitchAPI.getTopGamesObservable(TwitchClient.TWITCH_CLIENT_ID).flatMap(new Func1<Twitch, Observable<Top>>() {
			@Override
			public Observable<Top> call(Twitch twitch) {
				
				return Observable.from(twitch.getTop());
			}
		}).flatMap(new Func1<Top, Observable<String>>() {
			@Override
			public Observable<String> call(Top top) {
				
				return Observable.just(top.getGame().getName());
				
			}
		}).filter(new Func1<String, Boolean>() {
			@Override
			public Boolean call(String s) {
				return s.startsWith("H");
			}
		}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
			@Override
			public void onCompleted() {
				
			}
			
			@Override
			public void onError(Throwable e) {
				
			}
			
			@Override
			public void onNext(String s) {
				textView.append("From rx java with filter: " + s + "\n");
				System.out.println("From rx java with filter: " + s);
			}
		});
	}
}
