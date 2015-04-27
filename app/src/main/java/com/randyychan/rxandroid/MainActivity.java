package com.randyychan.rxandroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.view.ViewObservable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;


public class MainActivity extends ActionBarActivity {

    TextView text;
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        test8();
    }

    private void test() {
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {

                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Randyyy, world!");
                        subscriber.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                text.setText(s);
            }
        };

        myObservable.subscribe(mySubscriber);
    }
    private void test2() {
        Observable<String> myObservable =
                Observable.just("TEST TWO MANG!!0");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                text.setText(s);
            }
        };

        myObservable.subscribe(onNextAction);
    }
    private void test3() {
        Observable.just("TEST threeee").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                text.setText(s);
            }
        });
    }
    private void test4() {
        Observable.just("TEST 4!")
                .subscribe(s -> text.setText(s));
    }
    private void test5() {
        Observable.just("TEST 5 commencing!...")
                .map(s -> s + "another test!")
                .subscribe(s1 -> text.setText(s1));
    }
    private void test6() {
        Observable.just("5CHARS")
                .map(s -> s.length())
                .map(i -> Integer.toString(i))
                .subscribe(s1 -> text.setText(s1));
    }
    private void test7() {
        query("test")
                .flatMap(strings -> Observable.from(strings))
                .subscribe(new Subscriber<String>() {

                    StringBuilder sb = new StringBuilder();

                    @Override
                    public void onCompleted() {
                        text.setText(sb.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        sb.append(s + " cat ");
                    }
                });

    }

    private void test8() {
        boolean test1 = false;
        boolean test2 = false;
        Observable<Boolean> value2 = Observable.create(subscriber ->  {
            Log.e("RANDY", "value2 create");
            subscriber.onNext(false);
            button.setOnClickListener(new View.OnClickListener() {
                boolean test1 = false;

                @Override
                public void onClick(View view) {
                    subscriber.onNext(test1);
                    test1 = !test1;
                }
            });
        });
        Observable<Boolean> value3 = Observable.create(subscriber ->  {
            Log.e("RANDY", "value3 create");
            subscriber.onNext(false);

            button2.setOnClickListener(new View.OnClickListener() {
                boolean test1 = false;

                @Override
                public void onClick(View view) {
                    subscriber.onNext(test1);
                    test1 = !test1;
                }
            });
                });

        Func2<Boolean, Boolean, Boolean> combinator =
                (a, b) -> a && b ;

        Observable.combineLatest(value2, value3, combinator)
                .subscribe(allValid -> {
                    Log.e("RANDY", "combine");

                    if (allValid) {
                        text.setText("ALL VALID!!");
                    } else {
                        text.setText("NOT ALL VALID");
                    }
                });

    }
    private Observable<List<String>> query(String s) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("test");
        list.add("323");
        list.add("dear");
        list.add("android");

        return Observable.just(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
