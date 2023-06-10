package sl.com.eightdigitz.presentation.extensions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\b2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\b2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007\u001a2\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007\u001a2\u0010\t\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\f\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e\u00a8\u0006\u000f"}, d2 = {"showAlert", "", "Landroid/app/Activity;", "title", "", "message", "callback", "Lsl/com/eightdigitz/presentation/extensions/Callback;", "Landroidx/fragment/app/Fragment;", "showConfirm", "negativeText", "positiveText", "showToast", "context", "Landroid/content/Context;", "presentation_debug"})
public final class MessageExtensionsKt {
    
    public static final void showToast(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$showToast, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public static final void showAlert(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$showAlert, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public static final void showAlert(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$showAlert, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.presentation.extensions.Callback callback) {
    }
    
    public static final void showAlert(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$showAlert, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public static final void showAlert(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$showAlert, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.presentation.extensions.Callback callback) {
    }
    
    public static final void showConfirm(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$showConfirm, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    java.lang.String negativeText, @org.jetbrains.annotations.NotNull()
    java.lang.String positiveText, @org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.presentation.extensions.Callback callback) {
    }
    
    public static final void showConfirm(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$showConfirm, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    java.lang.String negativeText, @org.jetbrains.annotations.NotNull()
    java.lang.String positiveText, @org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.presentation.extensions.Callback callback) {
    }
}