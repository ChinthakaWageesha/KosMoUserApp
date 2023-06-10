package sl.com.eightdigitz.presentation.utile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J3\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u0010"}, d2 = {"Lsl/com/eightdigitz/presentation/utile/AppDialog;", "", "()V", "showChooiceDialog", "", "context", "Landroid/content/Context;", "event", "Lsl/com/eightdigitz/presentation/utile/AppDialog$DialogEvent;", "title", "", "array", "", "", "(Landroid/content/Context;Lsl/com/eightdigitz/presentation/utile/AppDialog$DialogEvent;Ljava/lang/String;[Ljava/lang/CharSequence;)V", "DialogEvent", "presentation_debug"})
public final class AppDialog {
    public static final sl.com.eightdigitz.presentation.utile.AppDialog INSTANCE = null;
    
    public final void showChooiceDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    sl.com.eightdigitz.presentation.utile.AppDialog.DialogEvent event, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.CharSequence[] array) {
    }
    
    private AppDialog() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016\u00a8\u0006\b"}, d2 = {"Lsl/com/eightdigitz/presentation/utile/AppDialog$DialogEvent;", "", "onItemClick", "", "which", "", "onNegativeClicked", "onPositiveClicked", "presentation_debug"})
    public static abstract interface DialogEvent {
        
        public abstract void onPositiveClicked();
        
        public abstract void onNegativeClicked();
        
        public abstract void onItemClick(int which);
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
        public static final class DefaultImpls {
            
            public static void onPositiveClicked(sl.com.eightdigitz.presentation.utile.AppDialog.DialogEvent $this) {
            }
            
            public static void onNegativeClicked(sl.com.eightdigitz.presentation.utile.AppDialog.DialogEvent $this) {
            }
            
            public static void onItemClick(sl.com.eightdigitz.presentation.utile.AppDialog.DialogEvent $this, int which) {
            }
        }
    }
}