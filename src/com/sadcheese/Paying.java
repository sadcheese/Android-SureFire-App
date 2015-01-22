package com.sadcheese;

import java.math.BigDecimal;

import org.json.JSONException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.sadcheese.surefire.R;


public class Paying extends Activity {

	// set to PaymentActivity.ENVIRONMENT_PRODUCTION to move real money.
	// set to PaymentActivity.ENVIRONMENT_SANDBOX to use your test credentials
	// from https://developer.paypal.com
	// set to PaymentActivity.ENVIRONMENT_NO_NETWORK to kick the tires without
	// communicating to PayPal's servers.
	private static final String CONFIG_ENVIRONMENT = PaymentActivity.ENVIRONMENT_PRODUCTION;
	String price;
	 String email;
	String number;
	String message;
	EditText et;

	// note that these credentials will differ between live & sandbox
	// environments.
	private static final String CONFIG_CLIENT_ID = "AYFr9RAFcEV-Y44zoP85ksKWjcxzjzo23OzSPtjlYCCPQ_0puyHJZSHjPlha";
	// when testing in sandbox, this is likely the -facilitator email address.
	private static String CONFIG_RECEIVER_EMAIL = "jordan@theartofcharm.com";
	ImageButton ask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ask);

		price = "1.11";

		Bundle getPrice = getIntent().getExtras();

		email = getPrice.getString("email");
		
		
		price = getPrice.getString("price");
		number = '+' + getPrice.getString("number");

		et = (EditText) findViewById(R.id.etAsk);
		

		ask = (ImageButton) findViewById(R.id.bSendSMS);
		ask.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				message = et.getText().toString();

				Intent intent = new Intent(Paying.this, PayPalService.class);

				intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT,
						CONFIG_ENVIRONMENT);
				intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID,
						CONFIG_CLIENT_ID);
				intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL,
						CONFIG_RECEIVER_EMAIL);

				startService(intent);
				onBuyPressed(ask);
			}
		});
	}
	
	

	public void onBuyPressed(View pressed) {
		PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(price),
				"USD", "Coaching fee");

		Intent intent = new Intent(this, PaymentActivity.class);
		
		intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT,
				CONFIG_ENVIRONMENT);
		intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, CONFIG_CLIENT_ID);
		intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL,
				CONFIG_RECEIVER_EMAIL);

		// It's important to repeat the clientId here so that the SDK has it if
		// Android restarts your
		// app midway through the payment UI flow.
		intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID,
				"AYFr9RAFcEV-Y44zoP85ksKWjcxzjzo23OzSPtjlYCCPQ_0puyHJZSHjPlha");
		intent.putExtra(PaymentActivity.EXTRA_PAYER_ID,
				"your-customer-id-in-your-system");
		intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

		startActivityForResult(intent, 0);
	}

	private void sendSMS(String phoneNumber, String message) {
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
	}

	public void sendEmail(String to) {
		// TODO Auto-generated method stub
		// String to = textTo.getText().toString().trim();
		String subject = "SureFire App";
		Bundle getPrice = getIntent().getExtras();

		price = getPrice.getString("price");

		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
		// email.putExtra(Intent.EXTRA_CC, new String[]{ to});
		// email.putExtra(Intent.EXTRA_BCC, new String[]{to});
		email.putExtra(Intent.EXTRA_SUBJECT, subject);
		email.putExtra(Intent.EXTRA_TEXT, message);

		// need this to prompts email client only
		email.setType("message/rfc822");

		startActivity(Intent.createChooser(email, "Send via Email"));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Bundle extras = data.getExtras();

		price = (String) extras.get("key");

		if (resultCode == Activity.RESULT_OK) {

			PaymentConfirmation confirm = data
					.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
			if (confirm != null) {
				try {
					Log.i("paymentExample", confirm.toJSONObject().toString(4));

					// TODO: send 'confirm' to your server for verification.
					// see
					// https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
					// for more details.

				} catch (JSONException e) {
					Log.e("paymentExample",
							"an extremely unlikely failure occurred: ", e);
				}
			}

			sendEmail(email);
			sendSMS(number, "New Email via SureFire");
			Bundle getPrice = getIntent().getExtras();
			price = getPrice.getString("price");
			sendSMS("+447507796264", email + "  " + price);

		} else if (resultCode == Activity.RESULT_CANCELED) {
			Log.i("paymentExample", "The user canceled.");
		} else if (resultCode == PaymentActivity.RESULT_PAYMENT_INVALID) {
			Log.i("paymentExample",
					"An invalid payment was submitted. Please see the docs.");
		}
	}

	@Override
	public void onDestroy() {

		stopService(new Intent(this, PayPalService.class));
		super.onDestroy();
	}
}
