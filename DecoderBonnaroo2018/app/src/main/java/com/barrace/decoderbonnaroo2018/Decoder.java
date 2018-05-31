package com.barrace.decoderbonnaroo2018;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Decoder extends AppCompatActivity {

  // layout views
    private Button decodeButton;
    private EditText inputEditText;
    private EditText outputEditText;
    private TextView debugTextView;

  private ProgressDialog progressDialog;

    //instance vars
    private List<Bit> decoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);

        // views should be assigned in onCreate
        //    activity may not be visible to the user yet
        //    no long running or memory intensive operations here
        inputEditText = findViewById(R.id.inputEditText);
        outputEditText = findViewById(R.id.outputEditText);
        decodeButton = findViewById(R.id.decodeButton);
        debugTextView = findViewById(R.id.debugTextView);

        // example of creating a dynamic view without loading from xml
        progressDialog = define(this, "Updating Content", "Please Wait");
    }

    @Override
    protected void onStart() {
        super.onStart();

        // activity is now visible
        //    set listeners & callbacks here
        decodeButton.setOnClickListener(v -> onValidateInput());

        // this is also where you can load data from network / database
        onContentUpdate();
    }

    public void onValidateInput() {
        int minLength = 1; // TODO change to desired char input minimum
        String userInput = inputEditText.getText().toString().toUpperCase(); //all letters in map are uppercase
        outputEditText.getText().clear();
        debugTextView.setText("");
        if (userInput.isEmpty()) { displayMessage(this, "Enter a Code to get Started"); return; }
        if (userInput.length() < minLength) { displayMessage(this, "Please enter a Valid Code"); return; }
        String output = onDecode(userInput);
        outputEditText.setText(output);
    }


    public String onDecode(String code) {
      printToConsole(code);

      List<String> result = new ArrayList<>();
      Queue<Character> items = new LinkedList<>();
      for (Character c : code.toCharArray()) items.add(c);

      String msg;
      while(!items.isEmpty()) {
        Character curr = items.poll();
        printToConsole("current: "+curr.toString() + " type: "+ Character.getType(curr));
        //printToConsole("next: "+items.peek().toString() + " type: "+ Character.getType(items.peek()));

        // when digit
        if (Character.isDigit(curr)) {
          printToConsole("\t Is Digit");

          // when double digit
          if (Character.isDigit(items.peek())) {
            printToConsole("\t Is Double Digit");
            msg = Bit.messageForNumber(decoder, curr + items.poll());
          }


          // when single digit
          else {
            printToConsole("\t IS Single Digit");
            msg = Bit.messageForNumber(decoder, curr);
          }

        }

        // when alpha
        else if (Character.isAlphabetic(curr)) {
          printToConsole("\t Is Alpha");
          msg = Bit.messageForLetter(decoder, curr);
        }

        // when symbol
        else {
          printToConsole("\t Is Symbol");
          msg = Bit.messageForSymbol(decoder, curr);
        }

        result.add(msg);
      }

      return TextUtils.join("", result);
    }


    public void onContentUpdate() {

        // show loading to the user know that the app isn't frozen
        progressDialog.show();

        // here we simulate an async call that doesn't response right away
        Runnable slowAsyncOperation = () -> {
            decoder = Bit.populateList(); // load new data into fields or a view
            progressDialog.hide(); // we can hide this after operation completed
        };

        // wait 1000 ms then call Runnable to simulate network delay
        new Handler().postDelayed(slowAsyncOperation, 1000);
    }

    public void printToConsole(String msg) {
      debugTextView.append("\n"+msg);
      Log.d("Decoder", msg);
    }

    public void displayMessage(Context ctx, String msg) {
        debugTextView.append("\n"+msg);
        int duration = Toast.LENGTH_SHORT; // .LENGTH_LONG
        Toast.makeText(ctx, msg, duration).show();
    }

    public static ProgressDialog define(Context ctx, String title, String msg) {
        int style = ProgressDialog.STYLE_SPINNER; // .STYLE_HORIZONTAL
        ProgressDialog it = new ProgressDialog(ctx);
        it.setProgressStyle(style);
        it.setCancelable(false);
        it.setTitle(title);
        it.setMessage(msg);
        return it;
    }

}
