package sl.com.eightdigitz.presentation.extensions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u001aJ\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004\u001a\u0014\u0010\r\u001a\u0004\u0018\u00010\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a:\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004\u001a\"\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001aJ\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004\u00a8\u0006\u0011"}, d2 = {"addFragment", "", "Landroidx/fragment/app/FragmentManager;", "containerViewId", "", "fragment", "Landroidx/fragment/app/Fragment;", "tag", "", "slideIn", "slideOut", "fadeIn", "fadeOut", "currentFragment", "removeFragment", "replaceFragment", "replaceFragmentWithBackStack", "presentation_debug"})
public final class FragmentManagerExtensionsKt {
    
    public static final void removeFragment(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager $this$removeFragment, @org.jetbrains.annotations.NotNull()
    java.lang.String tag, int slideIn, int slideOut, int fadeIn, int fadeOut) {
    }
    
    public static final void addFragment(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager $this$addFragment, int containerViewId, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.NotNull()
    java.lang.String tag, int slideIn, int slideOut, int fadeIn, int fadeOut) {
    }
    
    public static final void replaceFragment(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager $this$replaceFragment, int containerViewId, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.NotNull()
    java.lang.String tag) {
    }
    
    public static final void removeFragment(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager $this$removeFragment, @org.jetbrains.annotations.NotNull()
    java.lang.String tag) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final androidx.fragment.app.Fragment currentFragment(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager $this$currentFragment, int containerViewId) {
        return null;
    }
    
    public static final void replaceFragmentWithBackStack(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager $this$replaceFragmentWithBackStack, int containerViewId, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.NotNull()
    java.lang.String tag, int slideIn, int slideOut, int fadeIn, int fadeOut) {
    }
}