package com.devup.opointdoacai.opointdoacai;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.shuhart.stepview.StepView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

public class Authentication extends AppCompatActivity {

    private int currentStep = 0;
    LinearLayout layout1,layout2;
    RelativeLayout layout3;
    StepView stepView;
    AlertDialog dialog_verifying,profile_dialog;

    private static String uniqueIdentifier = null;
    private static final String UNIQUE_ID = "UNIQUE_ID";
    private static final long ONE_HOUR_MILLI = 60*60*1000;

    private static final String TAG = "FirebasePhoneNumAuth";

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth firebaseAuth;

    private String phoneNumber;
    private Button sendCodeButton;
    private Button verifyCodeButton;
    private Button signOutButton;
    private Button button3;

    private EditText phoneNum;
    private PinView verifyCodeET;
    private TextView phonenumberText;

    private EditText nomeAuth;

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;


    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 =  findViewById(R.id.layout3);
        sendCodeButton = findViewById(R.id.submit1);
        verifyCodeButton = findViewById(R.id.submit2);
        button3 = findViewById(R.id.submit3);
        firebaseAuth = FirebaseAuth.getInstance();
        phoneNum = findViewById(R.id.phonenumber);
        verifyCodeET = findViewById(R.id.pinView);
        phonenumberText = findViewById(R.id.phonenumberText);
        nomeAuth = findViewById(R.id.edt_nome_auth);


        stepView = findViewById(R.id.step_view);
        stepView.setStepsNumber(3);
        stepView.go(0, true);
        layout1.setVisibility(View.VISIBLE);

        sendCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNumbers = phoneNum.getText().toString();
                phoneNumber = "+55" + phoneNumbers;
                phonenumberText.setText(phoneNumber);

                if (TextUtils.isEmpty(phoneNumber)) {
                    phoneNum.setError("Digite o numero do celular");
                    phoneNum.requestFocus();
                } else if (phoneNumber.length() < 10) {
                    phoneNum.setError("Por favor, digite um número válido");
                    phoneNum.requestFocus();
                } else {

                    if (currentStep < stepView.getStepCount() - 1) {
                        currentStep++;
                        stepView.go(currentStep, true);
                    } else {
                        stepView.done(true);
                    }
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phoneNumber,        // Phone number to verify
                            60,                 // Timeout duration
                            TimeUnit.SECONDS,   // Unit of timeout
                            Authentication.this,               // Activity (for callback binding)
                            mCallbacks);        // OnVerificationStateChangedCallbacks
                }
            }
        });

        mCallbacks =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                LayoutInflater inflater = getLayoutInflater();
                View alertLayout= inflater.inflate(R.layout.processing_dialog,null);
                AlertDialog.Builder show = new AlertDialog.Builder(Authentication.this);

                show.setView(alertLayout);
                show.setCancelable(false);
                dialog_verifying = show.create();
                dialog_verifying.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog_verifying.show();
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                // ...
            }
        };

        verifyCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Common.isConnectedToInternet(getBaseContext())) {

                    String verificationCode = verifyCodeET.getText().toString();
                    if (verificationCode.isEmpty()) {
                        Toast.makeText(Authentication.this, "Digite o código de verificação que enviamos por SMS", Toast.LENGTH_SHORT).show();
                    } else {

                        LayoutInflater inflater = getLayoutInflater();
                        View alertLayout = inflater.inflate(R.layout.processing_dialog, null);
                        AlertDialog.Builder show = new AlertDialog.Builder(Authentication.this);

                        show.setView(alertLayout);
                        show.setCancelable(false);
                        dialog_verifying = show.create();
                        dialog_verifying.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog_verifying.show();

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationCode);
                        signInWithPhoneAuthCredential(credential);

                    }

                }else{

                Toasty.error(getApplicationContext(), "Sem conexão com a Internet", Toast.LENGTH_SHORT).show();

                return;
            }

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(nomeAuth.getText())){

                    nomeAuth.setError("Por favor, digite seu nome");
                    nomeAuth.requestFocus();

                }else {

                    //Gravando Token e Nome do Usuário
                    final String user_id = mAuth.getCurrentUser().getUid();
                    String token_id = FirebaseInstanceId.getInstance().getToken();
                    String name = nomeAuth.getText().toString();

                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("name", name);
                    userMap.put("token_id", token_id);

                    mFirestore.collection("Users").document(user_id).set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    });
                    //...

                    if (currentStep < stepView.getStepCount() - 1) {
                        currentStep++;
                        stepView.go(currentStep, true);
                    } else {
                        stepView.done(true);
                    }
                    LayoutInflater inflater = getLayoutInflater();
                    View alertLayout = inflater.inflate(R.layout.profile_create_dialog, null);
                    AlertDialog.Builder show = new AlertDialog.Builder(Authentication.this);
                    show.setView(alertLayout);
                    show.setCancelable(false);
                    profile_dialog = show.create();
                    profile_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    profile_dialog.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            profile_dialog.dismiss();
                            startActivity(new Intent(Authentication.this, MainActivity.class));
                            finish();
                        }
                    }, 5000);

                }
            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            dialog_verifying.dismiss();
                            if (currentStep < stepView.getStepCount() - 1) {
                                currentStep++;
                                stepView.go(currentStep, true);
                            } else {
                                stepView.done(true);
                            }
                            layout1.setVisibility(View.GONE);
                            layout2.setVisibility(View.GONE);
                            layout3.setVisibility(View.VISIBLE);

                        } else {

                            dialog_verifying.dismiss();
                            Toasty.error(Authentication.this, "Algo está errado", Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            }
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Authentication.this, PhoneAuth.class);
        startActivity(intent);
        finish();
    }
}
