package com.barrace.decoderbonnaroo2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Decoder extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View v) {

        //outputText.Text = decodeMessage(inputText.Text);

        EditText input = (EditText) findViewById(R.id.inputText);
        TextView output = (TextView)findViewById(R.id.outputText);

        //TODO
        output.setText("TEST OnClick SUCCESS");
        //output.setText(decodeMessage(input.getText().toString()));
    }

    //instance vars
    public static Map<String, String> decodeMap = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);

        Button decodeButton = (Button) findViewById(R.id.decodeButton);
        decodeButton.setOnClickListener(Decoder.this);

        populateMap();


    }

    public static void populateMap()
    {
        //a shit ton of static codes

        //we want spaces to return as spaces, too.
        decodeMap.put(" ", " ");

        //LETTERS
        //ALL UPPERCASE
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");

        //NUMBERS
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");

        //SPECIAL CHARS
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
        decodeMap.put("", "");
    }

    public static String decodeMessage(String code)
    {
        String message = "";
        boolean numberRead = false;
        code = code.toUpperCase(); //all letters in map are uppercase

        try
        {
            //BufferedReader //for chars and alpha
            BufferedReader bfr = new BufferedReader(new StringReader(code));
            //Scanner for numbers
            Scanner scn = new Scanner(code);
            scn.useDelimiter(" ");

            //start processing
            int i;
            while((i = bfr.read()) != -1)
            {
                char ch = (char)i;

                if(Character.isDigit(ch))
                {
                    numberRead = true;
                }
                else if(!Character.isWhitespace(ch))
                {
                    scn.next(); //keep up scanner with bfr
                    numberRead = false;
                }

                if(numberRead)
                {
                    String num = scn.next();
                    message += decodeMap.get(num);

                    for(int k=0; k<num.length() - 1; k++)
                    {
                        bfr.read(); //keep up bfr with scanner
                    }

                }
                else
                {
                    String get = "" + ch;
                    message += decodeMap.get(get);
                }
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex);
            System.exit(0);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            System.exit(0);
        }

        return message;
    }
}
