package sl.com.eightdigitz.authentication.presentation.registration;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0010H\u0003J#\u0010#\u001a\u00020!2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010%\u001a\u00020\u0005H\u0002\u00a2\u0006\u0002\u0010&J\b\u0010\'\u001a\u00020!H\u0002J\b\u0010(\u001a\u00020!H\u0002J\b\u0010)\u001a\u00020!H\u0002J\u0010\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020,H\u0002J\u0016\u0010-\u001a\u00020!2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020,0/H\u0002J\u0016\u00100\u001a\u00020!2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00100/H\u0002J\"\u00101\u001a\u00020!2\u0006\u0010%\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u00052\b\u00103\u001a\u0004\u0018\u000104H\u0015J\u0012\u00105\u001a\u00020!2\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u0012\u00108\u001a\u00020!2\b\u00109\u001a\u0004\u0018\u00010:H\u0014J\b\u0010;\u001a\u00020!H\u0014J\b\u0010<\u001a\u00020!H\u0003J\b\u0010=\u001a\u00020!H\u0002J\b\u0010>\u001a\u00020!H\u0002J\b\u0010?\u001a\u00020!H\u0002J\b\u0010@\u001a\u00020!H\u0002J\b\u0010A\u001a\u00020!H\u0002J\b\u0010B\u001a\u00020CH\u0002J\b\u0010D\u001a\u00020CH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001f\u0010\u001a\u001a\u0004\b\u001d\u0010\u001e\u00a8\u0006E"}, d2 = {"Lsl/com/eightdigitz/authentication/presentation/registration/RegisterUser;", "Lsl/com/eightdigitz/core/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "AVATAR_IMAGE_REQ_CODE", "", "arrayOptions", "", "", "[Ljava/lang/CharSequence;", "calender", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "ccpPicker", "Lcom/hbb20/CountryCodePicker;", "dobFormat", "", "file", "Ljava/io/File;", "userRequest", "Lsl/com/eightdigitz/core/model/domain/DUserRegister;", "vmAvatar", "Lsl/com/eightdigitz/authentication/presentation/contact/JoinContactViewModel;", "getVmAvatar", "()Lsl/com/eightdigitz/authentication/presentation/contact/JoinContactViewModel;", "vmAvatar$delegate", "Lkotlin/Lazy;", "vmRegister", "Lsl/com/eightdigitz/authentication/presentation/registration/RegistrationViewModel;", "getVmRegister", "()Lsl/com/eightdigitz/authentication/presentation/registration/RegistrationViewModel;", "vmRegister$delegate", "authWithBackend", "", "fmcToken", "cameraOptionsDialog", "arrayOption", "requestCode", "([Ljava/lang/CharSequence;I)V", "getData", "getFcmTokenObserver", "init", "navigateToPostRegister", "user", "Lsl/com/eightdigitz/core/model/presentation/PUser;", "observerSaveUser", "resource", "Lsl/com/eightdigitz/presentation/Resource;", "observerUploadAvatar", "onActivityResult", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "processSignUp", "setDatePicker", "setSpannableContent", "setToolbar", "startCountryPicker", "updateLabel", "validateForm", "", "validateProPic", "authentication_debug"})
public final class RegisterUser extends sl.com.eightdigitz.core.base.BaseActivity implements android.view.View.OnClickListener {
    private final int AVATAR_IMAGE_REQ_CODE = 1124;
    private final kotlin.Lazy vmAvatar$delegate = null;
    private final kotlin.Lazy vmRegister$delegate = null;
    private java.util.Calendar calender;
    private sl.com.eightdigitz.core.model.domain.DUserRegister userRequest;
    private com.hbb20.CountryCodePicker ccpPicker;
    private final java.lang.CharSequence[] arrayOptions = {"Take photo", "Choose from gallery"};
    private java.io.File file;
    private java.lang.String dobFormat;
    private java.util.HashMap _$_findViewCache;
    
    private final sl.com.eightdigitz.authentication.presentation.contact.JoinContactViewModel getVmAvatar() {
        return null;
    }
    
    private final sl.com.eightdigitz.authentication.presentation.registration.RegistrationViewModel getVmRegister() {
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
    
    private final void setSpannableContent() {
    }
    
    private final void startCountryPicker() {
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final void processSignUp() {
    }
    
    private final void getFcmTokenObserver() {
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final void authWithBackend(java.lang.String fmcToken) {
    }
    
    private final boolean validateForm() {
        return false;
    }
    
    private final boolean validateProPic() {
        return false;
    }
    
    private final void observerSaveUser(sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.presentation.PUser> resource) {
    }
    
    private final void observerUploadAvatar(sl.com.eightdigitz.presentation.Resource<java.lang.String> resource) {
    }
    
    private final void setDatePicker() {
    }
    
    private final void updateLabel() {
    }
    
    private final void cameraOptionsDialog(java.lang.CharSequence[] arrayOption, int requestCode) {
    }
    
    private final void navigateToPostRegister(sl.com.eightdigitz.core.model.presentation.PUser user) {
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    public RegisterUser() {
        super();
    }
}