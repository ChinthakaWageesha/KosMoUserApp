package sl.com.eightdigitz.presentation.cropper;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0011H\u0014J\b\u0010\u0014\u001a\u00020\u000fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lsl/com/eightdigitz/presentation/cropper/CropImageActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "file", "Ljava/io/File;", "mCropCallback", "Lcom/isseiaoki/simplecropview/callback/CropCallback;", "mIntKey", "", "Ljava/lang/Integer;", "mSaveCallback", "Lcom/isseiaoki/simplecropview/callback/SaveCallback;", "outputFileUri", "Landroid/net/Uri;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "oldInstanceState", "setUpCrop", "Companion", "presentation_debug"})
public final class CropImageActivity extends androidx.appcompat.app.AppCompatActivity {
    private java.io.File file;
    private final com.isseiaoki.simplecropview.callback.CropCallback mCropCallback = null;
    private final com.isseiaoki.simplecropview.callback.SaveCallback mSaveCallback = null;
    private android.net.Uri outputFileUri;
    private java.lang.Integer mIntKey;
    public static final int REQUEST_CROP_IMAGE = 11211;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_KEY_IMAGE_URL = "image_url";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_CROP_MODE = "extra_crop_mode";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_KEY = "key";
    public static final int MODE_CIRCLE = 1;
    public static final int MODE_SQUARE = 2;
    public static final sl.com.eightdigitz.presentation.cropper.CropImageActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setUpCrop() {
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle oldInstanceState) {
    }
    
    public CropImageActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lsl/com/eightdigitz/presentation/cropper/CropImageActivity$Companion;", "", "()V", "MODE_CIRCLE", "", "MODE_SQUARE", "PREF_CROP_MODE", "", "PREF_KEY", "PREF_KEY_IMAGE_URL", "REQUEST_CROP_IMAGE", "startCropActivity", "", "activty", "Landroid/app/Activity;", "imageUrl", "fragment", "Landroidx/fragment/app/Fragment;", "presentation_debug"})
    public static final class Companion {
        
        public final void startCropActivity(@org.jetbrains.annotations.NotNull()
        android.app.Activity activty, @org.jetbrains.annotations.NotNull()
        java.lang.String imageUrl) {
        }
        
        public final void startCropActivity(@org.jetbrains.annotations.NotNull()
        androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.NotNull()
        java.lang.String imageUrl) {
        }
        
        private Companion() {
            super();
        }
    }
}