package sl.com.eightdigitz.presentation.extensions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003\u001a\u001e\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u001e\u0010\t\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u000b\u001a\u00020\u0004*\u00020\u0002\u001a\n\u0010\f\u001a\u00020\u0004*\u00020\u0002\u001a\u001e\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003\u001a&\u0010\u000e\u001a\u00020\b*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00042\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0003\u001a4\u0010\u000e\u001a\u00020\b*\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0003\u001a4\u0010\u0011\u001a\u00020\b*\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0003\u001a4\u0010\u0012\u001a\u00020\b*\u00020\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0003\u00a8\u0006\u0013"}, d2 = {"afterTextChanged", "", "Landroid/widget/EditText;", "Lkotlin/Function1;", "", "backGroundRunTime", "errorMessage", "isCheckValidateIcon", "", "backGroundRunTimeAppET", "clearText", "getString", "getStringTrim", "onTextChanged", "validate", "message", "validator", "validateAppEditTextOnTextChange", "validateOnTextChange", "presentation_debug"})
public final class EditTextExtensionsKt {
    
    public static final void afterTextChanged(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$afterTextChanged, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> afterTextChanged) {
    }
    
    public static final void onTextChanged(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$onTextChanged, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onTextChanged) {
    }
    
    public static final boolean validate(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$validate, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.Boolean> validator) {
        return false;
    }
    
    public static final boolean validate(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$validate, @org.jetbrains.annotations.Nullable()
    java.lang.String message, boolean isCheckValidateIcon, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.Boolean> validator) {
        return false;
    }
    
    public static final boolean validateOnTextChange(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$validateOnTextChange, @org.jetbrains.annotations.Nullable()
    java.lang.String message, boolean isCheckValidateIcon, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.Boolean> validator) {
        return false;
    }
    
    private static final void backGroundRunTime(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$backGroundRunTime, java.lang.String errorMessage, boolean isCheckValidateIcon) {
    }
    
    public static final boolean validateAppEditTextOnTextChange(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$validateAppEditTextOnTextChange, @org.jetbrains.annotations.Nullable()
    java.lang.String message, boolean isCheckValidateIcon, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.Boolean> validator) {
        return false;
    }
    
    private static final void backGroundRunTimeAppET(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$backGroundRunTimeAppET, java.lang.String errorMessage, boolean isCheckValidateIcon) {
    }
    
    /**
     * @return String value of the EditTextView
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getString(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$getString) {
        return null;
    }
    
    /**
     * @return Trimmed String value of the EditTextView
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getStringTrim(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$getStringTrim) {
        return null;
    }
    
    public static final void clearText(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$clearText) {
    }
}