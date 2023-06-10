package sl.com.eightdigitz.presentation.extensions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0003\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0005\u001a\u0012\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\u0006\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"hideKeyboard", "", "Landroid/app/Activity;", "Landroidx/fragment/app/Fragment;", "hideKeyboardView", "Landroid/view/View;", "showKeyboard", "editText", "Landroid/widget/EditText;", "presentation_debug"})
public final class KeyboardExtensionsKt {
    
    /**
     * Use only from Activities, don't use from Fragment (with getActivity) or from Dialog/DialogFragment
     */
    public static final void showKeyboard(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$showKeyboard, @org.jetbrains.annotations.NotNull()
    android.widget.EditText editText) {
    }
    
    public static final void hideKeyboard(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$hideKeyboard) {
    }
    
    public static final void showKeyboard(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$showKeyboard, @org.jetbrains.annotations.NotNull()
    android.widget.EditText editText) {
    }
    
    public static final void hideKeyboard(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$hideKeyboard) {
    }
    
    /**
     * Use everywhere except from Activity (Custom View, Fragment, Dialogs, DialogFragments).
     */
    public static final void hideKeyboardView(@org.jetbrains.annotations.NotNull()
    android.view.View $this$hideKeyboardView) {
    }
}