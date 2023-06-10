package sl.com.eightdigitz.presentation.extensions;

import java.lang.System;

@android.annotation.SuppressLint(value = {"InflateParams"})
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0007J\u0006\u0010&\u001a\u00020\u0011J \u0010\u001c\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u00052\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020\'\u0018\u00010*J\"\u0010\u001c\u001a\u00020\'2\b\b\u0001\u0010+\u001a\u00020,2\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020\'\u0018\u00010*J\u0014\u0010-\u001a\u00020\'2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\'0*J \u0010!\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u00052\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020\'\u0018\u00010*J\"\u0010!\u001a\u00020\'2\b\b\u0001\u0010+\u001a\u00020,2\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020\'\u0018\u00010*J\f\u0010.\u001a\u00020\'*\u00020\u0018H\u0002J\u001c\u0010/\u001a\u00020\'*\u00020\u001d2\u000e\u0010)\u001a\n\u0012\u0004\u0012\u00020\'\u0018\u00010*H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0006\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u0017\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010\u0017\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010!\u001a\u00020\u001d8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010\u0017\u001a\u0004\b\"\u0010\u001fR\u001b\u0010\u0004\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b%\u0010\u0017\u001a\u0004\b$\u0010\u001a\u00a8\u00060"}, d2 = {"Lsl/com/eightdigitz/presentation/extensions/AlertDialogHelper;", "", "context", "Landroid/content/Context;", "title", "", "message", "(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;)V", "builder", "Landroidx/appcompat/app/AlertDialog$Builder;", "cancelable", "", "getCancelable", "()Z", "setCancelable", "(Z)V", "dialog", "Landroidx/appcompat/app/AlertDialog;", "dialogView", "Landroid/view/View;", "getDialogView", "()Landroid/view/View;", "dialogView$delegate", "Lkotlin/Lazy;", "Landroid/widget/TextView;", "getMessage", "()Landroid/widget/TextView;", "message$delegate", "negativeButton", "Landroid/widget/Button;", "getNegativeButton", "()Landroid/widget/Button;", "negativeButton$delegate", "positiveButton", "getPositiveButton", "positiveButton$delegate", "getTitle", "title$delegate", "create", "", "text", "func", "Lkotlin/Function0;", "textResource", "", "onCancel", "goneIfTextEmpty", "setClickListenerToDialogButton", "presentation_debug"})
public final class AlertDialogHelper {
    private final kotlin.Lazy dialogView$delegate = null;
    private final androidx.appcompat.app.AlertDialog.Builder builder = null;
    private final kotlin.Lazy title$delegate = null;
    private final kotlin.Lazy message$delegate = null;
    private final kotlin.Lazy positiveButton$delegate = null;
    private final kotlin.Lazy negativeButton$delegate = null;
    private androidx.appcompat.app.AlertDialog dialog;
    private boolean cancelable = true;
    
    private final android.view.View getDialogView() {
        return null;
    }
    
    private final android.widget.TextView getTitle() {
        return null;
    }
    
    private final android.widget.TextView getMessage() {
        return null;
    }
    
    private final android.widget.Button getPositiveButton() {
        return null;
    }
    
    private final android.widget.Button getNegativeButton() {
        return null;
    }
    
    public final boolean getCancelable() {
        return false;
    }
    
    public final void setCancelable(boolean p0) {
    }
    
    public final void positiveButton(@androidx.annotation.StringRes()
    int textResource, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> func) {
    }
    
    public final void positiveButton(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence text, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> func) {
    }
    
    public final void negativeButton(@androidx.annotation.StringRes()
    int textResource, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> func) {
    }
    
    public final void negativeButton(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence text, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> func) {
    }
    
    public final void onCancel(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> func) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.appcompat.app.AlertDialog create() {
        return null;
    }
    
    private final void goneIfTextEmpty(@org.jetbrains.annotations.NotNull()
    android.widget.TextView $this$goneIfTextEmpty) {
    }
    
    private final void setClickListenerToDialogButton(@org.jetbrains.annotations.NotNull()
    android.widget.Button $this$setClickListenerToDialogButton, kotlin.jvm.functions.Function0<kotlin.Unit> func) {
    }
    
    public AlertDialogHelper(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.CharSequence title, @org.jetbrains.annotations.Nullable()
    java.lang.CharSequence message) {
        super();
    }
}