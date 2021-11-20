package com.ecommerce.android.myownecommerceapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.android.myownecommerceapp.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    double amount = 0.0;
    TextView subtotal , discount , shipping , total ;
    Button paymentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        amount = getIntent().getDoubleExtra("amount",0.0);


        subtotal = findViewById(R.id.subtotal);
        discount = findViewById(R.id.discount);
        shipping = findViewById(R.id.shipping);
        total = findViewById(R.id.Total);
        paymentBtn = findViewById(R.id.checkout_btn);

        subtotal.setText(amount+"$");

          paymentBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  paymentMethod();
              }
          });
    }

    private void paymentMethod() {


       Checkout checkout = new Checkout();
      checkout.setKeyID("rzp_test_KxnYXnzAZE0GGp");

        final Activity activity = PaymentActivity.this;

        try {
            JSONObject options = new JSONObject();
            //Set Company Name
            options.put("name", "My E-Commerce App");
            //Ref no
            options.put("description", "Reference No. #123456");
            //Image to be display
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_9A33XWu170gUtm");
            // Currency type
            options.put("currency", "USD");
            //double total = Double.parseDouble(mAmountText.getText().toString());
            //multiply with 100 to get exact amount in rupee
            amount = amount * 100;
            //amount
            options.put("amount", amount);
            JSONObject preFill = new JSONObject();
            //email
            preFill.put("email", "developer.kharag@gmail.com");
            //contact
            preFill.put("contact", "7489347378");

            options.put("prefill", preFill);

            checkout.open(PaymentActivity.this, options);
        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout");
            e.printStackTrace();
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Cancel", Toast.LENGTH_SHORT).show();
    }
}
