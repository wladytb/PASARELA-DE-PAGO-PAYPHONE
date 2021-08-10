package com.wladytb.storewladytb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String url;
    TextView txt;
    Button btn;
    EditText txtCodigo, txtCell, txtCedula, txtReferencia, txtValorConIva, txtValorSinIva, txtImpuesto, txtTransaccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btn = (Button) findViewById(R.id.btnCobrar);
        txt = (TextView) findViewById(R.id.txtResult);
        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtCell = (EditText) findViewById(R.id.txtCell);
        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtReferencia = (EditText) findViewById(R.id.txtReferencia);
        txtValorConIva = (EditText) findViewById(R.id.txtValorConIva);
        txtValorSinIva = (EditText) findViewById(R.id.txtValorSinIva);
        txtImpuesto = (EditText) findViewById(R.id.txtImpuesto);
        txtTransaccion = (EditText) findViewById(R.id.txtTransaccion);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api();
            }
        });
    }

    public JSONObject getJSONtoSend() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("phoneNumber", txtCell.getText().toString());
            obj.put("countryCode", txtCodigo.getText().toString());
            obj.put("clientUserId", txtCedula.getText().toString());
            obj.put("reference", txtReferencia.getText().toString());
            obj.put("responseUrl", "http://paystoreCZ.com/confirm.php");
            obj.put("amount", Integer.valueOf(txtValorConIva.getText().toString()) +
                    Integer.valueOf(txtValorSinIva.getText().toString()) +
                    Integer.valueOf(txtImpuesto.getText().toString()));
            obj.put("amountWithTax", txtValorConIva.getText().toString());
            obj.put("amountWithoutTax", txtValorSinIva.getText().toString());
            obj.put("tax", txtImpuesto.getText().toString());
            Date dt = new Date();
            Random rd = new Random();
            String id = (rd.nextInt() + "" + dt.getDay() + "" + dt.getMonth() + "" + dt.getYear() + "" + dt.getHours() + "" + dt.getMinutes() + dt.getSeconds());
            obj.put("clientTransactionId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(obj.toString());
        return obj;
    }

    public void api() {
        if (txtCell.getText().toString().replaceAll(" ", "").trim().length() > 0 &&
                txtCodigo.getText().toString().replaceAll(" ", "").trim().length() > 0 &&
                txtCedula.getText().toString().replaceAll(" ", "").trim().length() > 0 &&
                txtValorConIva.getText().toString().replaceAll(" ", "").trim().length() > 0 &&
                txtValorSinIva.getText().toString().replaceAll(" ", "").trim().length() > 0 &&
                txtImpuesto.getText().toString().replaceAll(" ", "").trim().length() > 0) {
            RequestQueue queue = Volley.newRequestQueue(this);
            url = "https://pay.payphonetodoesposible.com/api/Sale";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, getJSONtoSend(),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                            txt.append(response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error.getMessage());
                    txt.append(error.getMessage());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Accept", "application/json");
                    headers.put("Authorization", "Bearer Ps7d5SOOe9CYIITZa-2llloFRjyXpjUFUuXW6_fcEqyRidzh2WGQOae3BOzm9yEzjPpQYcYIZLfvUsfPcqs7fsL7rUFLuExzi5MYINFlYWXBDb6NwODrbu4194_R39cGu0qOu5pO4MsCB7GyKLFH1FD1gmd9YZTLEfzgu0MjsvN1MiJBGy_2usa9OiNOuzsteO4Uh8zENzWNQhs538NGCVD_laR3-e0ApIErHZp9yCSFfGKOxi1Pr9CKaVhlUFlx8vn5Yp2J9C9yD4nk-JFCL7yYkr-9t_vaP1uEP637AUF2zbUChrUeok_gPLSZ3BKcp9zgB21gKgxywoR35_Etx9JYAJ8");
                    return headers;
                }
            };
            queue.add(jsonObjectRequest);
        }else
        {
            Toast.makeText(MainActivity.this, "Debe llenar todos los campos requeridos!", Toast.LENGTH_SHORT).show();
        }
    }
}
