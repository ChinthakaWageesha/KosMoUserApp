package sl.com.eightdigitz.authentication.presentation.verifyOTP;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0003J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0013H\u0002J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0002J\u0016\u0010\u001d\u001a\u00020\u00132\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0002J\"\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u00162\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\u0012\u0010&\u001a\u00020\u00132\b\u0010\'\u001a\u0004\u0018\u00010(H\u0016J\u0012\u0010)\u001a\u00020\u00132\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\b\u0010,\u001a\u00020\u0013H\u0014J\b\u0010-\u001a\u00020\tH\u0016J\b\u0010.\u001a\u00020\u0013H\u0002J\b\u0010/\u001a\u00020\u0013H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f\u00a8\u00060"}, d2 = {"Lsl/com/eightdigitz/authentication/presentation/verifyOTP/GetOTP;", "Lsl/com/eightdigitz/core/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "auth0", "Lcom/auth0/android/Auth0;", "ccpPicker", "Lcom/hbb20/CountryCodePicker;", "isNextClickable", "", "userRequest", "Lsl/com/eightdigitz/core/model/domain/DUserRegister;", "vmOTP", "Lsl/com/eightdigitz/authentication/presentation/verifyOTP/OTPViewModel;", "getVmOTP", "()Lsl/com/eightdigitz/authentication/presentation/verifyOTP/OTPViewModel;", "vmOTP$delegate", "Lkotlin/Lazy;", "getData", "", "getOtp", "getResId", "", "drawableName", "", "init", "makeNextDisable", "makeNextEnable", "navigateToVerify", "observerGetOTP", "resource", "Lsl/com/eightdigitz/presentation/Resource;", "Lsl/com/eightdigitz/core/model/domain/DOTP;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onSupportNavigateUp", "setToolbar", "startCountryPicker", "authentication_debug"})
public final class GetOTP extends sl.com.eightdigitz.core.base.BaseActivity implements android.view.View.OnClickListener {
    private com.hbb20.CountryCodePicker ccpPicker;
    private com.auth0.android.Auth0 auth0;
    private sl.com.eightdigitz.core.model.domain.DUserRegister userRequest;
    private final kotlin.Lazy vmOTP$delegate = null;
    private boolean isNextClickable = false;
    private java.util.HashMap _$_findViewCache;
    
    private final sl.com.eightdigitz.authentication.presentation.verifyOTP.OTPViewModel getVmOTP() {
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
    
    private final void makeNextEnable() {
    }
    
    private final void makeNextDisable() {
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final void getOtp() {
    }
    
    private final void observerGetOTP(sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DOTP> resource) {
    }
    
    private final void navigateToVerify() {
    }
    
    private final void startCountryPicker() {
    }
    
    private final int getResId(java.lang.String drawableName) {
        return 0;
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    public GetOTP() {
        super();
    }
}