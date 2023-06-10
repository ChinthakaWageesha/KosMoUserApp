package sl.com.eightdigitz.authentication.presentation.registration;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003B\u0005\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u0014\u001a\u00020\u0006H\u0002J\b\u0010\u0015\u001a\u00020\u0006H\u0003J\b\u0010\u0016\u001a\u00020\u0006H\u0003J\u0019\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0005H\u0096\u0002J\b\u0010\u001a\u001a\u00020\u0006H\u0002J\u001c\u0010\u001b\u001a\u00020\u00062\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e0\u001dH\u0002J\u0016\u0010\u001f\u001a\u00020\u00062\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020 0\u001dH\u0002J\u0016\u0010!\u001a\u00020\u00062\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\"0\u001dH\u0002J\u0016\u0010#\u001a\u00020\u00062\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020$0\u001dH\u0002J\u0012\u0010%\u001a\u00020\u00062\b\u0010&\u001a\u0004\u0018\u00010\'H\u0017J\u0012\u0010(\u001a\u00020\u00062\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\u0012\u0010+\u001a\u00020\u00052\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0010\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u00020\u0006H\u0014J\b\u00102\u001a\u00020\u0006H\u0002J\b\u00103\u001a\u00020\u0006H\u0003J\b\u00104\u001a\u00020\u0006H\u0002J\b\u00105\u001a\u00020\u0006H\u0002J\u0016\u00106\u001a\u00020\u00062\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000408H\u0002R\"\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011\u00a8\u00069"}, d2 = {"Lsl/com/eightdigitz/authentication/presentation/registration/PostRegister;", "Lsl/com/eightdigitz/core/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "Lkotlin/Function2;", "Lsl/com/eightdigitz/core/model/domain/DPreference;", "", "", "()V", "selectedPreferenceIds", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "user", "Lsl/com/eightdigitz/core/model/presentation/PUser;", "viewModel", "Lsl/com/eightdigitz/authentication/presentation/registration/RegistrationViewModel;", "getViewModel", "()Lsl/com/eightdigitz/authentication/presentation/registration/RegistrationViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getData", "getPreferences", "init", "invoke", "preference", "isChecked", "navigateToMain", "observerGetPreferences", "resource", "Lsl/com/eightdigitz/presentation/Resource;", "", "observerRegisterToken", "Lsl/com/eightdigitz/core/model/domain/DFirebaseToken;", "observerRequestNotification", "Lsl/com/eightdigitz/models/Success;", "observerSetPreferences", "Lsl/com/eightdigitz/core/model/domain/DUser;", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "registerFirebaseToken", "requestWelcomeNotification", "setPreferences", "setToolbar", "setUpPreferenceAdapter", "preferenceList", "", "authentication_debug"})
public final class PostRegister extends sl.com.eightdigitz.core.base.BaseActivity implements android.view.View.OnClickListener, kotlin.jvm.functions.Function2<sl.com.eightdigitz.core.model.domain.DPreference, java.lang.Boolean, kotlin.Unit> {
    private final kotlin.Lazy viewModel$delegate = null;
    private sl.com.eightdigitz.core.model.presentation.PUser user;
    private java.util.ArrayList<java.lang.String> selectedPreferenceIds;
    private java.util.HashMap _$_findViewCache;
    
    private final sl.com.eightdigitz.authentication.presentation.registration.RegistrationViewModel getViewModel() {
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
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void init() {
    }
    
    private final void registerFirebaseToken() {
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final void getPreferences() {
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final void requestWelcomeNotification() {
    }
    
    private final void observerRegisterToken(sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DFirebaseToken> resource) {
    }
    
    private final void observerGetPreferences(sl.com.eightdigitz.presentation.Resource<? extends java.util.List<sl.com.eightdigitz.core.model.domain.DPreference>> resource) {
    }
    
    private final void observerSetPreferences(sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DUser> resource) {
    }
    
    private final void observerRequestNotification(sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.models.Success> resource) {
    }
    
    private final void setUpPreferenceAdapter(java.util.List<sl.com.eightdigitz.core.model.domain.DPreference> preferenceList) {
    }
    
    private final void navigateToMain() {
    }
    
    private final void setPreferences() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.Nullable()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    @java.lang.Override()
    public void invoke(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.core.model.domain.DPreference preference, boolean isChecked) {
    }
    
    public PostRegister() {
        super();
    }
}