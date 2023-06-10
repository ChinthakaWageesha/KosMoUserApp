package sl.com.eightdigitz.authentication.presentation.verifyOTP;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0003J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u000fH\u0002J\b\u0010\u0014\u001a\u00020\u000fH\u0002J\b\u0010\u0015\u001a\u00020\u000fH\u0002J\u0016\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\u0016\u0010\u001a\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018H\u0002J\u0016\u0010\u001c\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0018H\u0002J\"\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\u0012\u0010$\u001a\u00020\u000f2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010\'\u001a\u00020\u000f2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\b\u0010*\u001a\u00020\u000fH\u0014J\b\u0010+\u001a\u00020\u0005H\u0016J\b\u0010,\u001a\u00020\u000fH\u0002J\b\u0010-\u001a\u00020\u000fH\u0003J\b\u0010.\u001a\u00020\u000fH\u0002J\b\u0010/\u001a\u00020\u000fH\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u00060"}, d2 = {"Lsl/com/eightdigitz/authentication/presentation/verifyOTP/VerifyOTP;", "Lsl/com/eightdigitz/core/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "isVerifyEnable", "", "userRequest", "Lsl/com/eightdigitz/core/model/domain/DUserRegister;", "vmOTPToken", "Lsl/com/eightdigitz/authentication/presentation/verifyOTP/OTPViewModel;", "getVmOTPToken", "()Lsl/com/eightdigitz/authentication/presentation/verifyOTP/OTPViewModel;", "vmOTPToken$delegate", "Lkotlin/Lazy;", "countDownTimer", "", "getData", "init", "makeVerifyDisable", "makeVerifyEnable", "navigateToGetStart", "navigateToSuccess", "observerGetOTP", "resource", "Lsl/com/eightdigitz/presentation/Resource;", "Lsl/com/eightdigitz/core/model/domain/DOTP;", "observerOTPToken", "Lsl/com/eightdigitz/core/model/domain/DOTPToken;", "observerRefUser", "Lsl/com/eightdigitz/core/model/domain/DUser;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onSupportNavigateUp", "onVerify", "resendCode", "setToolbar", "verifyCode", "authentication_debug"})
public final class VerifyOTP extends sl.com.eightdigitz.core.base.BaseActivity implements android.view.View.OnClickListener {
    private sl.com.eightdigitz.core.model.domain.DUserRegister userRequest;
    private final kotlin.Lazy vmOTPToken$delegate = null;
    private boolean isVerifyEnable = false;
    private java.util.HashMap _$_findViewCache;
    
    private final sl.com.eightdigitz.authentication.presentation.verifyOTP.OTPViewModel getVmOTPToken() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setToolbar() {
    }
    
    private final void getData() {
    }
    
    private final void init() {
    }
    
    private final void makeVerifyEnable() {
    }
    
    private final void makeVerifyDisable() {
    }
    
    private final void onVerify() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void countDownTimer() {
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final void resendCode() {
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final void verifyCode() {
    }
    
    private final void observerOTPToken(sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DOTPToken> resource) {
    }
    
    private final void observerGetOTP(sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DOTP> resource) {
    }
    
    private final void observerRefUser(sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DUser> resource) {
    }
    
    private final void navigateToGetStart() {
    }
    
    private final void navigateToSuccess() {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    public VerifyOTP() {
        super();
    }
}