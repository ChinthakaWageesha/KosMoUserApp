package sl.com.eightdigitz.authentication.utils.pdf;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u0007J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&\u00a8\u0006\b"}, d2 = {"Lsl/com/eightdigitz/authentication/utils/pdf/Download;", "", "download", "", "url", "", "customName", "Listener", "authentication_debug"})
public abstract interface Download {
    
    /**
     * @param url
     * @param customName custom name of the file
     */
    public abstract void download(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    java.lang.String customName);
    
    public abstract void download(@org.jetbrains.annotations.NotNull()
    java.lang.String url);
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&\u00a8\u0006\u0010"}, d2 = {"Lsl/com/eightdigitz/authentication/utils/pdf/Download$Listener;", "", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onProgressUpdate", "progress", "", "total", "onSuccess", "url", "", "destinationPath", "Ljava/io/File;", "authentication_debug"})
    public static abstract interface Listener {
        
        public abstract void onSuccess(@org.jetbrains.annotations.NotNull()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        java.io.File destinationPath);
        
        public abstract void onFailure(@org.jetbrains.annotations.NotNull()
        java.lang.Exception e);
        
        public abstract void onProgressUpdate(int progress, int total);
    }
}