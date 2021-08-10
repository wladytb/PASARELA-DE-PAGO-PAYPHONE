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
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class verificar extends AppCompatActivity {
    EditText txtTransactionIdSearch;
    TextView txtEmail, txtDeferred, txtCardBrand, txtAmount, txtClientTransactionId,
            txtPhoneNumber, txtStatusCode, txtTransactionStatus, txtMessageCode, txtTransactionId,
            txtDocument, txtCurrency, txtStoreName, txtDate, txtRegionIso, txtTransactionType, txtReference;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar);
        getSupportActionBar().hide();
        txtTransactionIdSearch = (EditText) findViewById(R.id.txtTransactionIdSearch);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtDeferred = (TextView) findViewById(R.id.txtDeferred);
        txtCardBrand = (TextView) findViewById(R.id.txtCardBrand);
        txtAmount = (TextView) findViewById(R.id.txtAmount);
        txtClientTransactionId = (TextView) findViewById(R.id.txtClientTransactionId);
        txtPhoneNumber = (TextView) findViewById(R.id.txtPhoneNumber);
        txtStatusCode = (TextView) findViewById(R.id.txtStatusCode);
        txtTransactionStatus = (TextView) findViewById(R.id.txtTransactionStatus);
        txtMessageCode = (TextView) findViewById(R.id.txtMessageCode);
        txtTransactionId = (TextView) findViewById(R.id.txtTransactionId);
        txtDocument = (TextView) findViewById(R.id.txtDocument);
        txtCurrency = (TextView) findViewById(R.id.txtCurrency);
        txtStoreName = (TextView) findViewById(R.id.txtStoreName);
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtRegionIso = (TextView) findViewById(R.id.txtRegionIso);
        txtTransactionType = (TextView) findViewById(R.id.txtTransactionType);
        txtReference = (TextView) findViewById(R.id.txtReference);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtTransactionIdSearch.getText().toString().trim().length() > 0)
                    getData();
                else
                    Toast.makeText(verificar.this, "Debe ingresar el Id de la transacción!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getData() {
        final String url = "https://pay.payphonetodoesposible.com/api/Sale/" + txtTransactionIdSearch.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        if (response != null) {
                            try {
                                txtEmail.setText("  " + response.getString("email"));
                                txtDeferred.setText("  " + String.valueOf(response.getBoolean("deferred")));
                                txtCardBrand.setText("  " + response.getString("cardBrand"));
                                txtAmount.setText("  " + response.getString("amount"));
                                txtClientTransactionId.setText("  " + response.getString("clientTransactionId"));
                                txtPhoneNumber.setText("  " + response.getString("phoneNumber"));
                                txtStatusCode.setText("  " + response.getString("statusCode"));
                                txtTransactionStatus.setText("  " + response.getString("transactionStatus"));
                                txtMessageCode.setText("  " + response.getString("messageCode"));
                                txtTransactionId.setText("  " + response.getString("transactionId"));
                                txtDocument.setText("  " + response.getString("document"));
                                txtCurrency.setText("  " + response.getString("currency"));
                                txtStoreName.setText("  " + response.getString("storeName"));
                                txtDate.setText("  " + response.getString("date"));
                                txtRegionIso.setText("  " + response.getString("regionIso"));
                                txtTransactionType.setText("  " + response.getString("transactionType"));
                                txtReference.setText("  " + response.getString("reference"));
                                Toast.makeText(verificar.this, "La API ha respondido!", Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(verificar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer Ps7d5SOOe9CYIITZa-2llloFRjyXpjUFUuXW6_fcEqyRidzh2WGQOae3BOzm9yEzjPpQYcYIZLfvUsfPcqs7fsL7rUFLuExzi5MYINFlYWXBDb6NwODrbu4194_R39cGu0qOu5pO4MsCB7GyKLFH1FD1gmd9YZTLEfzgu0MjsvN1MiJBGy_2usa9OiNOuzsteO4Uh8zENzWNQhs538NGCVD_laR3-e0ApIErHZp9yCSFfGKOxi1Pr9CKaVhlUFlx8vn5Yp2J9C9yD4nk-JFCL7yYkr-9t_vaP1uEP637AUF2zbUChrUeok_gPLSZ3BKcp9zgB21gKgxywoR35_Etx9JYAJ8");
                return headers;
            }
        };
        queue.add(getRequest);
    }
}