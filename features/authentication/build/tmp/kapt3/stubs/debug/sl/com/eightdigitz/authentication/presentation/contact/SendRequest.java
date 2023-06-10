package sl.com.eightdigitz.authentication.presentation.contact;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J#\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0017\u001a\u00020\u0005H\u0002\u00a2\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u0015H\u0002J\u0016\u0010\u001a\u001a\u00020\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J\u0016\u0010\u001e\u001a\u00020\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u001cH\u0002J\"\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\"H\u0015J\u0012\u0010#\u001a\u00020\u00152\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0012\u0010&\u001a\u00020\u00152\b\u0010\'\u001a\u0004\u0018\u00010(H\u0014J\b\u0010)\u001a\u00020\u0015H\u0014J\b\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020\u0015H\u0003J\b\u0010-\u001a\u00020\u0015H\u0002J\b\u0010.\u001a\u00020+H\u0002J\b\u0010/\u001a\u00020+H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011\u00a8\u00060"}, d2 = {"Lsl/com/eightdigitz/authentication/presentation/contact/SendRequest;", "Lsl/com/eightdigitz/core/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "AVATAR_IMAGE_REQ_CODE", "", "arrayOptions", "", "", "[Ljava/lang/CharSequence;", "file", "Ljava/io/File;", "uploadedImageURL", "", "vm", "Lsl/com/eightdigitz/authentication/presentation/contact/JoinContactViewModel;", "getVm", "()Lsl/com/eightdigitz/authentication/presentation/contact/JoinContactViewModel;", "vm$delegate", "Lkotlin/Lazy;", "cameraOptionsDialog", "", "arrayOption", "requestCode", "([Ljava/lang/CharSequence;I)V", "init", "observerJoinUs", "resource", "Lsl/com/eightdigitz/presentation/Resource;", "Lsl/com/eightdigitz/core/model/domain/DJoinUs;", "observerUploadJoinUsImage", "onActivityResult", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onSupportNavigateUp", "", "sendRequest", "setToolbar", "validateForm", "validateProPic", "authentication_debug"})
public final class SendRequest extends sl.com.eightdigitz.core.base.BaseActivity implements android.view.View.OnClickListener {
    private final kotlin.Lazy vm$delegate = null;
    private final int AVATAR_IMAGE_REQ_CODE = 1124;
    private final java.lang.CharSequence[] arrayOptions = {"Take photo", "Choose from gallery"};
    private java.io.File file;
    private java.lang.String uploadedImageURL;
    private java.util.HashMap _$_findViewCache;
    
    private final sl.com.eightdigitz.authentication.presentation.contact.JoinContactViewModel getVm() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setToolbar() {
    }
    
    private final void init() {
    }
    
    private final void cameraOptionsDialog(java.lang.CharSequence[] arrayOption, int requestCode) {
    }
    
    private final boolean validateForm() {
        return false;
    }
    
    private final boolean validateProPic() {
        return false;
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final void sendRequest() {
    }
    
    private final void observerUploadJoinUsImage(sl.com.eightdigitz.presentation.Resource<java.lang.String> resource) {
    }
    
    private final void observerJoinUs(sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DJoinUs> resource) {
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
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
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    public SendRequest() {
        super();
    }
}